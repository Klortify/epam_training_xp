package xp.calculator;

public class Multiply implements CalculatorCommand {
	double leftOperand;
	double rightOperand;
	
	public Multiply (double leftOperand, double rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public double execute() {
		return leftOperand * rightOperand;
	}
}