package com.jww.common.redis.aspect;

import com.jww.common.core.base.BaseModel;
import com.jww.common.core.exception.BusinessException;
import com.jww.common.redis.util.CacheUtil;
import com.xiaoleilu.hutool.util.ArrayUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 分布式锁切面处理类
 *
 * @author shadj
 * @date 2017/12/11 14:17
 */
@Slf4j
@Aspect
@Component
@Order(0)
public class DistributedLockAspect {

    public DistributedLockAspect(){
        log.info("---------------DistributedLockAspect()----------------------");
    }

    @Pointcut("@annotation(com.jww.common.core.annotation.DistributedLock)")
    public void pointCut(){}

    @Around("pointCut()")
    public void doAround(ProceedingJoinPoint pjp){
        String paramStr = getParamStr(pjp);
        String lockName = CacheUtil.getLockKey(paramStr, pjp.getSignature().getDeclaringType().getClass());
        String lockValue = CacheUtil.getCache().lock(lockName);
        try {
            if (StrUtil.isBlank(lockValue)){
                log.error("获取分布式锁失败，lockName ：{}",lockName);
                throw new BusinessException("获取分布式锁失败，lockName ：" + lockName);
            }
            try {
                Object result = pjp.proceed();
            } catch (Throwable throwable) {
                log.error("方法执行失败",throwable);
                throw new BusinessException(throwable);
            }
        } finally {
            try{
                CacheUtil.getCache().unlock(lockName,lockValue);
            } catch (Throwable throwable){
                log.error("分布式锁解锁失败，lockName ：{}",lockName);
            }
        }
    }

    /**
     * 获取参数串，BaseModel取ID，其他转换为String，使用下划线分隔拼接
     *
     * @param  pjp
     * @return java.lang.String
     * @author shadj
     * @date 2017/12/11 16:08
     */
    private String getParamStr(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        String delimiter = "_";
        for (Object arg : args){
            if(arg instanceof BaseModel){
                BaseModel param0BaseModel = (BaseModel) arg;
                str.append(String.valueOf(param0BaseModel.getId()))
                    .append(delimiter);
            } else {
                str.append(String.valueOf(arg))
                .append(delimiter);
            }
        }
        String ret = str.toString();
        if(ret.endsWith(delimiter)){
            ret = ret.substring(0,ret.length()-1);
        }
        return str.toString();
    }

}
