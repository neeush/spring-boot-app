package com.neeush.springappExpenseManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyPasswordGenerator {

	public static void main(String[] args) {
		
		
		String encoded=new BCryptPasswordEncoder().encode("password@123");
		System.out.println(encoded);

	}

}
