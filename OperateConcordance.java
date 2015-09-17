package concordance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OperateConcordance {
	public void operate() {
		Concordance ccd = new Concordance();
		GenerateAlphabeticTitle gat = new GenerateAlphabeticTitle();
		ReadFiles  rf = new ReadFiles("/Users/Jason/Desktop/example_2.txt");
		String fileContents;
		try {
			fileContents = rf.readFile();
			Map<String, String> rs = ccd.getConcordance(fileContents);
			ArrayList<String> sortedWordList = ccd.getSortedKeySet();
			for(int i = 0; i < rs.size(); i++) {
				String key = sortedWordList.get(i);
				System.out.println(gat.getAlphabeticTitle(i+1) + "  " +key + " " + rs.get(key));
				
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	public static void main(String[] args) {
		OperateConcordance oc = new OperateConcordance();
		oc.operate();
	}
}
