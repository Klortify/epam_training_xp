package xp.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
	
	private List<String> argumentList;
	
	public Calculator(String[] argumentList) {
		if (!isInputOrderValid(argumentList)){
			throw new IllegalArgumentException("Invalid user input");
		}
		this.argumentList = Arrays.asList(argumentList);
	}
	
	
	
	// TODO: Extract parser class
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
			
			if (!isValid){
				return false;
			}
		}

		return true;
	}

	
	
	public double execute() {

		List<String> temporaryArguments = new ArrayList<String>(argumentList);

		temporaryArguments = executeRecursive(temporaryArguments);

		return Double.parseDouble(temporaryArguments.get(0));
	}

	private List<String> executeRecursive(List<String> args) {
		Double result = 0.0;

		CalculatorCommand command = getNextCommand(args);

		result = command.execute();

		args = exchangeOperationWithResult(args, result);

		if (hasNextCommand(args)) {
			args = executeRecursive(args);
		}
		return args;

	}

	private CalculatorCommand getNextCommand(List<String> args)
			throws IllegalArgumentException {
		String operator = args.get(1);
		double leftOperand = Double.parseDouble(args.get(0));
		double rightOperand = Double.parseDouble(args.get(2));

		CalculatorCommand command;
		if (operator == "+") {
			command = new Add(leftOperand, rightOperand);
		} else if (operator == "-") {
			command = new Subtract(leftOperand, rightOperand);
		} else {
			throw new IllegalArgumentException("not supported operation");
		}

		return command;
	}

	private boolean hasNextCommand(List<String> args) {
		return args.size() > 2;
	}

	private List<String> exchangeOperationWithResult(List<String> args, Double result) {
		putResultToLeftOperand(args, result);
		removeAlreadyCalculatedItems(args);

		return args;
	}

	private void putResultToLeftOperand(List<String> args, Double result) {
		args.set(0, result.toString());
	}

	private void removeAlreadyCalculatedItems(List<String> args) {
		args.remove(1);
		args.remove(1);
	}

}
