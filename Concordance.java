package concordance;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.text.BreakIterator;

public class Concordance {
	private Map<String, ArrayList<Integer>> map;//key: word, value: the list that stores the index of sentence that contains the word
	private Map<String, String> concordance;//key: word, value: { frequencies: sentence number}
	private ArrayList<String> sentences;//list that stores every single sentence in given text
	private ArrayList<String> sortedKeySet;//store all the words, and using Collections.sort to sort the words according to alphabetic order
	private BreakIterator iterator;//used to break text into sentences
	
	public Map<String, String> getConcordance(String fileContents) {
		sentences = new ArrayList<>();
		iterator = BreakIterator.getSentenceInstance();
		iterator.setText(fileContents);
		map = new HashMap<String, ArrayList<Integer>>();
		sortedKeySet = new ArrayList<String>();
		concordance = new HashMap<>();
		
		int start = iterator.first();
		//break text into sentences
		for(int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			sentences.add(fileContents.substring(start, end));
		}
		
		//stores word and the number of sentence in which the word appears in map
		for(int j = 0; j < sentences.size(); j++) {
			String sentence = sentences.get(j).substring(0, sentences.get(j).length()-1).replaceAll("[\",:!?]","").replaceAll("--", "");
			String[] words = sentence.split(" ");
			for(int i = 0; i < words.length; i++) {
				if(words[i].matches("\\d+")) continue;//assume word means only English word, so if the word is a digit, ignore it
				
				String word = words[i].toLowerCase();
				if(map.containsKey(word)) {//if the word is already in the map, just add sentence number in the corresponding list
					map.get(word).add(j+1);
				} else {//if not, create a new ArrayList and put the word and ArrayList as <key, value> pair in the map.
					ArrayList<Integer> sentenceIndex = new ArrayList<>();
					sentenceIndex.add(j+1);
					map.put(word, sentenceIndex);
					sortedKeySet.add(word);//add the word into ArrayList that will be sorted to get alphabetic order
				}
			}
		}
		
		Collections.sort(sortedKeySet);//sort the ArrayList to get alphabetic order
		
		for(String key : sortedKeySet) {
			ArrayList<Integer> list = map.get(key);
			//construct the value as the format in the example, for instance: {2: 1,1}
			String value = "{" + list.size() + ":";
			for(int i = 0; i < list.size(); i++) {
				value += list.get(i);
				if(i != list.size()-1) {
					value += ",";
				}
			}
			value += "}";
			concordance.put(key, value);
		}
		
		return concordance;
	}
	
	public ArrayList<String> getSortedKeySet() {
		return sortedKeySet;
	}
	
	//test case
//	public static void main(String[] args) {
//		Concordance ccd = new Concordance();
//		GenerateAlphabeticTitle gat = new GenerateAlphabeticTitle();
//		ReadFiles  rf = new ReadFiles("/Users/Jason/Desktop/example_2.txt");
//		String fileContents;
//		try {
//			fileContents = rf.readFile();
//			Map<String, String> rs = ccd.getConcordance(fileContents);
//			ArrayList<String> sortedWordList = ccd.getSortedKeySet();
//			for(int i = 0; i < rs.size(); i++) {
//				String key = sortedWordList.get(i);
//				System.out.println(gat.getAlphabeticTitle(i+1) + "  " +key + " " + rs.get(key));
//				
//			}
//		} catch (IOException ioe) {
//			System.out.println(ioe.getMessage());
//		}
//		
//	}
}

