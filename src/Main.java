import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Node rootNode = ExecutionUtils.parseFromFile(args[0]);

		ExecutionState es = new ExecutionState(rootNode);
		
		try {
			List<String> textFile = Files.readAllLines(FileSystems.getDefault().getPath(args[1]), Charset.defaultCharset());
			
			int line = 1;
			for (String textLine : textFile) {
				String[] words = textLine.split(" ");
				
				int column = 1;
				for (String word : words) {
					es.execute(word);
					
					if (es.isStateFinal()) {
						System.out.println("[" + line + "," + column + "]" + " " + word);
					}
					
					column += word.length();
				}
				
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
