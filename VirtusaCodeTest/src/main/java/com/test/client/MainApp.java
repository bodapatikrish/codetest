package com.test.client;

import java.util.Scanner;

import com.test.service.IService;
import com.test.service.Service;

public class MainApp {
	public static void main(String[] args) {
		System.out.println("Please Enter Number :");
		Scanner scanner = new Scanner(System.in);
		IService iService = new Service();
		int n = scanner.nextInt();
		try {
			String words = iService.convert(n);
			System.out.println(n + " = " + words);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		scanner.close();
	}
}
