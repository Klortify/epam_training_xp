package xp.calculator;

class Add implements CalculatorCommand {

	public double execute(double leftOperand, double rightOperand) {
		return leftOperand + rightOperand;
	}
}

class Subtract implements CalculatorCommand {

	public double execute(double leftOperand, double rightOperand) {
		return leftOperand - rightOperand;
	}
}

class Multiply implements CalculatorCommand {

	public double execute(double leftOperand, double rightOperand) {
		return leftOperand * rightOperand;
	}
}

class Divide implements CalculatorCommand {

	public double execute(double leftOperand, double rightOperand) {
		return leftOperand / rightOperand;
	}
}