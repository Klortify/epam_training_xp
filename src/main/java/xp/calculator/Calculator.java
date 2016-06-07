package xp.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

	/*
	 * TODO this should work... later command pattern version, not yet developed
	 */
	// /* OPERATIONS */
	// public static double add(double leftOperand, double rightOperand) {
	// CalculatorCommand addCommand = new Add();
	// return addCommand.execute(leftOperand, rightOperand);
	// }
	//
	// public static double subtract(double leftOperand, double rightOperand) {
	// CalculatorCommand subtractCommand = new Subtract();
	// return subtractCommand.execute(leftOperand, rightOperand);
	// }
	//
	// public static double multiply(double ileftOperand, double rightOperand) {
	// CalculatorCommand multiplyCommand = new Multiply();
	// return multiplyCommand.execute(ileftOperand, rightOperand);
	// }
	//
	// public static double divide(double leftOperand, double rightOperand) {
	// CalculatorCommand divideCommand = new Divide();
	// return divideCommand.execute(leftOperand, rightOperand);
	// }

	public static boolean isOperand(String operand) {
		boolean isOperand = true;

		try {
			Double.parseDouble(operand);
		} catch (NumberFormatException e) {
			isOperand = false;
		}

		return isOperand;
	}

	public static boolean isOperator(String operator) {
		boolean isOperator = false;

		if (operator == "+" || operator == "-" || operator == "*" || operator == "/") {
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

		List<String> temporaryArguments = new ArrayList<String>(
				Arrays.asList(args));

		temporaryArguments = executeRecursive(temporaryArguments);

		return Double.parseDouble(temporaryArguments.get(0));
	}

	public static List<String> executeRecursive(List<String> args) {
		Double result = 0.0;

		CalculatorCommand command = getNextCommand(args);

		result = command.execute();

		args = exchangeOperationWithResult(args, result);

		if (hasMoreCommand(args)) {
			args = executeRecursive(args);
		}
		return args;

	}

	private static boolean hasMoreCommand(List<String> args) {
		return args.size() > 2;
	}

	private static List<String> exchangeOperationWithResult(List<String> args, Double result) {
		putResultToLeftOperand(args, result);
		removeAlreadyCalculatedItems(args);

		return args;
	}

	private static void removeAlreadyCalculatedItems(List<String> args) {
		args.remove(1);
		args.remove(1);
	}

	private static void putResultToLeftOperand(List<String> args, Double result) {
		args.set(0, result.toString());
	}

	public static CalculatorCommand getNextCommand(List<String> args)
			throws IllegalArgumentException {
		String operator = args.get(1);
		double leftOperand = Double.parseDouble(args.get(0));
		double rightOperand = Double.parseDouble(args.get(2));

		CalculatorCommand command;
		if (operator == "+") {
			command = new Add(leftOperand, rightOperand);
		} else if (operator == "-") {
			command = new Substract(leftOperand, rightOperand);
		} else {
			throw new IllegalArgumentException("not supported operation");
		}

		return command;
	}

}
