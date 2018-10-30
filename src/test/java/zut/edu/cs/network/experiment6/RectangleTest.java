package zut.edu.cs.network.experiment6;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.apache.log4j.*;


public class RectangleTest {
	private static Logger logger = Logger.getLogger(RectangleTest.class);
	Rectangle rectangle = new Rectangle();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger.info("Test start");
	}
	
	@AfterClass
	public static void setupAfterClass() throws Exception {
		logger.info("End of test");
	}

	@Test
	public void testRectangleDoubleDoubleString() {
		logger.info("���Դ������Ĺ��캯��");
		Rectangle rtest = new Rectangle(2.5,0.4,"red");
		assertEquals(2.5, 0.4,"red",rtest.getWidth(),rtest.getHeight(),rtest.getColor());
	}
	
	private void assertEquals(double d, double e, String string, double width, double height, String color) {
		// TODO Auto-generated method stub
	}

	@Test
	public void testFindArea() {
		logger.info("���Ծ������");
		rectangle.setWidth(3.6);
		rectangle.setHeight(0.5);
		assertEquals(1.8, rectangle.findArea(),0.1);
		logger.info("�������Ϊ��"+rectangle.findArea());
	}

	private void assertEquals(double d, double f, double e) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testFindPerimeter() {
		logger.info("���Ծ����ܳ�");
		rectangle.setWidth(3.6);
		rectangle.setHeight(0.5);
		assertEquals(8.2, rectangle.findPerimeter(),0.1);
		logger.info("�����ܳ�Ϊ��"+rectangle.findPerimeter());
	}

	@Test
	public void testGetWidth() {
		logger.info("���Ծ��εĿ�");
		rectangle.setWidth(3.6);
		assertEquals(3.6, rectangle.getWidth(),0.1);
		logger.info("���εĿ�Ϊ��"+rectangle.getWidth());
	}

	@Test
	public void testGetHeight() {
		logger.info("���Ծ��εĸ�");
		rectangle.setHeight(0.5);
		assertEquals(0.5, rectangle.getHeight(),0.1);
		logger.info("���εĸ�Ϊ��"+rectangle.getHeight());
	}

	@Test
	public void testGetColor() {
		logger.info("���Ծ��ε���ɫ");
		rectangle.setColor("red");
		assertEquals("red", rectangle.getColor());
		logger.info("���ε���ɫΪ��"+rectangle.getColor());
	}

	private void assertEquals(String string, String color) {
		// TODO Auto-generated method stub
		
	}
}
