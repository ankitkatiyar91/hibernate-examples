package com.test.persist;

public class SimpleTest {
	public static void main(String[] args) {
		way1();
		way2();
	}

	private static void way2() {
		String s = "abc";
		System.out.println("String: "
				+ new String(new StringBuffer(s).reverse()));
	}

	private static void way1() {
		String s = "abc";
		char[] ch = s.toCharArray();
		char[] ch2 = new char[ch.length];
		int i = ch.length - 1;
		for (char c : ch) {
			ch2[i] = c;
			i--;
		}
		System.out.println("String: " + new String(ch2));
	}
}
