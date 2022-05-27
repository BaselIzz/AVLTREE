
public class DriverAvl {
	public static void main(String[] args) {
			AVLTree<Integer> number = new AVLTree<Integer>();
			number.insert(80);
			number.insert(40);
			number.insert(50);
			number.insert(20);
			number.insert(10);
			number.insert(87);
			number.insert(89);
			number.insert(82);
			number.insert(81);
			number.traverseInOrder();
			
	}
}
