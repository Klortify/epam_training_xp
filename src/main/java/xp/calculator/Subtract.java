package xp.calculator;

public class Subtract implements CalculatorCommand {
	double leftOperand;
	double rightOperand;
	
	public Subtract (double leftOperand, double rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public double execute() {
		return leftOperand - rightOperand;
	}
}