/**
 *
 */
package utility;

import java.io.InputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import util.parsers.ParseUtil;

/**
 * @author Krishna Kishore
 * 
 */

public class ParseUtilTest {

	private ParseUtil parseUtil;

	@Before
	public void init() {

		parseUtil = new ParseUtil();

	}

	@After
	public void closeResources() {
		parseUtil = null;
	}

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Test
	public void testReadFile_NullFile() {
		exit.expectSystemExit();
		SystemExitUtility.exit();
	}

	@Test
	public void testReadFile_InvalidFile() {
		exit.expectSystemExit();
		SystemExitUtility.exit();
	}

	@Test
	public void getMatrixFromFile_NullFile() {
		exit.expectSystemExit();
		parseUtil.ReadFileIntoMatrix("null", 6, 6);
	}

	@Test
	public void getMatrixFromFile() {
		String fileName = "config/Level_1_Points.txt";
		InputStream actual = parseUtil.readFile(fileName);
		Assert.assertNotNull(actual);
	}
}