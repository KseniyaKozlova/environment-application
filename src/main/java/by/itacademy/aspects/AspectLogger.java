package by.itacademy.aspects;

import by.itacademy.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class AspectLogger {

    @Pointcut(Constants.CONTROLLERS_POINTCUT)
    public void pointCut() {
    }

    @Before(Constants.POINTCUT)
    public void logRequest(JoinPoint joinPoint) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info(Constants.LOG_REQUEST_PATTERN, request.getMethod(), joinPoint.getSignature().toShortString(), request.getRequestURI());

    }

    @AfterReturning(value = Constants.POINTCUT, returning = Constants.RESPONSE)
    public void logAfter(JoinPoint joinPoint, Object response) {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info(Constants.LOG_RESPONSE_PATTERN, request.getMethod(), joinPoint.getSignature().toShortString(), response);
    }

    @AfterThrowing(value = Constants.POINTCUT, throwing = Constants.EXCEPTION)
    public void logException(JoinPoint joinPoint, Exception exception) {
        log.error(Constants.LOG_EXCEPTION_PATTERN, joinPoint.getSignature(), joinPoint.getArgs(), exception.toString());
    }
}
