package Junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({
	OrderServiceTest.class,
	OrderService2Test.class,
	OrderService2MultiThreadTest.class,
	
})
public class OrderServiceTestSuite {

	//empty, serving as a test suite container

}
