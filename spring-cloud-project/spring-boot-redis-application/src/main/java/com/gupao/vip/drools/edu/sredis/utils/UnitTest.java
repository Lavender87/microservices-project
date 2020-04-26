package com.gupao.vip.drools.edu.sredis.utils;

public class UnitTest extends Thread {

    @Override
    public void run() {
        while (true){
            DistributeLock distributeLock = new DistributeLock();
            String rs = distributeLock.acquireLock("updateOrder",2000,5000);
            if(rs!=null){
                System.out.println(Thread.currentThread().getName()+"成功获取锁:"+rs);
                try {
                    Thread.sleep(1000);
//                    distributeLock.releaseLock("updateOrder",rs);
                    distributeLock.releaseLockWithdLua("updateOrder",rs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        UnitTest unitTest = new UnitTest();
        for(int i=0;i<10;i++){
            new Thread(unitTest,"tName:"+i).start();
        }
    }
}
