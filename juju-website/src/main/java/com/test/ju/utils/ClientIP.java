package com.test.ju.utils;

import javax.servlet.http.HttpServletRequest;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClientIP {
	
	public static String getClientIP(HttpServletRequest request) {//static 메서드 메서드 클래스로 직접 접근사용가능

		String ip = request.getHeader("X-Forwarded-For");//header를 통해서 다른 아이피도 가져옴?

		log.info(">>>> X-FORWARDED-FOR : " + ip);

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info(">>>> Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            log.info(">>>> WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.info(">>>> HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {//구글 클라우드 오면 이것만 하면 됨?
            ip = request.getRemoteAddr();//아이피 주소 가져오는거?
        }

        log.info(">>>> Result : IP Address : "+ip);

        return ip;

	}
	
}
