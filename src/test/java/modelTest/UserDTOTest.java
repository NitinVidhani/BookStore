package modelTest;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;

import jwt.model.UserDTO;

public class UserDTOTest {
private UserDTO u;
	
	@BeforeMethod
	public void setUp() throws Exception {
		u=new UserDTO();
		
		u.setUsername("mike");
		u.setPassword("wheeler123");
		
	}
	@Test
	public void Beantest() {

		new BeanTester().testBean(UserDTO.class);

	}
}
