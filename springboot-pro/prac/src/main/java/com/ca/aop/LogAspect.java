package com.ca.aop;

import com.alibaba.fastjson.JSONObject;
import com.ca.mapper.OperateLogMapper;
import com.ca.pojo.OperateLog;
import com.ca.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect//切面类
public class LogAspect {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.ca.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //操作人的ID
        //获取请求头当中的JWT令牌，解析令牌
        String jwt = httpServletRequest.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operatorUser = (Integer)claims.get("id");
        //操作时间
        LocalDateTime operatorTime=LocalDateTime.now();
        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法
        String methodName = joinPoint.getSignature().getName();
        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);


        long begin=System.currentTimeMillis();
        //调用原始目标运行
        Object result= joinPoint.proceed();
        long end=System.currentTimeMillis();


        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime=end-begin;
        //记录操作日志
        OperateLog operateLog=new OperateLog(null,operatorUser,operatorTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP的记录日志：{}",operateLog);
        return result;
    }
}
