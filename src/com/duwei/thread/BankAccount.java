package com.duwei.thread;

public class BankAccount {
	private String name;
	private int money;
	public BankAccount(String name, int money) {
		this.name = name;
		this.money = money;
	}

	public String getName() {
		return name;
	}
	public int getBalance() {
		return money;
	}

	public  void putin(int money){
//		int temp = this.money;
//		temp += money;
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		this.money = temp;
		this.money += money;
	}

	public  void getout(int money){
//		int temp = this.money;
//		temp -= money;
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		this.money = temp;

		this.money -= money;
	}
}
