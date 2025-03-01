package Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderService2Test {

	private OrderService2 orderService2;
	@BeforeEach //initialize orderservice2 before each test caser
	public void setUp() {
		orderService2 = new OrderService2();
	}
	@Test
	public void TestCalPrice_ValidInputs() {
		double price = 100.00;
		int quantity = 2;
		double expectedPrice = price*quantity*(1-orderService2.getDiscount());
		assertEquals(expectedPrice, orderService2.calPrice(price, quantity));
		//checking whether calPrice is calculating the price correctly or not
	}
	//Test case -> passing quantity = 0
	@Test
    void TestCalZeroQuantity() {
		double price = 100.00;
		int quantity = 0;
        double total = orderService2.calPrice(price, quantity);
        assertEquals(0, total, "Total should be 0 when quantity is 0");
    }
	//Test case -> successfully placing order
	//Sufficient Stocks
	@Test
	void TestSuccessfullyPlaced() {
		int quantity = 5;
		int stock = orderService2.getStock();
		boolean result = orderService2.placeOrder(quantity);
		assertTrue(result, "Order should be placed successfully");
		assertEquals((stock-quantity), orderService2.getStock(), "Stock should be reduced by the ordered quantity");
	}

	

	//Boundary test case edge cases
	//edge case -> quantity exactly same as stock
	 @Test
	    void TestPlaceOrderExactStock() {
		 
			int quantity = 10;
	        boolean result = orderService2.placeOrder(quantity);
	        assertTrue(result, "Order should be placed successfully");
	        assertEquals(0, orderService2.getStock(), "Stock should be 0 after ordering the exact stock amount");
	    }
 
 
	//edge case -> order just below stock limit i.e. 10
	  @Test
	    void TestPlaceOrderBelowStockLimit() {
		  int quantity = 9;
			int stock = orderService2.getStock();
			boolean result = orderService2.placeOrder(quantity);
			assertTrue(result, "Order should be placed successfully");
	        assertEquals((stock-quantity), orderService2.getStock(), "Stock should be reduced to 1 after ordering 9 out of 10");
	    }


	//Exceptional cases
	  //Order beyond stock
	    @Test
	    void TestPlaceOrderBeyondStock() {
	    	int stockOrder = 15;
	        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
	            orderService2.placeOrder(stockOrder);
	        });
	        assertEquals("Insufficient stock", e.getMessage());
	    }
 
	    //Set stock to negative value
	    @Test
	    void TestSetNegativeStock() {
	    	int stockOrder = -5;
	        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
	            orderService2.setStock(stockOrder);
	        });
	        assertEquals("Stock cannot be negative.", e.getMessage());
	    }
	    //invalid discount
	    @Test
	    void TestSetInvalidDiscount() {
	    	double discount = 1.5;
	        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
	            orderService2.setDiscount(discount);
	        });
	        assertEquals("Discount must be between 0 and 1.", e.getMessage());
	    }
 
	    
	  //Price is negative
		void TestNegativePrice() {
			int quantity = 4;
			double price = -100.00;
	        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
	            orderService2.calPrice(price, quantity);
	        });
	        assertEquals("Price cannot be negative.", e.getMessage());
	    }

		 //Quantity is negative
		void TestNegativeQuantity() {
			int quantity = -4;
			double price = 100.00;
	        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
	            orderService2.calPrice(price, quantity);
	        });
	        assertEquals("Quantity cannot be negative.", e.getMessage());
	    }

		
		
		
		
		
		
		//Parameterized Test Cases
		/*
		csv sourse - cal price -> 4 sets
		price, q, expected 
		
		test to check for boundary condition for placing orders 
		values -> 11, 13, 15
		
		test to check for invalid discounts 
		values -> -0.5, 1.5 , 2.0
		
		test to chcek invalid stock values 
		values -> -5, -10, -15
		
		test for negative price and negative quantity
		csv source -> cal price -> 4 sets 
		
		"100.0, -5"
		"100.0, 5"
		"-50.0, -2"
		*/
		
		
		
		
		//Parameterized test - @CsvSource, @ValueSource, @MethodSource
		@ParameterizedTest
		@CsvSource ({
		    "400, 8, 2880",
		    "200, 5, 900",
		    "600, 4, 2160",
		    "700, 10, 6300"
		})
		void testCalculatePrice(double price, int quantity, double expected) {
		    assertEquals(expected, orderService2.calPrice(price, quantity));
		}

		//Checking valid order placements with different quantities
		//values -> 2, 5, 8, 10
		@ParameterizedTest
		@ValueSource(ints = { 2, 5, 8, 10 })
		void testValidOrderPlacement(int quantity) {
		    assertTrue(orderService2.placeOrder(quantity));
		}

		//Test to check for boundary conditions for placing order
		//values -> 12, 14, 16, 18
		@ParameterizedTest
		@ValueSource(ints = { 12, 14, 16, 18 })
		void testOrderBeyondStock(int quantity) {
		    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.placeOrder(quantity);
		    });
		}

		//test to check for invalid values
		//values -> -0.3, 1.7, 2.5, 3.0
		@ParameterizedTest
		@ValueSource(doubles = { -0.3, 1.7, 2.5, 3.0 })
		void testInvalidDiscountValues(double discount) {
		    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.setDiscount(discount);
		    });
		    assertEquals("Discount must be between 0 and 1.", e.getMessage());
		}

		//test to check invalid stock values
		//values -> -4, -9, -12, -15
		@ParameterizedTest
		@ValueSource(ints = { -4, -9, -12, -15 })
		void testInvalidStockValues(int stock) {
		    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.setStock(stock);
		    });
		}

		//test for negative price and negtative quantity
		//csv source - cal price -> 4 sets 
		//"100.0, -5"
		//"100.0, 5"
		//"-50.0, -2"
		@ParameterizedTest
		@CsvSource({
		    "150.0, -6",
		    "-120.0, 4",
		    "-60.0, -3",
		    "80.0, -7"
		})
		void testNegativePriceAndQuantity(double price, int quantity) {
		    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.calPrice(price, quantity);
		    });
		}
		
		
		
		static Stream<Integer> validStockValues(){
			return Stream.of(0,10,20,30);
		}
		
		
		@ParameterizedTest
		@MethodSource("validStockValues")
		public void testValidStockValue(int stock) {
			orderService2.setStock(stock);
	    	assertEquals(stock,orderService2.getStock());
		}
//	==================================================================================================================================
		
		
		//modify parameterized test -> with method source
		static Stream<Arguments> validInputs() {
			return Stream.of(
					org.junit.jupiter.params.provider.Arguments.of(100.00, 2, 180.0),
					org.junit.jupiter.params.provider.Arguments.of(100.00, 3, 270.0),
					org.junit.jupiter.params.provider.Arguments.of(100.00, 4, 360.0)
					);
		}
		@ParameterizedTest
		@MethodSource("validInputs")
		public void testValidInputs(double price, int quantity, double expected) {
	        assertEquals(expected, orderService2.calPrice(price, quantity));
		}
		static Stream<Integer> boundaryOrderValues() {
			return Stream.of(11, 13, 15);
		}
		@ParameterizedTest
		@MethodSource("boundaryOrderValues")
		void testBoundaryOrderPlace(int number) {
		    assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.placeOrder(number);
		    });
		}
		static Stream<Double> invalidDiscountValues() {
			return Stream.of(-0.5, 1.5, 2.0);
		}
		@ParameterizedTest
		@MethodSource("invalidDiscountValues")
		void testInvalidDiscounts(double number) {
		    assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.setDiscount(number);
		    });
		}
		static Stream<Arguments> negativePriceAndQuantity() {
			return Stream.of(
					Arguments.of(100.0, -5),
					Arguments.of(-50.0, -2)
					);
		}
		@ParameterizedTest
		@MethodSource("negativePriceAndQuantity")
		void testNegPriceNegQuan(double price, int quantity) {
		    assertThrows(IllegalArgumentException.class, () -> {
		        orderService2.calPrice(price, quantity);
		    });
		}
		

}
