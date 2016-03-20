package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SellTickets implements Runnable {

	private final static int tickets = 50;
	private static int ticket = tickets;
	static int count = 0;

	public synchronized void sail() {

		if (ticket > 0) {
			ticket--;
			count = tickets - ticket;
			System.out.println("当前票数是:" + ticket + "售出" + count + "线程名：" + Thread.currentThread().getName());

		}
	}

	public void run() {
		while (true) {
			// synchronized (this) {
			if (ticket > 0) {
				try {
					sail();
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				break;
			}
			// }
		}
	}

	public static void main(String[] args) 
	{
		SellTickets r = new SellTickets();
		ExecutorService pool = Executors.newFixedThreadPool(3);
		//ExecutorService pool = Executors.newCachedThreadPool();
		Thread thread = new Thread(r);
		for (int i = 0; i < 5; i++) 
		{
			pool.execute(thread);
		}
		pool.shutdown();
		
		// Thread t3 = new Thread(r, "t3");
		// Thread t4 = new Thread(r, "t4");
		// Thread t2 = new Thread(r, "t2");
		// t2.start();
		// t1.start();
		// t3.start();
		// t4.start();
	}
}