package com.java.util.secret;

public class TestRSA {
	
	public static void main(String[] args) throws Exception {
		
		// testRSA();
		String a = RSAUtil.encrypt("laibai", RSAUtil.PUBLIC_KEY_STR);
		String b = RSAUtil.decrypt(a, RSAUtil.PRIVATE_KEY_STR);
		System.out.println(a);
		System.out.println(b);
		
	}
 
}
