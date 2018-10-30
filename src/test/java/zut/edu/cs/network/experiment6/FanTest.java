package zut.edu.cs.network.experiment6;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.apache.log4j.*;

public class FanTest {

	private static Logger logger = Logger.getLogger(FanTest.class);
	Fan fan = new Fan();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        logger.info("Test start");
    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        logger.info("End of test");
    }

	@Test
	public void testGetSpeed() {
		logger.info("���Է��ȵ��ٶ�");
		fan.setSpeed(12);
		logger.info(fan.getSpeed());
		assertEquals(12, fan.getSpeed());
	}

	@Test
	public void testIsOn() {
		logger.info("���Է��ȵĿ���");
		fan.setOn(true);
		logger.info(fan.isOn());
		assertEquals(true, fan.isOn());
	}

	@Test
	public void testGetRadius() {
		logger.info("���Է��ȵİ뾶");
		fan.setRadius(3.2);
		logger.info(fan.getRadius());
	    assertEquals(3.2, fan.getRadius(),0.1);
	}

	@Test
	public void testGetColor() {
		
		logger.info("���Է��ȵ���ɫ");
		fan.setColor("red");
		logger.info(fan.getColor());
		assertEquals("red", fan.getColor());
		
	}

	@Test
	public void testToString() {
		logger.info(fan.toString());
		fan.setColor("yellow");
		fan.setOn(true);
		fan.setRadius(6.6);
		fan.setSpeed(6);
		logger.info(fan.toString());
		assertEquals("Fan [color=yellow, radius=6.6, speed=6, on=true]", fan.toString());
	}
}
