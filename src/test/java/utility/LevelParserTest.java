package utility;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.rochambeau.service.StageService;

import util.parsers.GameLevelParser;
import util.parsers.ParseUtil;

/**
 * @author Krishna Kishore
 * 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(GameLevelParser.class)
public class LevelParserTest {

	public static ParseUtil parseUtil;

	@Resource
	public static GameLevelParser gameLevelParser;
	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		parseUtil = Mockito.mock(ParseUtil.class);
		gameLevelParser = new GameLevelParser();
	}

	@Test
	public void testReadData_NullInput() {

		exit.expectSystemExit();
		gameLevelParser.readData(null);
	}

	@Test
	public void testReadData_InvalidInput() {
		exit.expectSystemExit();
		gameLevelParser.readData("krishna.kishore");
	}

	@Test
	public void testReadData_ValidFile() throws Exception {
		gameLevelParser.readData("config/Game_Levels.txt");
		assertEquals(new Integer(9), StageService.getTotalOpponentsCount());
	}

}
