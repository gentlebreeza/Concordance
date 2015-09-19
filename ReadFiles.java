package concordance;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

//assume word means only English words.

public class ReadFiles {
	private String path;
	public ReadFiles(String fileName) {
		path = fileName;
	}
	public String readFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String line;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}
}
