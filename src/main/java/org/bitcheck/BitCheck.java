package org.bitcheck;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class BitCheck {

	public static void main(String[] args) {
		
		Security.addProvider(new BouncyCastleProvider());
		
		System.out.println("BitCheck");
		
	}
	
}
