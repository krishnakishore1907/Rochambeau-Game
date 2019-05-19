/**
 *
 */
package stage;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rochambeau.model.Opponent;
import com.rochambeau.service.StageService;

/**
 * @author Krishna Kishore
 * 
 */
public class StageServiceTest {

	@Mock
	public static StageService stageService;

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		stageService = new StageService(5);
	}

	@After
	public void CleanResources() throws Exception {
		stageService = null;
	}

	/**
	 * Test method for getOpponents(int) method.
	 */
	@Test()
	public void testGetopponents() throws Exception {
		exit.expectSystemExit();
		List<Opponent> exp = stageService.getOpponents(10);
	}

	/**
	 * Test method for
	 * {@link com.rochambeau.service.StageService#setopponents(int, java.util.List)}.
	 * 
	 * @throws NullPointerException
	 *             when an invalid stage is provided
	 */
	@Test
	public void testSetopponents_invalidLevel() throws Exception {
		exit.expectSystemExit();
		List<Opponent> list = new ArrayList<>();
		list.add(new Opponent("Testopponent", 10));
		StageService service1 = new StageService(5);
		service1.setOpponents(10, list);
	}

}
