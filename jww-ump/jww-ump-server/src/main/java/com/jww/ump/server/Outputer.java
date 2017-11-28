package com.jww.ump.server;

import com.jww.common.redis.util.CacheUtil;

/**
 * @author shadj
 * @description: TODO
 * @date 2017/11/27 16:55
 */
public class Outputer {
    private static final String LOCK_NAME = "Outputer";

    public void output(String name) {
        String lockValue = CacheUtil.getCache().lock(LOCK_NAME);
        try{
            for(int i=0; i<name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println("==========================");
        }finally {
            CacheUtil.getCache().unlock(LOCK_NAME,lockValue);
        }
    }
}
