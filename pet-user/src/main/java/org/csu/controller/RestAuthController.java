package org.csu.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.csu.common.utils.JwtUtil;
import org.csu.dao.AccountDao;
import org.csu.dao.SignonDao;
import org.csu.domain.Account;
import org.csu.domain.Signon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class RestAuthController {

    @Autowired
    private SignonDao signonDao;
    @Autowired
    private AccountDao accountDao;

    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        System.out.println("render");
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public  void login(AuthCallback callback ,HttpServletResponse response) throws IOException {

        AuthRequest authRequest = getAuthRequest();
        //获取 GitHub 返回的用户信息
        JSONObject jsonObject = JSONUtil.parseObj(authRequest.login(callback),false);
        JSONObject userInfo = JSONUtil.parseObj(jsonObject.get("data"), false);
        // 获取从 GitHub 返回的用户基本信息
        String githubUuid = userInfo.getStr("uuid");
        String username = userInfo.getStr("username");
        String avatar = userInfo.getStr("avatar");
        System.out.println(githubUuid+","+username+","+avatar);
        // 查询 signon 表中是否已有该 GitHub 用户
        Signon signon = signonDao.selectOne(new QueryWrapper<Signon>().eq("github_uuid", githubUuid));

        // 如果该 GitHub 用户已经存在
        if (signon != null) {
            // 可选择更新 GitHub 头像等信息
            signon.setAvatarUrl(avatar);  // 更新头像 URL
            signonDao.updateById(signon);  // 更新数据库记录
        } else {
            // 如果是新用户，通过 GitHub 信息进行注册
            signon = new Signon();
            signon.setGithubUuid(githubUuid);
            signon.setUsername(username);  // 通过 GitHub 的 username 设置
            signon.setAvatarUrl(avatar);
            Account account= new Account();
            account.setUserid(username);
            accountDao.insert(account);
            // 假设没有 password 字段，或者你可以设定一个默认密码
            signon.setPassword("default_password");  // 设置默认密码
            signonDao.insert(signon);  // 插入新用户

        }

        // 生成 JWT Token 等登录逻辑
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", signon.getUsername());
//      claims.put("githubUuid", signon.getGithubUuid());
        String token = JwtUtil.genToken(claims);  // 生成 token

        // 返回成功和 token
//        return new Result(Code.SAVE_OK,true,token);
        // 回调成功后，重定向到前端首页并带 token 参数
        String redirectUrl = "http://localhost:4173/oauth/callback?token=" + token;
        // 重定向前端页面
        response.sendRedirect(redirectUrl);
    }

    private AuthRequest getAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId("Ov23linG21czB3FJR5yk")
                .clientSecret("12dbb83508c786fb087286e06b14e4df6ec176e0")
                .redirectUri("http://localhost:81/oauth/callback")
                .build());
    }
}
