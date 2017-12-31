package com.jww.common.redis.aspect;

import com.jww.common.core.annotation.DistributedLock;
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
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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

    @Pointcut("@annotation(com.jww.common.core.annotation.DistributedLock)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object result = null;
        /*
        如果注释中配置了key(支持SpEL表达式)，则lockName的规则为：lock:{cacheName}：{key}
        否则，lockName的规则为：lock:{cacheName}：{param0.id}_{param1.id}
         */
        String baseKey = getBaseKey(pjp);
        log.debug("获取基础key完成,baseKey ：{}", baseKey);
        String lockName = CacheUtil.getLockKey(baseKey, pjp.getTarget().getClass());
        log.debug("获取分布式锁完成，lockName ：{}", lockName);
        String lockValue = CacheUtil.getCache().lock(lockName);
        try {
            if (StrUtil.isBlank(lockValue)) {
                log.error("获取分布式锁失败，lockName ：{}", lockName);
                throw new BusinessException("获取分布式锁失败，lockName ：" + lockName);
            }
            log.info("获取分布式锁成功，lockName ：{}，lockValue : {}", lockName, lockValue);
            try {
                result = pjp.proceed();
            } catch (BusinessException e) {
                log.error("方法执行失败", e);
                throw e;
            } catch (Throwable throwable) {
                log.error("方法执行失败", throwable);
                throw new BusinessException(throwable);
            }
        } finally {
            try {
                CacheUtil.getCache().unlock(lockName, lockValue);
                log.info("释放分布式锁成功，lockName ：{}，lockValue : {}", lockName, lockValue);
            } catch (Throwable throwable) {
                log.error("分布式锁解锁失败，lockName ：{}", lockName);
            }
        }
        return result;
    }

    /**
     * 获取基础key
     * 如果参数为空，返回空串
     * 如果注释中配置了key(支持SpEL表达式)，则解析key表达式，并返回
     * 否则，从参数中取ID，循环各参数，BaseModel取ID，其他转换为String，使用下划线分隔拼接
     *
     * @param pjp
     * @return java.lang.String
     * @author shadj
     * @date 2017/12/11 16:08
     */
    private String getBaseKey(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        if (ArrayUtil.isEmpty(args)) {
            return "";
        }
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        DistributedLock distributedLock = signature.getMethod().getAnnotation(DistributedLock.class);
        String key = distributedLock.key();
        //优先取key，如果为空取value
        if (StrUtil.isEmpty(key)) {
            key = distributedLock.value();
        }
        if (StrUtil.isNotEmpty(key)) {
            return parserKey(signature, key, args);
        }

        StringBuilder str = new StringBuilder();
        String delimiter = "_";
        for (Object arg : args) {
            if (arg instanceof BaseModel) {
                BaseModel param0BaseModel = (BaseModel) arg;
                str.append(String.valueOf(param0BaseModel.getId()))
                        .append(delimiter);
            } else {
                str.append(String.valueOf(arg))
                        .append(delimiter);
            }
        }
        String ret = str.toString();
        if (ret.endsWith(delimiter)) {
            ret = ret.substring(0, ret.length() - 1);
        }
        return str.toString();
    }

    /**
     * 解析分布式锁key
     * key 定义在注解上，支持SPEL表达式
     *
     * @param signature
     * @param key
     * @param args
     * @return
     */
    private String parserKey(MethodSignature signature, String key, Object[] args) {
        if (ArrayUtil.isEmpty(args)) {
            return "";
        }
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(signature.getMethod());
        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

}
