package xp.calculator;

public class Add implements CalculatorCommand {
	double leftOperand;
	double rightOperand;

	public Add(double leftOperand, double rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public double execute() {
		return leftOperand + rightOperand;
	}
}