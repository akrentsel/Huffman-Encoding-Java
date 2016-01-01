import java.text.DecimalFormat;
/** 
  * @author Alex Krentsel
  * Main compression class.
  */
public class SampleClass {
	public static void main(String[] args) {
		String input = "This is a sample string that I will try to compress";
		int originalBitLength = input.length() * 8; //each character is 8 bits
		String compressedOutput = HuffmanCompressor.compressString(input);
		int newBitLength = compressedOutput.length(); //already in bits
		System.out.println("Input: " + input);
		System.out.println("Compressed Output: " + compressedOutput);
		System.out.println("Compression Amount: " + new DecimalFormat("#.##").format((((float) newBitLength) / originalBitLength) * 100) + "%");
	}
}