package com.leasewithease.rest.authentication;

import org.springframework.util.DigestUtils;

public class MD5 {
	public static String hashString(String plainText) {
		byte[] digest = DigestUtils.md5Digest(plainText.getBytes());
		return DigestUtils.md5DigestAsHex(digest);
	}
}