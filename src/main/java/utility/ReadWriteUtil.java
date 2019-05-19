package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import com.rochambeau.model.Characters;
import com.rochambeau.model.Player;
import com.rochambeau.service.LoggingService;
import com.rochambeau.service.impl.LoggingServiceImpl;

/**
 * Utility for saving and retrieving the state of the object. This functionality
 * is generalized by giving interface type rather Player while saving object.
 * 
 * @author Krishna Kishore
 * 
 */
public class ReadWriteUtil {

	public static final LoggingService LOGGER = LoggingServiceImpl.getLoggingServiceImpl();
	public static final String SAVE_DIR = System.getProperty("user.home") + "/rochambeau/";

	public static Player readObject(String name) {

		if (Objects.isNull(name) || name.isEmpty()) {
			return null;
		}
		Path filePath = Paths.get(SAVE_DIR + name + ".ser");
		File file = filePath.toFile();

		if (!file.exists()) {
			return null;
		}
		// Using Java7 try-with-resources to have resource auto closed
		try (FileInputStream fin = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fin);) {

			Player player = (Player) in.readObject();
			LOGGER.log("Player already found and restored..");
			return player;

		} catch (Exception e) {
			LOGGER.log("Could not read the player from backup..");
			return null;
		}
	}

	public static void writeObject(Characters player) {

		boolean fileAvailable = true;

		if (Objects.isNull(player)) {
			LOGGER.log("Null player can't be saved");
			return;
		}
		Path filePath = Paths.get(SAVE_DIR + player.getName() + ".ser");
		File file = filePath.toFile();

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				fileAvailable = file.createNewFile();
			} catch (IOException e) {
				SystemExitUtility.exitWithMsg("Unable to create the file..");
			}
		}
		// Java7: try-with-resources
		try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fos);) {

			if (fileAvailable) {
				out.writeObject(player);
				LOGGER.log("Player " + player.getName().toUpperCase() + " is saved successfully ");
			}

		} catch (FileNotFoundException e) {
			LOGGER.log("File is not found for writing the object");
		} catch (IOException e) {
			LOGGER.log("IOException while writing the object");
		}
	}
}
