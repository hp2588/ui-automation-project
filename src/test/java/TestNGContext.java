import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestNGContext {
	WebDriver driver;
	
	@Test
	public void test1(ITestContext con) {
		
		con.setAttribute("title", driver.getTitle());
		
	}
	
	
	@Test
	public void test2(ITestContext con) {

	}
	

}
