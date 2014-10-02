import java.util.ArrayList;
import java.util.List;


public class ExecutionState {

	private Node rootNode;
	private List<Node> currentNodes = new ArrayList<Node>();
	
	public ExecutionState(Node node) {
		rootNode = node;
		this.reset();
	}
	
	public void reset() {
		currentNodes = new ArrayList<Node>();
		currentNodes.add(rootNode);
	}
	
	public void execute(String text) {
		this.reset();
		
		for (Character character : text.toCharArray()) {
			this.execute(character);
		}
	}
	
	public void execute(Character character) {
		List<Node> newCurrentNodes = new ArrayList<Node>();
		
		for (Node node : currentNodes) {
			List<Node> nextNodes = node.getNextNodes(character);
			
			if (nextNodes == null) {
				continue;
			}
			
			newCurrentNodes.addAll(nextNodes);
		}
		
		currentNodes = newCurrentNodes;
	}
	
	public boolean isStateFinal() {
		for (Node node : currentNodes) {
			if (node.isFinalState()) {
				return true;
			}
		}
		
		return false;
	}
	
}
