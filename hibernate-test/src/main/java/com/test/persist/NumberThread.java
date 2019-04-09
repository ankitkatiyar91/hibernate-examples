package com.test.persist;

public class NumberThread {

	public static void main(String[] args) {
		new NumberThread().testThreads();
	}

	private boolean isEvenPrinted = true;

	public void testThreads() {
		System.out.println("Starting all threads");
		Object obj = new Object();

		Thread odd = new Thread(() -> {
			int i = 1;
			while (i < 100) {
				synchronized (this) {
					if (!isEvenPrinted) {
						try {
							wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println(i);
					i += 2;
					isEvenPrinted = false;
					notify();
				}
			}
		});

		Thread even = new Thread(() -> {
			int i = 2;
			while (i <= 100) {
				synchronized (this) {
					if (isEvenPrinted) {
						try {
							wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					System.out.println(i);
					i += 2;
					this.isEvenPrinted = true;
					notify();
				}
			}
		});

		odd.start();
		even.start();
	}

}
