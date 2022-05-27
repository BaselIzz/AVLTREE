
public class AVLTree<T extends Comparable<T>> {
	private TNode root;

	public TNode rotateRight(TNode nodeN) {
		TNode nodeC = nodeN.getLeft();
		nodeN.setLeft(nodeC.getRight());
		nodeC.setRight(nodeN);
		return nodeC;
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	public void traverseInOrder(TNode node) {
		if (node != null) {
			if (node.getLeft() != null)
				traverseInOrder(node.getLeft());
			System.out.print(node + " ");
			if (node.getRight() != null)
				traverseInOrder(node.getRight());
		}
	}

	public TNode rotateLeft(TNode nodeN) {
		TNode nodeC = nodeN.getRight();
		nodeN.setRight(nodeC.getLeft());
		nodeC.setLeft(nodeN);
		return nodeC;
	}

	public TNode rotateRightLeft(TNode nodeN) {
		TNode nodeC = nodeN.getRight();
		nodeN.setRight(rotateRight(nodeC));

		return rotateLeft(nodeN);
	}

	public TNode rotateLeftRight(TNode nodeN) {
		TNode nodeC = nodeN.getLeft();
		nodeN.setLeft(rotateLeft(nodeC));

		return rotateRight(nodeN);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(T data) {
		if (isEmpty())
			root = new TNode<>(data);
		else {
			TNode rootNode = root;
			addEntry(data, rootNode);
			root = rebalance(rootNode);
		}
	}

	private TNode rebalance(TNode nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (getHeightDifference(nodeN.getLeft()) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (getHeightDifference(nodeN.getRight()) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

private int getHeightDifference(TNode nodeN) {
		int leftHight= height(nodeN.getLeft());
		int rightHight=height(nodeN.getRight());
		
		return leftHight-rightHight;
	}

//	public TNode delete(T data) {
//		TNode temp = super.delete(data);
//		if (temp != null) {
//			TNode rootNode = root;
//			root = rebalance(rootNode);
//		}
//		return temp;
//	}

	public int height() {
		return height(root);
	}

	public int height(TNode node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.getLeft());
		if (node.hasRight())
			right = height(node.getRight());
		return (left > right) ? (left + 1) : (right + 1);
	}

	public void addEntry(T data, TNode rootNode) {
		assert rootNode != null;
		if (data.compareTo((T) rootNode.getData()) < 0) { // right into left subtree
			if (rootNode.hasLeft()) {
				TNode leftChild = rootNode.getLeft();
				addEntry(data, leftChild);
				rootNode.setLeft(rebalance(leftChild));
			} else
				rootNode.setLeft(new TNode(data));
		} else { // right into right subtree
			if (rootNode.hasRight()) {
				TNode rightChild = rootNode.getRight();
				addEntry(data, rightChild);
				rootNode.setRight(rebalance(rightChild));
			} else
				rootNode.setRight(new TNode(data));
		}
	}
}
