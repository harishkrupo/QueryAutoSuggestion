package com.krupo;
import java.util.ArrayList;


public class Driver {

	public static void main(String[] args) {
		Trie trie = new Trie();
//		trie.insert("ted");
//		trie.insert("to");
//		trie.insert("tea");
//		trie.insert("ten");
//		trie.insert("in");
//		trie.insert("inn");
		trie.buildTrie("/home/harishkrupo/Desktop/uc.txt");
		ArrayList<String> x = trie.getCompletions("geo");
		System.out.println(x);
//		System.out.println("Hold");
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
