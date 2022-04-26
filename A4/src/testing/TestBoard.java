package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Board;
import main.Direction;

public class TestBoard {

	@Test
	public void testInit() {
		Board.init(4,4);
		Board.init(10, 10);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testInitBadDimensions() {
		Board.init(1,1);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testInitNegativeDimensions() {
		Board.init(-1,0);
	}

	@Test
	public void testMove() {
		Board.init(3,3);
		Board.move(Direction.UP);
		Board.move(Direction.DOWN);
		Board.move(Direction.LEFT);
		Board.move(Direction.RIGHT);
	}

	@Test
	public void testScore() {
		Board.init(4,4);
		assertEquals(0, Board.getScore());
		Board.setScore(100);
		assertEquals(100, Board.getScore());
		Board.setScore(-4);
		assertEquals(-4, Board.getScore());
	}

	@Test
	public void testMovesPossible() {
		Board.init(2,2);
		assertTrue(Board.movesPossible());
		for (int i = 0; i < 10; i++) {
			Board.move(Direction.UP);
			Board.move(Direction.LEFT);
			Board.move(Direction.DOWN);
			Board.move(Direction.RIGHT);
		}
		assertFalse(Board.movesPossible());
	}

	@Test
	public void testGetBoard() {
		Board.init(5,5);
		int[][] result = Board.getBoard();
		assertEquals(5,result.length);
		assertEquals(5,result[0].length);
	}

}