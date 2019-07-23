
/*					Author : Francois Mukaba 
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Queue;

public class RedBlackTree<Key extends Comparable, Value> {
	private Node root;
	private static boolean RED = true;
	private static boolean BLACK = false;

	private class Node {
		Key key;
		Value value;
		Node left, right;
		int size;
		boolean color;

		Node(Key key, Value value, boolean color) {
			this.key = key;
			this.value = value;
			this.color = color;
		}
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		return get(key) != null;
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.size;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null) {
			return null;
		}
		int compare = key.compareTo(x.key);
		if (compare < 0) {
			return get(x.left, key);
		} else if (compare > 0) {
			return get(x.right, key);
		} else {
			return x.value;
		}
	}

	public List<Value> greaterThanKey(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		List<Value> ls = new ArrayList<Value>();
		greaterThanKey(root, key, ls);
		return ls;
	}

	private void greaterThanKey(Node x, Key key, List<Value> ls) {
		if (x != null) {
			int compare = key.compareTo(x.key);
			if (compare < 0) {
				ls.add(x.value);
			}
			greaterThanKey(x.left, key, ls);
			greaterThanKey(x.right, key, ls);
		}
	}

	public List<Value> lessThanKey(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		List<Value> ls = new ArrayList<Value>();
		lessThanKey(root, key, ls);
		return ls;
	}

	private void lessThanKey(Node x, Key key, List<Value> ls) {
		if (x != null) {
			int compare = key.compareTo(x.key);
			if (compare > 0) {
				ls.add(x.value);
			}
			lessThanKey(x.left, key, ls);
			lessThanKey(x.right, key, ls);
		}
	}

	public void put(Key key, Value value) {
		if (key == null)
			throw new IllegalArgumentException();
		if (value == null)
			return; // delete(key)
		root = put(root, key, value);
		root.color = BLACK;
	}

	private Node put(Node x, Key key, Value value) {
		if (x == null) {
			return new Node(key, value, RED);
		}
		int compare = key.compareTo(x.key);
		if (compare < 0)
			x.left = put(x.left, key, value);
		else if (compare > 0)
			x.right = put(x.right, key, value);
		else
			x.value = value;

		if (!isRed(x.left) && (isRed(x.right)))
			x = rotateLeft(x);
		if (isRed(x.left) && (isRed(x.left.left)))
			x = rotateRight(x);
		if (isRed(x.left) && isRed(x.right))
			flipColors(x);
		return x;
	}

	private Node rotateLeft(Node x) {
		Node temp = x.right;
		x.right = temp.left;
		temp.left = x;
		temp.color = x.color;
		x.color = RED;
		temp.size = x.size;
		x.size = size(x.left) + size(x.right) + 1;
		return temp;
	}

	private Node rotateRight(Node x) {
		Node temp = x.left;
		x.left = temp.right;
		temp.right = x;
		temp.color = x.color;
		x.color = RED;
		temp.size = x.size;
		x.size = size(x.left) + size(x.right) + 1;

		return temp;
	}

	private void flipColors(Node x) {
		x.color = RED;
		x.left.color = BLACK;
		x.right.color = BLACK;
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
	}

	public boolean is23() {
		return is23(root);
	}

	private boolean is23(Node x) {
		if (x == null) {
			return true;
		}
		if (isRed(x.right)) {
			return false;
		}
		if (isRed(x) && isRed(x.left)) {
			return false;
		}
		return is23(x.left) && is23(x.right);
	}

	public boolean isBalanced() {
		int blackHeight = 0;
		Node x = root;
		while (x != null) {
			if (!isRed(x)) {
				blackHeight++;
			}
			x = x.left;
		}
		return isBalanced(root, blackHeight);
	}

	private boolean isBalanced(Node x, int blackHeight) {
		if (x == null) {
			return blackHeight == 0;
		}
		if (!isRed(x)) {
			blackHeight--;
		}
		return isBalanced(x.left, blackHeight) && isBalanced(x.right, blackHeight);
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue);
		return queue;
	}

	private void keys(Node x, Queue<Key> q) {
		if (x == null)
			return;
		keys(x.left, q);
		q.enqueue(x.key);
		keys(x.right, q);
	}

	public void print() {
		print(root);
	}

	private void print(Node x) {
		if (x != null) {
			System.out.println(x.value);
			print(x.left);
			print(x.right);
		}
	}

	public RedBlackTree() {
		// TODO Auto-generated constructor stub
	}

}
