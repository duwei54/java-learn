package com.duwei.thread;

/**
 * 多线程演示
 */
public class ThreadDemo {

	static int counter1 = 0;

	public static void main(String[] args) {

		Counter counter = new Counter();
		Thread thread1 = new Thread(new Thread1(counter));

		Thread thread2 = new Thread(new Thread2(counter));

		Thread thread3 = new Thread(new Runnable() {


			@Override
			public void run() {
				while(true){
				ThreadDemo.counter1 ++;
				System.out.println("thread3 print:" + ThreadDemo.counter1);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		});

		Thread thread4 = new Thread(new Runnable() {


			@Override
			public void run() {
				while(true){
					if(ThreadDemo.counter1 >0){
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ThreadDemo.counter1 --;
					}
					System.out.println("thread4 print:" + ThreadDemo.counter1);
				}
			}
		});

		thread1.start();
		thread2.start();

//		thread3.start();
//		thread4.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}
		System.exit(0);
	}
}

class Thread1 implements Runnable {
	Counter counter;
	public Thread1(Counter counter) {
		this.counter = counter;
	}


	@Override
	public void run() {
		while (true) {
			counter.add();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Thread2 implements Runnable {
	Counter counter;
	public Thread2(Counter counter) {
		this.counter = counter;
	}
	@Override
	public void run() {
		while (true) {
			counter.decrease();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Counter {

	int num;

	public synchronized void  add() {
//		synchronized (this) {
			this.num += 1;
			System.out.println("Counter ++ :" + this.num);
//		}
	}

	public void decrease() {
		if (this.num > 1) {
			this.num--;
			System.out.println("Counter -- :" + this.num);
		}
	}
}


