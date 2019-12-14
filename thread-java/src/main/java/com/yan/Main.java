package com.yan;

import com.yan.util.concurrent.ExecutorService;
import com.yan.util.concurrent.Executors;
import com.yan.util.concurrent.TimeUnit;

public class Main extends Thread {
    private final Object object = new Object();
    private int count;

    @Override
    public void run() {
        synchronized (object) {
            while (count < 100) {
                System.out.println("Thread name: " + Thread.currentThread().getName() + ", count:" + (count++));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> System.out.println("hello"));
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        Main main = new Main();
        new Thread(main, "main1").start();
        new Thread(main, "main2").start();
        new Thread(main, "main3").start();
        new Thread(main, "main4").start();
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            res[0] = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }
}