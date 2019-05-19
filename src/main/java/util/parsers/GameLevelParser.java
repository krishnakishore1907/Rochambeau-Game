package util.parsers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import com.rochambeau.model.Actions;
import com.rochambeau.model.Opponent;
import com.rochambeau.service.LoggingService;
import com.rochambeau.service.StageService;
import com.rochambeau.service.impl.LoggingServiceImpl;

import utility.SystemExitUtility;

/**
 * Class created to read the number of levels and opponents details on each
 * levels in the game
 * 
 * @author Krishna Kishore
 * 
 */

public class GameLevelParser {

	private static final LoggingService LOGGER = LoggingServiceImpl.getLoggingServiceImpl();
	private Integer numberOfOpponents = 0;
	ParseUtil parseUtil = new ParseUtil();

	public void readData(String fileName) {

		if (Objects.isNull(fileName)) {
			SystemExitUtility.exitWithMsg("Unable to find stage Details configuration file");
		}
		InputStream inputStream = parseUtil.readFile(fileName);
		if (Objects.isNull(inputStream)) {
			SystemExitUtility.exitWithMsg("Unable to find stage Details configuration file");
		}
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

			String sCurrentLine = br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(sCurrentLine, "|");

				while (stringTokenizer.hasMoreElements() && stringTokenizer.countTokens() == 3) {
					Integer level = Integer.parseInt(stringTokenizer.nextElement().toString());

					// Load Opponents of each Stage
					List<String> opponentList = parseUtil.parseString(stringTokenizer.nextElement().toString(), ",");
					List<Opponent> opponents = new ArrayList<>();
					opponentList.forEach(e -> opponents.add(new Opponent(e, level)));
					numberOfOpponents += opponents.size();

					// Load Actions of each Stage
					List<String> actionList = parseUtil.parseString(stringTokenizer.nextElement().toString(), ",");
					List<Actions> actions = new ArrayList<>();
					actionList.forEach(a -> actions.add(new Actions(a, 100)));

					if (level == null || actionList.isEmpty() || opponentList.isEmpty()) {

						LOGGER.log("stage Parsing failed for line :  " + sCurrentLine);
						continue;
					}
					StageService stageMain = new StageService(level);
					stageMain.setOpponents(level, opponents);
					stageMain.setActions(level, actions);
				}
			}

		} catch (Exception e) {
			SystemExitUtility.exitWithMsg("Exception while parsing Stages file");
		}
	}
}