/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom2.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author phamt
 */
public class EmailFormatUtils {
    private static Pattern pattern;
	private Matcher matcher;
	private static final String email = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

	public EmailFormatUtils() {
		pattern = Pattern.compile(email);
	}

	public boolean validate_Email(String regex) {
		matcher = pattern.matcher(regex);
		return matcher.matches();
	}
}
