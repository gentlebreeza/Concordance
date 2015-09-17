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
	/*public static void main(String[] args) {
		String fn = "/Users/Jason/Desktop/english_file 2.txt";
		ReadFiles rfs = new ReadFiles(fn);
		List<String> ls = new ArrayList<String>();
		List<String> sortedKeySet = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		try{
			String rs = rfs.readFile();
			
			BreakIterator bi = BreakIterator.getSentenceInstance();
			int start = bi.first();
			bi.setText(rs);
			for (int end = bi.next();
				end != BreakIterator.DONE;
				start = end, end = bi.next()) {
				ls.add(rs.substring(start, end));
			}
						
			for(String str : ls) {
				
				String[] st = str.substring(0,str.length()-2).replaceAll("[,:]","").split("\\s");
				for(int i = 0; i < st.length; i++) {
					if(map.containsKey(st[i])) {
						map.put(st[i], map.get(st[i])+1);
					} else {
						map.put(st[i], 1);
						sortedKeySet.add(st[i].toLowerCase());
					}
				}
			}
			Collections.sort(sortedKeySet);
			for(String sss : sortedKeySet) {
				System.out.println(sss);
			}
			
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}*/
}
