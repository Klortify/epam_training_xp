package calculator.xp;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import xp.calculator.Add;
import xp.calculator.CalculatorCommand;
import xp.calculator.Multiply;
import xp.calculator.Substract;


public class CalculatorTest {
	
	/* D A T A  P R O V I D E R S */
	@DataProvider(name = "inputsForAdd")
	Object[][] addInput() {
		return new Object[][] { 
				{ 2, 3, 5 }, 
				{ 0, 1, 1 }, 
				{ -1, 1, 0 },
				{ -1, -5, -6 } 
		};
	}

	@DataProvider(name = "inputsForSubtract")
	Object[][] subtractInput() {
		return new Object[][] { 
				{ 2, 3, -1.0 }, 
				{ 0, 1, -1 }, 
				{ -1, 1, -2 },
				{ -1, -5, 4 } 
		};
	}

	@DataProvider(name = "operands")
	Object[][] operands() {
		return new Object[][]{
				{"0.0"}, {"1.0"}, {"-1.0"}, {"3.14"}, {"7"}, {"-5"},
		};
	}
	
	@DataProvider(name = "operators")
	Object[][] operators() {
		return new Object[][]{
				{"+"}, {"-"}, {"*"}, {"/"},
		};
	}

	@DataProvider(name = "inputsForMultipleOperation")
	Object[][] multipleInput() {
		return new Object[][] { 
				{ 10.0, "3", "+", "10", "+", "2", "-", "5" },
				{ 0.0, "1", "-", "3", "+", "2" } 
		};
	}
	
	/* T E S T S */
	
	@Test
	public void testAdd() {
		CalculatorCommand addCommand = new Add(5.0, 4.0);

		double result = addCommand.execute();
		
		Assert.assertEquals(result, 9.0);
	}

	@Test(dataProvider = "inputsForAdd", enabled = false)
	public void testAddBulk(double a, double b, double expected_result) {
		double result = Calculator.add(a, b);

		Assert.assertEquals(result, expected_result, "Add failed.");
	}

	@Test
	public void testSubtract() {
		CalculatorCommand substractCommand = new Substract(5.0, 4.0);
		double result = substractCommand.execute();
		Assert.assertEquals(result, 1.0);
	}

	@Test
	public void testMultiply() {
		CalculatorCommand multiplyCommand = new Multiply(5.0, 4.0);
		double result = multiplyCommand.execute();
		Assert.assertEquals(result, 20.0);
	}

	@Test(dataProvider = "inputsForSubtract", enabled = false)
	public void testSubtractBulk(double a, double b, double expected_result) {
		double result = Calculator.subtract(a, b);

		Assert.assertEquals(result, expected_result, "Subtract failed.");
	}

	@Test(dataProvider="operands")
	public void testIsOperand(String o) {
		Assert.assertEquals(Calculator.isOperand(o), true);
	}
	@Test(dataProvider="operators")
	public void testIsNotOperand(String o) {
		Assert.assertEquals(Calculator.isOperand(o), false);
	}
	@Test(dataProvider="operators")
	public void testIsOperator(String o) {
		Assert.assertEquals(Calculator.isOperator(o), true);
	}
	@Test(dataProvider="operands")
	public void testIsNotOperator(String o) {
		Assert.assertEquals(Calculator.isOperator(o), false);
	}

	@Test
	public void testValidateInputOrder() {
		String[] testInput = { "1", "+", "2", "-", "9" };

		boolean isValid = Calculator.isInputOrderValid(testInput);

		Assert.assertEquals(isValid, true);
	}

	@Test
	public void testExecutionAdd() {
		String[] testInput = { "1", "+", "2" };

		Double result = Calculator.execution(testInput);

		Assert.assertEquals(result, 3.0);
	}

	@Test
	public void testExecutionSubtract() {
		String[] testInput = { "2", "-", "1" };

		Double result = Calculator.execution(testInput);

		Assert.assertEquals(result, 1.0);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testExecutionIllegalArgument() {
		String[] testInput = { "2", "&", "1" };

		Double result = Calculator.execution(testInput);

	}

	@Test
	public void testExecutionAddMultipleNumbers() {
		String[] testInput = { "1", "+", "2", "+", "3" };

		Double result = Calculator.execution(testInput);

		Assert.assertEquals(result, 6.0);
	}

	@Test(dataProvider = "inputsForMultipleOperation")
	public void testExecutionAddMultipleNumbersWithDataProvider(
			Double expectedResult, String... parts) {

		Double result = Calculator.execution(parts);

		Assert.assertEquals(result, expectedResult);
	}
}
