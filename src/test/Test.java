package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test implements Runnable {
	
	String str = "R+X+Y+Z";
	static Boolean flag = true;
	static int count = 0;
	public static void main(String[] args) throws Exception {
		Test test1 = new Test();// 只是实例
		// Test test2 = new Test();
		// Test test3 = new Test();
		// Test test4 = new Test();
		// new Thread(test1).start();
		// new Thread(test2).start();
		// new Thread(test3).start();
		// new Thread(test4).start();
//		ExecutorService pool = Executors.newFixedThreadPool(2);
		ExecutorService pool = Executors.newCachedThreadPool();
		Thread t1 = new Thread(test1);
		for(int i=0;i<10;i++){
			pool.execute(t1);
			count++;
		}
		pool.shutdown();
		
		while (true) {
			if (pool.isTerminated()) {
				System.out.println("结束了！");
				break;
			}
			Thread.sleep(200);
		}
		System.out.println(count);
		// Thread t2 = new Thread(test2);
		// pool.execute(t1);
		// pool.execute(t2);
		// Thread.sleep(2000);
	}

	@Override
	public void run() 
	{
		// 对资源加锁
		synchronized (this) { 
			while (flag) {
				String str1 = null;
				if (str.contains("+")) {
					str1 = str.split("\\+",2)[0];
					str = str.split("\\+",2)[1]; 
				} else {
					str1 = str;
					flag = false;
				}
				System.out.println(Thread.currentThread().getName() + "→" + str1);
			}
		}
	}
}
