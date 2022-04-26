package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Board;
import main.Score;

public class TestScore {

	@Before
	public void setUp() {
		Board.init(4,4);
	}

	@Test
	public void test() {
		assertEquals(0, Board.getScore());
		Score.updateScore(100);
		assertEquals(100, Board.getScore());
		Score.updateScore(10);
		assertEquals(110, Board.getScore());
	}

}