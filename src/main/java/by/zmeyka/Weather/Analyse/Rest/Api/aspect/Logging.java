package by.zmeyka.Weather.Analyse.Rest.Api.aspect;

import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherService;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Log4j
@Aspect
@Component
public class Logging {

    private final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Before("execution(* by.zmeyka.Weather.Analyse.Rest.Api.*.* (..))")
    public void logBeforeStartApi(JoinPoint joinPoint) {
        logger.info("ENTERING API: ", joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName());
    }



    @Before("execution(* by.zmeyka.Weather.Analyse.Rest.Api.controller.*.* (..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("ENTERING method of CONTROLLER: {} with arguments: {} ,Class Name:{}", joinPoint.getSignature().getName(), joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());
    }

    @AfterReturning(pointcut = "execution(* by.zmeyka.Weather.Analyse.Rest.Api.controller.*.* (..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("EXIT method: {} with result: {} ,Class Name:{}", joinPoint.getSignature().getName(), result, joinPoint.getSignature().getDeclaringTypeName());
    }


}
