package com.example.romanyuk_lab4;

import java.util.Scanner;

import static com.example.romanyuk_lab4.GMD.calculate;

public class Lab4Application {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the first number: ");
		int first = scanner.nextInt();
		System.out.print("Enter the second number: ");
		int second = scanner.nextInt();

		System.out.print("Would you like to see the steps of calculation? (y/n): ");
		String ans = scanner.next();

		int result = calculate(first, second, ans);
		System.out.println("Greatest common multiple of " + first + " and " + second + " is " + result);

	}

}
