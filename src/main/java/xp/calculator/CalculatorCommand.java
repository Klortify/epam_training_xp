package xp.calculator;

public interface CalculatorCommand {
	// TODO: Finish 'command' pattern by adding operands as members. 

	public double execute(double leftOperand, double rightOperand);
}
