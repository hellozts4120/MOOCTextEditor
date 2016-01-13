package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		if(word.equals(null) || word.equals("") || isWord(word)){
			return false;
		}
		else{
			String WordLowerCase = word.toLowerCase();
			char[] WordLowerCaseArray = WordLowerCase.toCharArray();
			TrieNode cur = new TrieNode(WordLowerCase);
			cur = root;
			for(int i = 0; i < WordLowerCaseArray.length; i++){
				if(!cur.getValidNextCharacters().contains((Character)WordLowerCaseArray[i])){
					cur = cur.insert((Character)WordLowerCaseArray[i]);
				}
				else{
					cur = cur.getChild((Character)WordLowerCaseArray[i]);
				}
				if(i == WordLowerCaseArray.length - 1){
					cur.setEndsWord(true);
				}
			}
			if(!cur.equals(null)){
				size++;
				return true;
			}
			return false;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s.equals("") || s.equals(null) || root.getValidNextCharacters().size()==0){
			return false;
		}
		else{
			String sLowerCase = s.toLowerCase();
			char[] sLowerCaseArray = sLowerCase.toCharArray();
			TrieNode cur = new TrieNode();
			cur = root;
			for(int i = 0; i < sLowerCaseArray.length; i++){
				if(cur == null || cur.getValidNextCharacters().isEmpty()){
					return false;
				}
				else{
					cur = cur.getChild((Character)sLowerCaseArray[i]);
					if(cur != null && i == (sLowerCaseArray.length)-1){
						return cur.endsWord();
					}
				}
			}
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 List <String> completions = new LinkedList<String>();
    	 Queue<TrieNode> queue = new LinkedList<TrieNode>();
    	 String prefixLCase = prefix.toLowerCase();
    	 char[] prefixArray = prefixLCase.toCharArray();
    	 TrieNode cur = new TrieNode();
    	 cur = root;
    	 for(int i = 0; i < prefixArray.length; i++){
    		 if(cur != null && (!cur.getValidNextCharacters().isEmpty())){
    			 cur = cur.getChild(prefixArray[i]);
    		 }
    	 }
    	 if(cur == null){
    		 return completions;
    	 }
    	 
    	 if(cur.getValidNextCharacters().isEmpty()){
    		 return completions;
    	 }
    	 
    	 queue.add(cur);
    	 while(!queue.isEmpty() && completions.size() < numCompletions){
    		 cur = queue.remove();
    		 if(isWord(cur.getText())){
    			 completions.add(cur.getText());
    		 }
    		 TrieNode temp = new TrieNode();
    		 for(Character c : cur.getValidNextCharacters()){
    			 temp = cur.getChild(c);
    			 queue.add(temp);
    		 }
    	 }
    	 
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}