package testing;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Direction;
import main.Movement;

public class TestMovement {

	private int[] regRow, fullRow, mergeRow, greedyRow, doubleGreed, specificTest;

	@Before
	public void setUp() throws Exception {
		regRow = new int[] { 2, 0, 4, 0 };
		fullRow = new int[] { 2, 4, 16, 128 };
		mergeRow = new int[] { 0, 2, 0, 2 };
		greedyRow = new int[] { 16, 16, 32, 0 };
		doubleGreed = new int[] { 128, 128, 128, 128 };
		specificTest = new int[] { 4, 32, 32, 64 };
	}

	@After
	public void tearDown() throws Exception {
		regRow = null;
		fullRow = null;
		mergeRow = null;
		greedyRow = null;
		doubleGreed = null;
		specificTest = null;
	}

	@Test
	public void testMoveLeft() {
		int[] shiftedRow = Movement.shift(Direction.LEFT, regRow);
		assertEquals(2, shiftedRow[0]);
		assertEquals(4, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.LEFT, fullRow);
		assertEquals(2, shiftedRow[0]);
		assertEquals(4, shiftedRow[1]);
		assertEquals(16, shiftedRow[2]);
		assertEquals(128, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.LEFT, mergeRow);
		assertEquals(4, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.LEFT, greedyRow);
		assertEquals(32, shiftedRow[0]);
		assertEquals(32, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);
		
		shiftedRow = Movement.shift(Direction.LEFT, doubleGreed);
		assertEquals(256, shiftedRow[0]);
		assertEquals(256, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);
		
		shiftedRow = Movement.shift(Direction.LEFT, specificTest);
		assertEquals(4, shiftedRow[0]);
		assertEquals(64, shiftedRow[1]);
		assertEquals(64, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);
	}

	@Test
	public void testMoveUp() {
		int[] shiftedRow = Movement.shift(Direction.UP, regRow);
		assertEquals(2, shiftedRow[0]);
		assertEquals(4, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.UP, fullRow);
		assertEquals(2, shiftedRow[0]);
		assertEquals(4, shiftedRow[1]);
		assertEquals(16, shiftedRow[2]);
		assertEquals(128, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.UP, mergeRow);
		assertEquals(4, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.UP, greedyRow);
		assertEquals(32, shiftedRow[0]);
		assertEquals(32, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);
		
		shiftedRow = Movement.shift(Direction.UP, doubleGreed);
		assertEquals(256, shiftedRow[0]);
		assertEquals(256, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);
		
		shiftedRow = Movement.shift(Direction.UP, specificTest);
		assertEquals(4, shiftedRow[0]);
		assertEquals(64, shiftedRow[1]);
		assertEquals(64, shiftedRow[2]);
		assertEquals(0, shiftedRow[3]);
	}

	@Test
	public void testMoveRight() {
		int[] shiftedRow = Movement.shift(Direction.RIGHT, regRow);
		assertEquals(0, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(2, shiftedRow[2]);
		assertEquals(4, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.RIGHT, fullRow);
		assertEquals(2, shiftedRow[0]);
		assertEquals(4, shiftedRow[1]);
		assertEquals(16, shiftedRow[2]);
		assertEquals(128, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.RIGHT, mergeRow);
		assertEquals(0, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(4, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.RIGHT, greedyRow);
		assertEquals(0, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(32, shiftedRow[2]);
		assertEquals(32, shiftedRow[3]);
		
		shiftedRow = Movement.shift(Direction.RIGHT, doubleGreed);
		assertEquals(0, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(256, shiftedRow[2]);
		assertEquals(256, shiftedRow[3]);
		
		shiftedRow = Movement.shift(Direction.RIGHT, specificTest);
		assertEquals(0, shiftedRow[0]);
		assertEquals(4, shiftedRow[1]);
		assertEquals(64, shiftedRow[2]);
		assertEquals(64, shiftedRow[3]);
	}

	@Test
	public void testMoveDown() {
		int[] shiftedRow = Movement.shift(Direction.DOWN, regRow);
		assertEquals(0, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(2, shiftedRow[2]);
		assertEquals(4, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.DOWN, fullRow);
		assertEquals(2, shiftedRow[0]);
		assertEquals(4, shiftedRow[1]);
		assertEquals(16, shiftedRow[2]);
		assertEquals(128, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.DOWN, mergeRow);
		assertEquals(0, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(0, shiftedRow[2]);
		assertEquals(4, shiftedRow[3]);

		shiftedRow = Movement.shift(Direction.DOWN, greedyRow);
		assertEquals(0, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(32, shiftedRow[2]);
		assertEquals(32, shiftedRow[3]);
		
		shiftedRow = Movement.shift(Direction.DOWN, doubleGreed);
		assertEquals(0, shiftedRow[0]);
		assertEquals(0, shiftedRow[1]);
		assertEquals(256, shiftedRow[2]);
		assertEquals(256, shiftedRow[3]);
		
		shiftedRow = Movement.shift(Direction.DOWN, specificTest);
		assertEquals(0, shiftedRow[0]);
		assertEquals(4, shiftedRow[1]);
		assertEquals(64, shiftedRow[2]);
		assertEquals(64, shiftedRow[3]);
	}
}
