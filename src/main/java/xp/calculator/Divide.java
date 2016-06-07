package xp.calculator;

public class Divide implements CalculatorCommand {
	double leftOperand;
	double rightOperand;
	
	public Divide (double leftOperand, double rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public double execute() {
		return leftOperand / rightOperand;
	}
}