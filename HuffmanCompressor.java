import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Comparator;
/** 
  * @author Alex Krentsel
  * Main compression class.
  */

public class HuffmanCompressor {

	public static void main(String[] args) {
		System.out.println(compressString("this is a sample input string. let's see how well it works.").length() + "");
	}

	public static String compressString(String s) {
		List<HuffmanTree> nodes = generateNodes(s);
		HuffmanTree huffmanTree = generateTree(nodes);
		HashMap<Character, String> hashMap = generateHashMap(huffmanTree);
		String compressedString = generateCompressedString(s, hashMap);
		return compressedString;
	}

	private static List<HuffmanTree> generateNodes(String s) {
		List<HuffmanTree> nodes = new ArrayList<>();

		for(int i = 0; i < s.length(); i = i + 1) {
			HuffmanTree newTree = new HuffmanTree(s.charAt(i), 1);
			int index = -1;
			for(int k = 0; k < nodes.size(); k++) {
				if(nodes.get(k).c == newTree.c) {
					index = k;
					break;
				}
			}
			if(index != -1) {
				nodes.get(index).val += 1;
			} else {
				nodes.add(newTree);
			}
		}
		return nodes;
	}

	private static HuffmanTree generateTree(List<HuffmanTree> nodes) {
		PriorityQueue<HuffmanTree> priorityQueue = new PriorityQueue<>(nodes.size(), new Comparator<HuffmanTree>() {

			@Override
			public int compare(HuffmanTree h1, HuffmanTree h2) {
				return h1.val - h2.val;
			}
		});

		 for(HuffmanTree node : nodes) {
		 	priorityQueue.add(node);
		 }

		while(priorityQueue.size() > 1) {
			HuffmanTree a = priorityQueue.poll();
			HuffmanTree b = priorityQueue.poll();
			HuffmanTree combinedNode = new HuffmanTree('_', a.val + b.val, a, b);
			priorityQueue.add(combinedNode);
		}
		return priorityQueue.poll();
	}

	private static HashMap<Character, String> generateHashMap(HuffmanTree t) {
		HashMap<Character, String> hashMap = new HashMap<>();
		generateHashMapHelper(t, "", hashMap);
		return hashMap;
	}

	/**
	  * Recursively navigate our HuffmanTree to build up a HashMap of Character, String pairs. 
	 **/
	private static void generateHashMapHelper(HuffmanTree t, String path, HashMap<Character, String> hashMap) {
		if(t.isLeaf()) {
			hashMap.put(t.c, path);
		} else {
			if (t.leftBranch != null) {
				generateHashMapHelper(t.leftBranch, path + "0", hashMap);
			}
			if (t.rightBranch != null) {
				generateHashMapHelper(t.rightBranch, path + "1", hashMap);
			}
		}
	}

	private static String generateCompressedString(String input, HashMap<Character, String> hashMap) {
		String output = "";
		for (int i = 0; i < input.length(); i = i + 1) {
			output = output + hashMap.get(input.charAt(i));
		}
		return output;
	}
}