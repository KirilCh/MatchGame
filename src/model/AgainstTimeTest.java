package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

//import junit.framework.Assert;

public class AgainstTimeTest  extends junit.framework.TestCase{

	AgainstTime at1;
	public AgainstTimeTest() {setUp();}
	
	public void setUp()
	{
		at1 = new AgainstTime("Kiril",0);
	}
	
	@Test
	public void testSetGame() {
		
	}

	@Test
	public void testScoreCalc() {
		
		at1.scoreCalc(0); //Can be tested with any value except for "1", to verify nothing has changed inside score array
		assertTrue(at1.score[0]==0); //Test init of combo value
		assertTrue(at1.score[1]==0); //Test init of score value
		
		at1.score[1]=1;
		at1.scoreCalc(1);
		assertTrue(at1.score[0]==5); //Combo is zero
		
		at1.score[1]=2;
		at1.scoreCalc(1);
		assertTrue(at1.score[0]==15);
		
		at1.scoreCalc(2); //score[1] value is not changed because it can only update score for value=1
		assertTrue(at1.score[0]==15); //Combo is zero
		
	}

	@Test
	public void testNRandomIntegers() {
		
	}

}
