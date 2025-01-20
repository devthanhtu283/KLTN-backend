package com.demo.helpers;

import java.util.UUID;

public class RandomHelper {

	
	public static String random() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
