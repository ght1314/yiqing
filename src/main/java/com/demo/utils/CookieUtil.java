package com.demo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieUtil {


    /**
     * 设置cookie
     */
    public static void  setCookie(String key, String value, String domain,
                                  HttpServletResponse response) {

    }
    /**
     * 查找cookie
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies= request.getCookies();
        Cookie cookie = null;
        if (cookies != null)
        {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(key))
                {
                    cookie = cookies[i];
                }
            }
        }
        if (cookie != null)
        {
            try {
                return URLDecoder.decode(cookie.getValue(),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


}
