/**
 *
 */
package utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rochambeau.model.Player;
import com.rochambeau.service.StageService;

//TODO
/**
 * @author Krishna Kishore
 * 
 */
public class ReadWriteUtilTest {

	private static Player player;

	@Mock
	private static StageService stageService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		stageService = new StageService(0);
		player = new Player("Test");
	}

	@After
	public void closeResources() {
		player = null;
		stageService = null;
	}

	/**
	 * Test method for
	 * {@link util.ReadWriteUtil#writeObject(com.rochambeau.model.Player)}.
	 */
	@Test
	public void testwriteObject() {
		File file = new File(System.getProperty("user.home") + "/rochambeau/" + player.getName() + ".ser");
		ReadWriteUtil.writeObject(player);
		assertTrue(file.exists());
		file.delete();
	}

	/**
	 * Test method for {@link util.ReadWriteUtil#readObject(java.lang.String)}.
	 */
	@Test
	public void testReadObject() {
		File file = new File(System.getProperty("user.home") + "/rochambeau/" + player.getName() + ".ser");
		ReadWriteUtil.writeObject(player);
		assertTrue(file.exists());
		Player actual = ReadWriteUtil.readObject("Test");
		assertEquals("Test", actual.getName());
		file.delete();
	}

	@Test
	public void testReadObject_NullInput() {
		Player a = ReadWriteUtil.readObject(null);
		assertNull(a);
	}

	@Test
	public void testReadObject_FileNotPresent() {
		Player a = ReadWriteUtil.readObject("krishna.kishore");
		assertNull(a);
	}

}