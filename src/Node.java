import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Node {
	
	private HashMap<Character, List<Node>> links = new HashMap<Character, List<Node>>();
	private boolean isFinalState = false;
	
	public Node() {
	}
	
	public Node(boolean isFinalState) {
		this.isFinalState = isFinalState;
	}
	
	public void addAction(Character character, Node node) {
		List<Node> currNodes = null;
		
		if (links.containsKey(character)) {
			currNodes = links.get(character);
		} else {
			currNodes = new ArrayList<Node>();
		}
		
		currNodes.add(node);
		
		links.put(character, currNodes);
	}
	
	public List<Node> getNextNodes(Character character) {
		if (links.containsKey(character)) {
			return links.get(character);
		}
		
		return null;
	}

	public boolean isFinalState() {
		return isFinalState;
	}

	public void setFinalState(boolean isFinalState) {
		this.isFinalState = isFinalState;
	}
	
}
