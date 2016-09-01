package test;

import java.security.Security;

public class Test {
	/**
	 * 找出系统支持的消息摘要算法
	 */
	public static void main(String[] args) {
		System.out.println("---------系统支持的消息摘要算法有--------");
		for (String s : Security.getAlgorithms("MessageDigest")) {
			System.out.println(s);
		}
	}

}
