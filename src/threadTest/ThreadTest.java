package threadTest;

public class ThreadTest implements Runnable {

	static Object object1 = new Object(), object2 = new Object();
	int flag;

	public static void main(String[] args) {
		ThreadTest thread1 = new ThreadTest();
		ThreadTest thread2 = new ThreadTest();
		thread1.flag = 1;
		thread2.flag = 0;
		new Thread(thread1).start();
		new Thread(thread2).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (flag == 1) {
			synchronized (object1) {
				try {
					System.out.println("持有对象object1");
					Thread.sleep(200);// 休眠时间
					synchronized (object2) {
						System.out.println(Thread.currentThread().getName() + ":" + flag);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		if (flag == 0) {
			synchronized (object2) {
				try {
					System.out.println("持有对象object2");
					Thread.sleep(200);
					synchronized (object1) {
						System.out.println(Thread.currentThread().getName() + ":" + flag);
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
