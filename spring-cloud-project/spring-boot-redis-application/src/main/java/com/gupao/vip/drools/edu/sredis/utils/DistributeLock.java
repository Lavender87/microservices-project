package com.gupao.vip.drools.edu.sredis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

public class DistributeLock {

    //获得锁

    /**
     *
     * @param lockName 锁名称
     * @param acquireTimeout 获得锁的超时时间
     * @param lockTimeout 所本身过期时间
     * @return
     */
    public String acquireLock(String lockName,long acquireTimeout,long lockTimeout){
        String identifier = UUID.randomUUID().toString();
        String lockKey = "lock:"+lockName;
        int lockExpire = (int)(lockTimeout/1000);
        Jedis jedis = null;
        try{
            jedis = JedisConnectionUtils.getJedis();
            long end  = System.currentTimeMillis()+acquireTimeout;
            while(System.currentTimeMillis()<end){
                if(jedis.setnx(lockKey,identifier)==1){
                    jedis.expire(lockKey,lockExpire);//获取锁之后，设置超时时间
                    return identifier;

                }
                //(TTL, time to live)以秒为单位，返回给定 key 的剩余生存时间
                if(jedis.ttl(lockKey)==-1){
                    jedis.expire(lockKey,lockExpire);//设置超时时间
                }

                //等待片刻，保证不是失败后马上又去获取锁，没有意义
                Thread.sleep(100);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }

        return null;
    }


    //释放锁
    public boolean releaseLock(String lockName,String identifier){
        System.out.println(lockName+"开始释放锁:"+identifier);
        boolean isRelease = false;
        String lockKey = "lock:"+lockName;
        Jedis jedis = null;
        try{
            jedis = JedisConnectionUtils.getJedis();
            while(true){
                jedis.watch(lockKey);
                //判断是否是同一把锁
                if(identifier.equals(jedis.get(lockKey))){
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    if(transaction.exec().isEmpty()){
                        continue;
                    }

                    isRelease = true;
                }
                jedis.unwatch();
                break;
            }
        }finally {
            jedis.close();
        }

        return isRelease;
    }


    public boolean releaseLockWithdLua(String lockName,String identifier){
        String lockKey = ""+lockName;
        String lua = "if redis.call(\"get\",KEYS[1])==ARGV[1] then " +
                " return redis.call(\"del\",KEYS[1]) " +
                "else return 0 end";
        Jedis jedis = JedisConnectionUtils.getJedis();
        Long rs = (Long)jedis.eval(lua,1,new String[]{lockKey,identifier});
        if(rs.intValue()>0)return true;
        return false;
    }
}
