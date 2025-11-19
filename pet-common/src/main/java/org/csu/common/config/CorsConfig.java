package org.csu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // 允许所有路径
                        .allowedOrigins("http://localhost:4399","http://localhost:4173") // 允许的前端地址
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的 HTTP 方法
                        .allowedHeaders("*") // 允许所有请求头
                        .exposedHeaders("Authorization")  // 可选
                        .allowCredentials(true) // 允许携带 Cookie
                        .maxAge(3600); // 预检请求缓存时间
            }
        };
    }
}
