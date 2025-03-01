package Junit;
 
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
 
class OrderServiceTest {
	OrderService os=new OrderService();
 
	@Test
	void test() {
		double total = os.calPrice(100.00, 2);
		assertEquals(180,total, "total should be 180 after 10% discount");
		//fail("Not yet implemented");
	}
 
	//Sufficient Stocks
	@Test
	void testSufficientStock() {
	    OrderService os = new OrderService();
	    boolean result = os.placeOrder(5);
	    assertTrue(result, "Order should be placed successfully");
	    assertEquals(5, os.getStock(), "Stock should be reduced by the ordered quantity");
	}
	
	
	//Insufficient Stocks
	@Test
	void testInsufficientStock() {
	    OrderService os = new OrderService();
	    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	        os.placeOrder(12); // Trying to place an order for 12 units when only 10 are available
	    });
	    assertEquals("Insufficient stock", exception.getMessage(), "Exception message should match");
	}

	//Test with Zero quantity
	@Test
	void testCalTotalWithZeroQuantity() {
	    OrderService os = new OrderService();
	    double total = os.calPrice(100.00, 0); // Calculating total price with quantity 0
	    assertEquals(0, total, "Total should be 0 when quantity is 0");
	}
	
	
	
	//Parameterized Test
	@ParameterizedTest
    @CsvSource({
        "100.00,2,180.00",
        "200.00,1,180.00",
        "50.00,3,135.00",
        "0.00,4,0.00"
    })  
    void testCalPriceParamCsv(double price, int quantity, double expectedTotal) {
        OrderService os = new OrderService();
        double total = os.calPrice(price, quantity);
        assertEquals(expectedTotal, total, 0.01, "Total should match the expected value");
    }
	
	
	//Timeout 
	@Test
	void testTimeout() {
		assertTimeout(java.time.Duration.ofMillis(1000),()->{
			Thread.sleep(500);
			os.calPrice(100.00, 2);
		});
	}


	
	
	//MultiThread Test
//	place order -> parallely -> 2 orders
//	1st order -> 2
//	2nd order -> 1
//	final Stock -> 7
	
	
	
	/* beforeall, aftereach, afterall -> methods -> println
	 * before all -> print -> runs once for all tests
	 * after all -> print runs once after all tests
	 * @Disabled
	 * after each -> running after every test
	 */
	
	@Test
	void testPlaceOrderMultithread() throws InterruptedException {
		Thread thread1 = new Thread(() -> os.placeOrder(2));
		Thread thread2 = new Thread(() -> os.placeOrder(1));
		
		
		thread1.start();
		thread2.start();
		
		
		thread1.join();
		thread2.join();
		
		assertEquals(7, os.getStock());
	}
	
	
	
	

	@BeforeAll
    public static void beforeAllTests() {
        System.out.println("Testing Started");
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("All Tests Completed");
    }
	
    @AfterEach
    public void afterEachTest() {
        System.out.println("Test Completed");
    }
    
    
    @Test
    @Disabled("Test disabled for demonstration purposes")
    public void testDisabled() {
        System.out.println("This test is disabled and will not run");
    }
	
	
	
}