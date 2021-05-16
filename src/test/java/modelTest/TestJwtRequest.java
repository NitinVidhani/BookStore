package modelTest;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;

import jwt.model.JwtRequest;

public class TestJwtRequest {
private  JwtRequest j;
	
	@BeforeMethod
	public void setUp() throws Exception {
		j=new JwtRequest();
		j.setUsername("nancy");
		j.setPassword("wheeler123");
	}
	@Test
	public void Beantest() {

		new BeanTester().testBean(JwtRequest.class);

	}
}
