package testing;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestMovement.class,
	TestBoardStorage.class,
	TestScore.class,
	TestBoard.class
})

public class TestAll {
}