package com.web.app.upbit.util;

public class NumberHelper {
	public double doubleDotdouble(double d, int n) {		
		return Math.floor(d * Math.pow(10, n))/Math.pow(10, n);
	}
}
