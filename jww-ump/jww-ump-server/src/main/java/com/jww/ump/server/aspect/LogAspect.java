package com.jww.ump.server.aspect;

import com.alibaba.fastjson.JSON;
import com.jww.common.core.Constants;
import com.jww.common.web.model.ResultModel;
import com.jww.ump.model.UmpLogModel;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpLogService;
import com.jww.ump.server.annotation.LogData;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 系统日志，切面处理类
 *
 * @author wanyong
 * @date 2017/11/16 17:04
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    /**
     * 开始时间
     */
    private long startTime = 0L;

    @Autowired
    private UmpLogService logService;

    private UmpLogModel umpLogModel = new UmpLogModel();

    @Pointcut("execution(* *..controller..*.*(..)) && @annotation(com.jww.ump.server.annotation.LogData)")
    public void webLogPointCut() {

    }

    @Around("webLogPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        startTime = System.currentTimeMillis();
        Object result = null;
        boolean isQueryType = false;
        isQueryType = logPre(pjp);
        try {
            result = pjp.proceed();
        } finally {
            //查询类型不添加日志
            if(!isQueryType){
                logAfter(result);
                logService.add(umpLogModel);
            }
        }
        return result;
    }


    private boolean logPre(ProceedingJoinPoint pjp) throws Exception{
        boolean isQueryType = false;
        //操作类型
        Integer operationType = null;
        //方法名称
        String operation = null;
        for (Method method : Class.forName(pjp.getTarget().getClass().getName()).getMethods()) {
            if (method.getName().equals(pjp.getSignature().getName())) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == pjp.getArgs().length) {
                    //方法名称
                    operation = method.getAnnotation(LogData.class).value();
                    //操作类型
                    operationType = method.getAnnotation(LogData.class).operationType();
                    break;
                }
            }
        }
        //查询类型不添加日志
        if(operationType==Constants.LOG_OPERATION_TYPE_UNKONW||operationType==Constants.LOG_OPERATION_TYPE_QUERY){
            return true;
        }
        //获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        startTime = System.currentTimeMillis();
        String ip = HttpUtil.getClientIP(request);
        //方法名含包名（com.jww.ump.server.controller.SysLogController.queryListPage）
        String classMethod = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
        //请求参数
        String args = JSON.toJSONString(pjp.getArgs());
        umpLogModel.setIp(ip);
        umpLogModel.setMethod(classMethod);
        umpLogModel.setParams(args);
        umpLogModel.setCreateTime(new Date());
        umpLogModel.setCreateBy(0L);
        umpLogModel.setUpdateBy(0L);
        UmpUserModel crrentUser = (UmpUserModel) SecurityUtils.getSubject().getPrincipal();
        if(crrentUser!=null){
            umpLogModel.setUserName(crrentUser.getUserName());
        }
        //方法名称
        umpLogModel.setOperation(operation);
        //操作类型
        umpLogModel.setOperationType(operationType);
        return isQueryType;
    }


    private void logAfter(Object result) {
        ResultModel response = JSON.parseObject(JSON.toJSONString(result), ResultModel.class);
        //返回结果
        if(response!=null && response.code == Constants.ResultCodeEnum.SUCCESS.value()){
            umpLogModel.setResult(1);
        }else{
            umpLogModel.setResult(0);
        }
        //执行时长(毫秒)
        Long spendTime = System.currentTimeMillis() - startTime;
        umpLogModel.setTime(spendTime);
    }
}