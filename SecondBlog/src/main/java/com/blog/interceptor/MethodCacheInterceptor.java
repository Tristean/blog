package com.blog.interceptor;

import com.blog.util.RedisUtil;
import javafx.beans.binding.ObjectExpression;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MethodCacheInterceptor implements MethodInterceptor {
    private Logger logger= LoggerFactory.getLogger(MethodCacheInterceptor.class);
    private RedisUtil redisUtil;
    private List<String> targetNameList;
    private List<String> methodNameList;
    private Long defaultCacheExpireTime;
    private Long xxxRecordManagerTime;
    private Long xxxSetRecordManagerTime;

    private MethodCacheInterceptor(){
        try{
            //分割字符串
            String[]targetNames={};
            String[]methodNames={};

            //加载过期时间设置
            defaultCacheExpireTime=3600L;
            xxxRecordManagerTime=60L;
            xxxSetRecordManagerTime=60L;
            //创建list
            targetNameList=new ArrayList<String>(targetNames.length);
            methodNameList=new ArrayList<String>(methodNames.length);
            Integer maxLen=targetNames.length>methodNames.length?targetNames.length
                            :methodNames.length;
            for (int i=0;i<maxLen;i++){
                if(i<targetNames.length){
                    targetNameList.add(targetNames[i]);
                }
                if(i<methodNames.length){
                    methodNameList.add(methodNames[i]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object value=null;

        String targetName=invocation.getThis().getClass().getName();
        String methodName=invocation.getMethod().getName();

        if(!isAddCache(targetName,methodName)){

            return invocation.proceed();
        }


        Object[]arguements=invocation.getArguments();
        String key=getCacheKey(targetName,methodName,arguements);
        logger.debug("rdisKey:"+key);
        try{
            if(redisUtil.exists(key)){
                return redisUtil.get(key);
            }
            //写入缓存
            value=invocation.proceed();
            if (value!=null){
                final  String tkey=key;
                final Object tvalue=value;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (tkey.startsWith("com.srevice.imp.xxxRecordManager")){
                            redisUtil.set(tkey,tvalue,xxxRecordManagerTime);
                        }
                        else if(tkey.startsWith("com.service.imp.xxxSetRecordManager")){
                            redisUtil.set(tkey,tvalue,xxxSetRecordManagerTime);
                        }
                        else{
                            redisUtil.set(tkey,tvalue,defaultCacheExpireTime);
                        }
                    }
                }).start();
            }
        }catch (Exception e){
            e.printStackTrace();
            if (value==null){
                return invocation.proceed();
            }
        }
        System.out.println("cache successful");
        return value;
    }

    private String getCacheKey(String targetName, String methodName, Object[] arguements) {
        StringBuffer sbu=new StringBuffer();
        sbu.append(targetName).append("_").append(methodName);
        if((arguements!=null)&&(arguements.length!=0)){
            for (int i=0;i<arguements.length;i++){
                sbu.append("_").append(arguements[i]);
            }
        }
        return sbu.toString();
    }

    private boolean isAddCache(String targetName, String methodName) {
        boolean flag=true;
        if (targetNameList.contains(targetName)||methodNameList.contains(methodName)){
            return false;
        }
        return flag;
    }

    public void setRedisUtil(RedisUtil redisUtil){
        this.redisUtil=redisUtil;
    }
}
