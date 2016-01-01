/** 
  * @author Alex Krentsel
  * Data structure used to create dictionary with proper weighting.
  */

public class HuffmanTree {
	public char c;
	public int val;
	public HuffmanTree leftBranch;
	public HuffmanTree rightBranch;

	public HuffmanTree(char c, int val, HuffmanTree leftBranch, HuffmanTree rightBranch) {
		this.c = c;
		this.val = val;
		this.leftBranch = leftBranch;
		this.rightBranch = rightBranch;
	}

	public HuffmanTree(char c, int val) {
		this.c = c;
		this.val = val;
		this.leftBranch = null;
		this.rightBranch = null;
	}

	public boolean isLeaf() {
		return ((leftBranch == null) && (rightBranch == null));
	}

	@Override
	public String toString() {	
		return "[" + c + ", " + String.valueOf(val) + "]";
	}
}