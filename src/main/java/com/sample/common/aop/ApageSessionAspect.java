package com.sample.common.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApageSessionAspect {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    // 어디서 관여할 것인지
    // execution을 이용하여 추가
    // 예제는 apage 폴더 내 Controller에 대해서 접근한다는 의미
    @Pointcut("execution(public * com.sample.apage..*Controller.*(..))")
    private void apageSessionAspect() {
    }

    @Around(value = "apageSessionAspect() ")
    public Object apageSessionInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = null;
        HttpServletResponse response = null;

        String[] exceptionUrl = { "/WEB-INF/", "/apage.do", "/apage/adminLoginJson.do" };

        for (Object o : joinPoint.getArgs()) {
            if (o instanceof HttpServletRequest) {
                request = (HttpServletRequest) o;
            } else if (o instanceof HttpServletResponse) {
                response = (HttpServletResponse) o;
            }
        }
        // controller에 request, response 가 없을 경우
        if (request == null || response == null) {
            return joinPoint.proceed();
        }

        String getUrl = request.getRequestURI();

        // 세션 예외 URL
        for (int i = 0; i < exceptionUrl.length; i++) {

            if (getUrl.indexOf(exceptionUrl[i]) > -1) {
                return joinPoint.proceed();
            }
        }

        System.out.println("ip >>> " + getClientIpAddr(request));

        return joinPoint.proceed();
    }

    private String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}