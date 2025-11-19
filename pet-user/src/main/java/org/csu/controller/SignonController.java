package org.csu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.csu.dao.AdminDao;
import org.csu.dao.SignonDao;
import org.csu.domain.Signon;
import org.csu.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YinBo
 * @since 2025-03-08
 */
@RestController
@RequestMapping("/signon")
public class SignonController {
    @Autowired
    private IAdminService adminService;

//    @GetMapping("/login")
//    public String loginPage() {
//        System.out.println("man");
//        return "login";
//    }

    //跳转页面
    @Autowired
    private SignonDao signonDao;
//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password, Model model,
//                        RedirectAttributes redirectAttributes, HttpSession session) {
//
//        System.out.println("kobe");
//        QueryWrapper<Signon> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username).eq("password", password);
//        Signon signon = signonDao.selectOne(queryWrapper);
//        if (signon != null) {
//            //model.addAttribute("username", username);
//            redirectAttributes.addFlashAttribute("username", username);
//            session.setAttribute("signon", signon);
//            return "redirect:/pages/categoryManager.html";
//        }
//        else {;
//            //model.addAttribute("message", "用户名或密码错误");
//            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
//            return "redirect:/index.html";
//        }
//    }

    // 模拟数据库中的用户信息
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "123456";
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        System.out.println("收到 JSON 登录请求，用户名：" + username + "，密码：" + password);

        Map<String, Object> response = new HashMap<>();
        if ("admin".equals(username) && "123456".equals(password)) {
            session.setAttribute("user", username);
            response.put("status", "success");
            response.put("message", "登录成功");
        } else {
            response.put("status", "error");
            response.put("message", "用户名或密码错误");
        }
        return response;
    }


    @GetMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate(); // 让 session 失效
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "已退出登录");
        return response;
    }

    @GetMapping("/check")
    public Map<String, Object> checkSession(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (session.getAttribute("user") != null) {
            response.put("status", "logged-in");
            response.put("user", session.getAttribute("user"));
        } else {
            response.put("status", "not-logged-in");
        }
        return response;
    }
}
