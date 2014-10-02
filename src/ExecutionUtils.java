import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;


public class ExecutionUtils {

	public static Node parseFromFile(String filePath) {
		Node rootNode = null;
		
		try {
			List<String> configFile = Files.readAllLines(FileSystems.getDefault().getPath(filePath), Charset.defaultCharset());
			
			HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
			
			for (String configLine : configFile) {
				String[] params = configLine.split(" ");
				
				int number = Integer.parseInt(params[0]);
				
				Node n = new Node();
				
				if (params.length == 3) {
					String lastParam = params[2];
					
					if (lastParam != null) {
						if (lastParam.equals("i")) {
							rootNode = n;
						} else if (lastParam.equals("f")) {
							n.setFinalState(true);
						}
					}
				}
				
				nodes.put(number, n);
			}
			
			for (String configLine : configFile) {
				String[] params = configLine.split(" ");
				
				int number = Integer.parseInt(params[0]);
				
				Node n = nodes.get(number);
				
				String[] actions = params[1].split("\\|");
				
				for (String action : actions) {
					String[] actionParams = action.split(",");
					
					int actionNumber = Integer.parseInt(actionParams[1]);
					
					Node nextNode = nodes.get(actionNumber);
					
					n.addAction(actionParams[0].toCharArray()[0], nextNode);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rootNode;
	}
}
