package stockwatch;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ConfigParser {

	private final static String separator = "=";
	private final static String quotes = "quotes";
	private final static String statistics = "statystics";

	private Map<String, String> params;

	public ConfigParser(String filePath) {
		try {
			params = new HashMap<String, String>();
			FileReader file = new FileReader(filePath);
			LineNumberReader lineNumber = new LineNumberReader(file);

			String line;
			String records[];
			while ((line = lineNumber.readLine()) != null) {
				records = line.split(separator);
				if (records.length == 2) {
					params.put(records[0].trim(), records[1].trim());
				}
			}
		} catch (FileNotFoundException x) {
			x.printStackTrace();
			System.out.println(x.getMessage());
		} catch (IOException y) {
			y.printStackTrace();
			System.out.println(y.getMessage());
		}
	}

	public String getQuotesDataFilePath() {
		if (params.containsKey(quotes)) {
			return params.get(quotes);
		}
		return null;
	}

	public String getStatisticsDataFilePath() {
		if (params.containsKey(statistics)) {
			return params.get(statistics);
		}
		return null;
	}
}