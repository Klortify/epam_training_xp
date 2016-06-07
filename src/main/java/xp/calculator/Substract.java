package xp.calculator;

public class Substract implements CalculatorCommand {
	double leftOperand;
	double rightOperand;
	
	public Substract (double leftOperand, double rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public double execute() {
		return leftOperand - rightOperand;
	}
}