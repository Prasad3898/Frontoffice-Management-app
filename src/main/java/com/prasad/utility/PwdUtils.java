package com.prasad.utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
@Component
public class PwdUtils {
	//generate random pwd in java 
	public static String randomPwd() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random(6, characters);
		return pwd;
	}
}
