package generics;

public class Pair<LEFT_TYPE, RIGHT_TYPE> {

	private LEFT_TYPE left;
	private RIGHT_TYPE right;

	public Pair(LEFT_TYPE left, RIGHT_TYPE right) {
		this.left = left;
		this.right = right;
	}

	public LEFT_TYPE getLeft() {
		return left;
	}

	public void setLeft(LEFT_TYPE left) {
		this.left = left;
	}

	public RIGHT_TYPE getRight() {
		return right;
	}

	public void setRight(RIGHT_TYPE right) {
		this.right = right;
	}

}
