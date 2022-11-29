package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        org.springframework.context.ApplicationContext ac = SpringApplication.run(StartApplication.class, args);
        String url = "http://localhost:" + ac.getEnvironment().getProperty("server.port") + "/User/login";
        System.out.println("启动后访问主页：" + url);
        try {
            System.setProperty("java.awt.headless", "false");
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));//自动打开浏览器
        } catch (Exception e) {
            System.out.println("自动打开浏览器失败");
        }

    }
}
