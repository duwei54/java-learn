package com.duwei.demo.thread;

public class AccountTest {
	private static int THREADNUM = 10;
	private static Thread[] threads = new Thread[THREADNUM];

	public static void main(String[] args) {

		final BankAccount ac = new BankAccount("张三", 2000);
		for (int i = 0; i < THREADNUM; i++) {
			threads[i] = new Thread(() -> {

				synchronized (ac) {
					ac.putin(100);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ac.getout(100);
				}
			});
			threads[i].start();
		}
		for (int i = 0; i < THREADNUM; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("账户:" + ac.getName() + "  \n余额为:" + ac.getBalance());
	}
}
