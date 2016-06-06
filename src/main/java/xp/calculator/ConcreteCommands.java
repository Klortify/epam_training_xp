package xp.calculator;

class Add implements CalculatorCommand {

	public double execute(double operand1, double operand2) {
		return operand1 + operand2;
	}
}

class Subtract implements CalculatorCommand {

	public double execute(double operand1, double operand2) {
		return operand1 - operand2;
	}
}

class Multiply implements CalculatorCommand {

	public double execute(double operand1, double operand2) {
		return operand1 * operand2;
	}
}

class Divide implements CalculatorCommand {

	public double execute(double operand1, double operand2) {
		return operand1 / operand2;
	}
}