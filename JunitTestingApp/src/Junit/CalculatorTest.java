//package Junit;
// 
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.*;
// 
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
// 
//class CalculatorTest {
//	Calculator calc=new Calculator();
// 
//	@Test
//	void testAdd() {
//		Calculator calc=new Calculator();
//		assertEquals(15,calc.add(5, 10));
//	}
// 
//	@Test
//	void testSub() {
//		Calculator calc=new Calculator();
//		assertEquals(-5,calc.sub(5, 10));
//	}
//	@Test
//	void testConditionT() {
//		Calculator calc=new Calculator();
//		assertTrue(calc.add(5, 10)>10);
//	}
//	@Test
//	void testConditionF() {
//		Calculator calc=new Calculator();
//		assertFalse(calc.sub(5, 10)>0);
//	}
//	@Disabled("this is disabled")
//	@Test
//	void testNull() {
//		Calculator calc=new Calculator();
//		assertNull(calc.add(null, null));
//	}
//	@Test
//	void testNotNull() {
//		Calculator calc=new Calculator();
//		assertNotNull(calc.sub(5, 15));
//	}
//	@ParameterizedTest
//	@ValueSource(ints = {12,15,16,17})
//	void testAddParam(int number) {
//		Calculator calc=new Calculator();
//		assertEquals(number+10, calc.add(number, 10));
//	}
//	@ParameterizedTest
//	@CsvSource ({
//		"5,7,12",
//		"3,6,9",
//		"7,5,12",
//		"8,9,17"
//	})	
//	void testAddParamCsv(int a, int b, int expected) {
//		Calculator calc=new Calculator();
//		assertEquals(expected, calc.add(a,b));
//	}
//	@Test
//	void testTimeout() {
//		assertTimeout(java.time.Duration.ofMillis(1000),()->{
//			Thread.sleep(500);
//			calc.add(2, 3);
//		});
//	}
//	//disabling a test
//	
//	@Test
//	void testException() {
//		ArithmeticException exception = assertThrows(ArithmeticException.class, ()-> {
//			calc.div(12,2);
//		});
//	}
//
//}