package com.org.tests.purchasedashboarddata;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

public class GetMethods {

@Test
public void getMethod()
	{

		Method[] methods = Create_PO_Data_Opt.class.getDeclaredMethods();
		int nMethod = 1;
		System.out.println("1. List of all methods of Person class");
		for (Method method : methods) {
			System.out.printf("%d. %s", ++nMethod, method);
			System.out.println();
		}
		System.out.printf("%d. End - all  methods of Person class", ++nMethod);
	}

}
