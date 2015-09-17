package concordance;

public class GenerateAlphabeticTitle {
	public String getAlphabeticTitle(int n) {
		char alphabet = (char) (--n % 26 + 'a');
		StringBuilder sb = new StringBuilder();
		sb.append(alphabet);
		String title = new String(new char[(n/26)+1]).replace("\0", sb.toString());
		return title + ".";
	}
}
