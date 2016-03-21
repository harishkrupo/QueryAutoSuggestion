package com.krupo;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("ted");
		trie.insert("to");
		trie.insert("tea");
		trie.insert("ten");
		trie.insert("in");
		trie.insert("inn");
		ArrayList<String> x = trie.getCompletions("t");
		System.out.println(x);
	}

}
