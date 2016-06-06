package xp.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

	/* OPERATIONS */
	public static double add(double i, double j) {
		CalculatorCommand addCommand = new Add();
		return addCommand.execute(i, j);
	}

	public static double subtract(double i, double j) {
		CalculatorCommand subtractCommand = new Subtract();
		return subtractCommand.execute(i, j);
	}

	public static double multiply(double i, double j) {
		CalculatorCommand multiplyCommand = new Multiply();
		return multiplyCommand.execute(i, j);
	}

	public static double divide(double i, double j) {
		CalculatorCommand divideCommand = new Divide();
		return divideCommand.execute(i, j);
	}

	/*  */
	public static boolean isOperand(String operand) {
		boolean isOperand = true;

		try {
			double number = Double.parseDouble(operand);
		} catch (NumberFormatException e) {
			isOperand = false;
		}

		return isOperand;
	}

	public static boolean isOperator(String n1) {
		boolean isOperator = false;

		if (n1 == "+" || n1 == "-") {
			isOperator = true;
		}

		return isOperator;
	}

	public static boolean isInputOrderValid(String[] s) {
		boolean isValid = true;

		for (int i = 0; i < s.length; i++) {
			if (i % 2 == 0) {
				isValid = isOperand(s[i]);
			} else {
				isValid = isOperator(s[i]);
			}
		}

		return isValid;
	}

	public static double execution(String[] args) {

		List<String> tmp = new ArrayList<String>(Arrays.asList(args));

		tmp = executeRecursive(tmp);

		return Double.parseDouble(tmp.get(0));
	}

	public static List<String> executeRecursive(List<String> args) {
		Double result = 0.0;

		// elso elem kiszedese
		String operator = args.get(1);
		Double firstNumber = Double.parseDouble(args.get(0));
		Double secondNumber = Double.parseDouble(args.get(2));

		// muvelet a masodik elemmel
		if (operator == "+") {
			Add addCommand = new Add();
			result = addCommand.execute(firstNumber, secondNumber);
		} else if (operator == "-") {
			Subtract subtractCommand = new Subtract();
			result = subtractCommand.execute(firstNumber, secondNumber);
		} else {
			throw new IllegalArgumentException("nem tamogatott muveletsor");
		}

		// rekurziv hivas
		args.set(0, result.toString());
		args.remove(1);
		args.remove(1);

		if (args.size() > 2) {
			args = executeRecursive(args);
		}
		return args;

	}

}
