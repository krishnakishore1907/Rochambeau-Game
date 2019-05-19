package util.parsers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.rochambeau.service.LoggingService;
import com.rochambeau.service.impl.LoggingServiceImpl;

import exceptions.InputNotFoundException;
import utility.SystemExitUtility;

/**
 * Utility class to parse Input file.
 * 
 * @author Krishna Kishore
 * 
 */
public class ParseUtil {

	LoggingService LOGGER = LoggingServiceImpl.getLoggingServiceImpl();

	/**
	 * @param fileName
	 *            Name of the input file to read config into matrix
	 * @param rows
	 *            number of rows in the matrix
	 * @param cols
	 *            number of columns in the matrix
	 * @return Loaded Matrix of Integers from input file
	 */
	public Integer[][] ReadFileIntoMatrix(String fileName, int rows, int cols) {

		InputStream inputStream = readFile(fileName);
		Integer[][] resultMatrix = new Integer[rows][cols];
		Integer cRow = 0;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			// Ignoring the header
			String sCurrentLine = br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				List<String> tokens = parseString(sCurrentLine, ",");
				// Removing the row title
				tokens.remove(0);
				for (int i = 0; i < tokens.size(); i++) {
					resultMatrix[cRow][i] = Integer.parseInt(tokens.get(i).trim());
				}
				cRow++;
			}

		} catch (Exception e) {
			SystemExitUtility
					.exitWithMsg("Exception in reading Matrix configuration File. Please revalidate the file content");
		}

		return resultMatrix;
	}

	/**
	 * @return Returning Arraylist by removing delimiters
	 * @throws InputNotFoundException
	 */
	public List<String> parseString(String input, String token) {
		List<String> result = new ArrayList<>();
		String[] tokens = null;

		if (Objects.isNull(input) || Objects.isNull(token)) {
			try {
				throw new InputNotFoundException("Input file is empty");
			} catch (InputNotFoundException e) {
				e.printStackTrace();
			}
		}
		tokens = input.split(token);
		Arrays.asList(tokens).forEach(p -> result.add(p));
		return result;
	}

	/**
	 * Checks and reads the file
	 * 
	 * @return Returns input resource as an InputStream
	 */
	public InputStream readFile(String input) {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(input);

		if (Objects.isNull(inputStream)) {
			SystemExitUtility.exitWithMsg("Unable to find file " + input);
		}
		return inputStream;
	}

}