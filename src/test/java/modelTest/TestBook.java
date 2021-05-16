package modelTest;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;

import jwt.model.Books;

public class TestBook {

private Books b;
	
	@BeforeMethod
	public void setUp() throws Exception {
		b=new Books();
		b.setId(1);
		b.setTitle("java");
		b.setSubtitle("Core Java");
		b.setIsbn13("1234567");
		b.setImage("java.png");
		b.setPrice("40");
		b.setUrl("www.java.com");
	}
	@Test
	public void Beantest() {

		new BeanTester().testBean(Books.class);

	}

}
