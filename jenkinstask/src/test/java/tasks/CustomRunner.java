package tasks;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class CustomRunner extends TestNG{
	
	public CustomRunner() {
		//Set test classes to be executed
//		setTestClasses(new Class[] {CalculatorTest.class});
		
		
		//for test suites to be executed
		List<String> suites = Lists.newArrayList();
	    suites.add(".//testng.xml");
	    setTestSuites(suites);
		
		//add listener
		addListener(new ExtentListener());
	}
	
	public static void main(String [] args) {
		CustomRunner runner = new CustomRunner();
		runner.run();
	}

}
