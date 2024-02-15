package by.itacademy.environment.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static by.itacademy.environment.util.Constants.*;

@Aspect
@Component
@Slf4j
public class AspectLogger {

    @Pointcut(CONTROLLERS_POINTCUT)
    public void pointCut() {
    }

    @Before(POINTCUT)
    public void logRequest(JoinPoint joinPoint) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info(LOG_REQUEST_PATTERN, request.getMethod(), joinPoint.getSignature().toShortString(), request.getRequestURI());

    }

    @AfterReturning(value = POINTCUT, returning = RESPONSE)
    public void logAfter(JoinPoint joinPoint, Object response) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info(LOG_RESPONSE_PATTERN, request.getMethod(), joinPoint.getSignature().toShortString(), response);
    }

    @AfterThrowing(value = POINTCUT, throwing = EXCEPTION)
    public void logException(JoinPoint joinPoint, Exception exception) {
        log.error(LOG_EXCEPTION_PATTERN, joinPoint.getSignature(), joinPoint.getArgs(), exception.toString());
    }
}
