package tasks;


import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import mainfiles.Calculator;



public class CalculatorTest {
   
	Calculator calculator = new Calculator();
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method........");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method........");
	}
	
/*************** Positive Test Cases *************/
	
	@Test(groups = "smoke")
	public void addition() {
		System.out.println("Invoking addition method");
		Assert.assertEquals(calculator.sum(10,30), 40);
	}
	@Test (groups = "smoke")
	public void substraction() {
		System.out.println("Invoking substraction method");
		Assert.assertEquals(calculator.sub(10,30), -20);

	}
	
	@Test 
	public void multiplication() {
		System.out.println("Invoking multiplication method");
		Assert.assertEquals(calculator.mult(10,30), 300);
	}
	
	@Test 
	public void division() {
		System.out.println("Invoking division method");
		Assert.assertEquals(calculator.div(100,50), 2);
	}
	
	@Test
	public void squareRoot() {
		System.out.println("Invoking squareRoot method");
		Assert.assertEquals(calculator.sqrt(25.0), 5.0);
	}
	
	@Test
	public void power() {
		System.out.println("Invoking power method");
		Assert.assertEquals(calculator.pow(-8.2, 2), 67.24);
	}
	
	
	//Test with Dataprovider
	@Test(dataProvider = "testData",groups ="smoke")
	public void additionWithDataProvider(int a, int b) {
		System.out.println("Invoking additionWithDataProvider method");
		Assert.assertEquals(calculator.sum(a, b), Math.addExact(a, b));
	}
	
	
/*************** Negative Test Cases *************/
	
//  Bug 1 = Wrong exception thrown for divide by zero
	@Test 
	public void divisionByZero() {
		System.out.println("Invoking divisionByZero method");
		try {
			Assert.assertEquals(calculator.div(10, 0),0);
		}catch(ArithmeticException e) {
			System.out.println("Cannot divide by zero : " + e);
		}
	}
	
//  Bug 2 = Wrong result by 'double' multiplication -- it is rounding off result
	@Test
	public void multiplicationByDouble() {
		System.out.println("Invoking multiplicationByDouble method");
		Assert.assertEquals(calculator.mult(2.5, 3.8),9.5);
	}
	
//  Bug 3 = ArithmeticException not thrown for divide by 0.0	
	@Test
	public void divideByZeroInDouble() throws ArithmeticException{
		System.out.println("Invoking divideByZeroInDouble method");
		try {
			Assert.assertEquals(calculator.div(6, 0.0), 0);
		}catch(ArithmeticException e) {
			System.out.println("Cannot divide by zero " + e);
		}
	}
	
	@Test(groups = "negativeTests")
	public void divideByNegative() {
		System.out.println("Invoking divideByNegative method");
		Assert.assertEquals(calculator.div(1.5, -0.6), -2.5);
	}
	
	@Test (groups = "negativeTests")
	public void multiPlyByNegative() {
		System.out.println("Invoking multiPlyByNegative method");
		Assert.assertEquals(calculator.mult(-62, 0.5), -31);
	}
	
//  Bug 4 = Wrong result by double power
	@Test
	public void powerWithDouble() {
		System.out.println("Invoking powerWithDouble method");
		Assert.assertEquals(calculator.pow(8, 2.2), 97.00586025666551);
	}	
	
//  Bug 5 = Wrong result for cos
	@Test
	public void cos() {
		System.out.println("Invoking cos method");
		Assert.assertEquals(calculator.cos(0),1.0);
	}
	
	@DataProvider(name = "testData")
	public Object[][] getTestData(){
		return new Object[][]{
			{10,20},
			{23,25},
			{90,5}
		};
	}
}
