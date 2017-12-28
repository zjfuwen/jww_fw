package com.jww.ump.server.aspect;

import com.alibaba.fastjson.JSON;
import com.jww.common.core.Constants;
import com.jww.common.web.model.ResultModel;
import com.jww.ump.model.UmpLogModel;
import com.jww.ump.model.UmpUserModel;
import com.jww.ump.rpc.api.UmpLogService;
import com.jww.ump.server.annotation.SysLogOpt;
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
 * 日志入库切面
 *
 * @author RickyWang
 * @date 2017/12/27 13:56
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

    UmpUserModel crrentUser = null;

    @Pointcut("execution(* *..controller..*.*(..)) && @annotation(com.jww.ump.server.annotation.SysLogOpt)")
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
            if(!isQueryType && logAfter(result)){
                logService.add(umpLogModel);
            }
        }
        return result;
    }


    private boolean logPre(ProceedingJoinPoint pjp) throws Exception{
        crrentUser = (UmpUserModel) SecurityUtils.getSubject().getPrincipal();
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
                    operation = method.getAnnotation(SysLogOpt.class).value();
                    //操作类型
                    operationType = method.getAnnotation(SysLogOpt.class).operationType().value();
                    break;
                }
            }
        }
        //查询类型不添加日志
        if(operationType==Constants.LogOptEnum.UNKNOW.value()||operationType==Constants.LogOptEnum.QUERY.value()){
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
        //方法名称
        umpLogModel.setOperation(operation);
        //操作类型
        umpLogModel.setOperationType(operationType);
        return isQueryType;
    }


    private boolean logAfter(Object result) {
        boolean hasLogin = false;
        ResultModel response = null;
        if(result!=null){
            response = (ResultModel)result;
        }
        if(crrentUser==null){
            crrentUser = (UmpUserModel) SecurityUtils.getSubject().getPrincipal();
        }
        if(crrentUser!=null){
            umpLogModel.setUserName(crrentUser.getUserName());
            hasLogin = true;
        }else{
            return hasLogin;
        }
        //返回结果
        if(response!=null && response.code == Constants.ResultCodeEnum.SUCCESS.value()){
            umpLogModel.setResult(1);
        }else{
            umpLogModel.setResult(0);
        }
        //执行时长(毫秒)
        Long spendTime = System.currentTimeMillis() - startTime;
        umpLogModel.setTime(spendTime);
        return hasLogin;
    }
}
