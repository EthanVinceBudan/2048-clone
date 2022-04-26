package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.BoardStorage;

public class TestBoardStorage {

	private BoardStorage testStorage, smallStorage, largeStorage;

	@Before
	public void setUp() {
		testStorage = new BoardStorage(4,4);
		smallStorage = new BoardStorage(2,2);
		largeStorage = new BoardStorage(8, 10);
	}


	@After
	public void tearDown() {
		testStorage = null;
		smallStorage = null;
		largeStorage = null;
	}

	@Test
	public void testGetDimensions() {
		assertEquals(4, testStorage.getWidth());
		assertEquals(4, testStorage.getHeight());
		assertEquals(10, largeStorage.getHeight());
	}

	@Test (expected=IllegalArgumentException.class)
	public void testNewStorageError() {
		BoardStorage temp = new BoardStorage(1,1);
	}

	@Test
	public void testSetRow() {
		testStorage.setRow(2, new int[] { 1, 2, 3, 4 });
		assertEquals(1, testStorage.getBoard()[2][0]);
		assertEquals(2, testStorage.getBoard()[2][1]);
		assertEquals(3, testStorage.getBoard()[2][2]);
		assertEquals(4, testStorage.getBoard()[2][3]);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetRowOutOfBounds() {
		testStorage.setRow(100, new int[] { 1, 2, 3, 4 });
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetRowWrongLength() {
		testStorage.setRow(0, new int[] { 1 });
	}

	@Test
	public void testSetCol() {
		testStorage.setCol(2, new int[] { 1, 2, 3, 4 });
		assertEquals(1, testStorage.getBoard()[0][2]);
		assertEquals(2, testStorage.getBoard()[1][2]);
		assertEquals(3, testStorage.getBoard()[2][2]);
		assertEquals(4, testStorage.getBoard()[3][2]);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetColOutOfBounds() {
		testStorage.setCol(100, new int[] { 1, 2, 3, 4 });
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetColWrongLength() {
		testStorage.setCol(0, new int[] { 1 });
	}

	@Test
	public void testGetBoard() {
		testStorage.setRow(1, new int[] { 3, 3, 3, 3 });
		int[][] result = testStorage.getBoard();
		assertEquals(4, result.length);
		assertEquals(4, result[0].length);
		assertEquals(3, result[1][3]);

		result = largeStorage.getBoard();
		assertEquals(10, result.length);
		assertEquals(8, result[0].length);
	}

	@Test
	public void testAddRandomTileValues() {
		for (int[] row : smallStorage.getBoard()) {
			for (int val : row) {
				assertTrue(val == 0 || val == 2 || val == 4);
			}
		}
		smallStorage.addRandomTiles(2);
		for (int[] row : smallStorage.getBoard()) {
			for (int val : row) {
				assertTrue(val == 0 || val == 2 || val == 4);
			}
		}
	}

	@Test
	public void testAddRandomTileNumber() {
		int i = 0;
		for (int[] row : testStorage.getBoard()) {
			for (int val : row) {
				if (val != 0) i++;
			}
		}
		assertEquals(2, i);

		testStorage.addRandomTiles(5);
		i = 0;
		for (int[] row : testStorage.getBoard()) {
			for (int val : row) {
				if (val != 0) i++;
			}
		}
		assertEquals(7, i);

		testStorage.addRandomTiles(0);
		i = 0;
		for (int[] row : testStorage.getBoard()) {
			for (int val : row) {
				if (val != 0) i++;
			}
		}
		assertEquals(7, i);		

	}

	@Test (expected=ArrayStoreException.class)
	public void testAddRandomTileError() {
		largeStorage.addRandomTiles(100);
	}

	@Test
	public void testIsFull() {
		assertFalse(testStorage.isFull());
		int i = 0;
		while (!testStorage.isFull()) {
			testStorage.addRandomTiles(1);
			i++;
		}

		assertTrue(testStorage.isFull());
		assertEquals(14, i);
	}

	@Test
	public void testGetTileAt() {
		testStorage.setRow(1, new int[] { 3, 7, 9, 1 });
		assertEquals(9, testStorage.getTileAt(2, 1));
		assertEquals(-1, testStorage.getTileAt(-1, -1));
		assertEquals(-1, testStorage.getTileAt(4, 5));
	}

}