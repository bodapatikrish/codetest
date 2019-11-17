package com.test.service;

import java.text.DecimalFormat;

import com.test.exception.MyException;
import com.test.uitl.NumberToEnglishWords;

public class Service implements IService {

	@Override
	public String convert(int n) throws MyException {
		if (n < 0) {
			throw new MyException("Negative Numbers Not Allowed");
		}
		if (n == 0) {
			return "zero";
		}

		String stringnumber = Long.toString(n);

		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		stringnumber = df.format(n);

		int billions = Integer.parseInt(stringnumber.substring(0, 3));
		int millions = Integer.parseInt(stringnumber.substring(3, 6));
		int hundredThousands = Integer.parseInt(stringnumber.substring(6, 9));
		int thousands = Integer.parseInt(stringnumber.substring(9, 12));

		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + " billion ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + " billion ";
		}
		String result = tradBillions;

		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "one thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " thousand ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;

		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");

	}

	private String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = NumberToEnglishWords.numNames[number % 100];
			number /= 100;
		} else {
			soFar = NumberToEnglishWords.numNames[number % 10];
			number /= 10;

			soFar = NumberToEnglishWords.tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		return NumberToEnglishWords.numNames[number] + " hundred" + soFar;
	}

}
