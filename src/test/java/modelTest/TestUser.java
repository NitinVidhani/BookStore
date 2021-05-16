package modelTest;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;

import jwt.model.DAOUser;

public class TestUser {
private DAOUser u;
	
	@BeforeMethod
	public void setUp() throws Exception {
		u=new DAOUser();
		u.setUsername("William");
		u.setPassword("Byers1234");
	}
	@Test
	public void Beantest() {

		new BeanTester().testBean(DAOUser.class);

	}
}
