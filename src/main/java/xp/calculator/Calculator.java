package xp.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

	/* OPERATIONS */
	public static double add(double leftOperand, double rightOperand) {
		CalculatorCommand addCommand = new Add();
		return addCommand.execute(leftOperand, rightOperand);
	}

	public static double subtract(double leftOperand, double rightOperand) {
		CalculatorCommand subtractCommand = new Subtract();
		return subtractCommand.execute(leftOperand, rightOperand);
	}

	public static double multiply(double ileftOperand, double rightOperand) {
		CalculatorCommand multiplyCommand = new Multiply();
		return multiplyCommand.execute(ileftOperand, rightOperand);
	}

	public static double divide(double leftOperand, double rightOperand) {
		CalculatorCommand divideCommand = new Divide();
		return divideCommand.execute(leftOperand, rightOperand);
	}

	/*  */
	public static boolean isOperand(String operand) {
		boolean isOperand = true;

		try {
			Double.parseDouble(operand);
		} catch (NumberFormatException e) {
			isOperand = false;
		}

		return isOperand;
	}

	public static boolean isOperator(String n1) {
		boolean isOperator = false;

		if (n1 == "+" || n1 == "-" || n1 == "*" || n1 == "/") {
			isOperator = true;
		}

		return isOperator;
	}

	public static boolean isInputOrderValid(String[] args) {
		boolean isValid = true;

		for (int i = 0; i < args.length; i++) {
			if (i % 2 == 0) {
				isValid = isOperand(args[i]);
			} else {
				isValid = isOperator(args[i]);
			}
		}

		return isValid;
	}

	public static double execution(String[] args) {

		List<String> temporaryArguments = new ArrayList<String>(Arrays.asList(args));

		temporaryArguments = executeRecursive(temporaryArguments);

		return Double.parseDouble(temporaryArguments.get(0));
	}

	public static List<String> executeRecursive(List<String> args) {
		Double result = 0.0;

		// elso elem kiszedese
		String operator = args.get(1);
		double leftOperand = Double.parseDouble(args.get(0));
		double rightOperand = Double.parseDouble(args.get(2));

		// muvelet a masodik elemmel
		if (operator == "+") {
			Add addCommand = new Add();
			result = addCommand.execute(leftOperand, rightOperand);
		} else if (operator == "-") {
			Subtract subtractCommand = new Subtract();
			result = subtractCommand.execute(leftOperand, rightOperand);
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
