package com.nowcoder.community.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xindong
 * @create 2022-05-17 16:57
 * 使用日志统一记录访问service层的请求
 */
@Component
@Aspect
public class ServiceLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 日志格式：用户[1.2.3.4],在[xxx],访问了[com.nowcoder.community.service.xxx()].
        // 利用工具类获取到当前的request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        //如果不是controller调用service的话是拿不到attributes的(没有请求)，attributes==null，产生空指针异常
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            String ip = request.getRemoteHost();
            String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // 得到类名和方法名
            String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

            // 格式化字符串加入日志
            logger.info(String.format("用户[%s]，在[%s]，访问了[%s]。", ip, now, target));
        }
    }


}
