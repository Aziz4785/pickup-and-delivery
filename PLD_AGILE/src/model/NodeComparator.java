package model;

import java.util.Comparator;

/**
 * a short class made to compare Nodes by their ID
 *
 */
public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
		if (arg0.getID() > arg1.getID())
			return 1;
		if (arg0.getID() < arg1.getID())
			return -1;
		return 0;
	}

}
