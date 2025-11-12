package org.csu.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.csu.controller.Code;
import org.csu.controller.Result;
import org.csu.utils.JwtUtil;
import org.csu.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取Token
        String token = request.getHeader("Authorization");

        // 验证Token
        try {
            // 解析Token中的信息
            Map<String, Object> claims = JwtUtil.parseToken(token);

            // 如果解析成功，将Token的相关信息存储到ThreadLocal中
            ThreadLocalUtil.set(claims);

            // 令牌有效，放行请求
            return true;
        } catch (Exception e) {
            // Token无效，返回401 Unauthorized
//            response.setStatus(401);
//            response.getWriter().write("Unauthorized: Invalid or expired token");
            response.setStatus(401);

            Result result = new Result(Code.SAVE_ERR, false, "Unauthorized: Invalid or expired token");
            String json = new ObjectMapper().writeValueAsString(result);
            response.getWriter().write(json);
            // 拦截请求，不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求处理完成后，清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
