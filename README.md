# Huffman-Encoding-Java
Simple implementation of Huffman compression in Java, storing 1s and 0s in a String instead of converting all the way to actual bits.

# Explanation

The idea behind Huffman Compression is building a tree with the letters from the input, giving
each letter a weight based on how often it appears in the input. 

So for a sample input of "Hello World", our original letter-weight pairs look like this:

"l": 3
"o": 2
"H": 1
"e": 1
"W": 1
"r": 1
"d": 1

The genius breakthrough in Huffman Compression was building the tree from bottom up, instead of the
top down approach used before. 

First we order our pairs from smallest to greatest by their weights. Then, we take the two smallest pairs and
create a new tree, with the parent having a weight of the sum of the two pairs' weights, and each branch being
one of the letters. Then this tree is placed back into our list, and the list is ordered again. 

In this code, the sorting is implemented using a priority queue, which automatically sorts the items in it
based on the comparator provided in the constructur (or by natural ordering if no comparator is provided).
To get the two smallest items, `.poll()` is called twice, then the newly created tree is placed back into
the priority queue. This is repeated until there is only one tree left in the priority queue. 

At this point, our tree looks something like this:

      [ 10 ] 
     /      \
    /        \   
   / \      / \ 
  /\ /\    /\  l
 r d e W  o H  

 Now, to get our bit representation for each letter, we just navigate down the tree until we reach a letter,
 counting each left as a "0" and each right as a "1".

 So "r" is represented as 000, and l is represented as 11. The way the tree is built, characters that are 
 used more often will appear higher up in the tree, and so will have a shorter representation (and so we
 save more space). Also note that for regular characters, we need 8 bits, but with this method, as long as we have
 less than 2^8 different characters in the string, we will ALWAYS save some space, as the representations of the
 characters will be less than or equal to the regular 8 bit representation. 
