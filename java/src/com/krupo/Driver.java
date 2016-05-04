package com.krupo;
import java.util.ArrayList;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {
		Trie trie = new Trie();
//		trie.insert("ted");
//		trie.insert("to");
//		trie.insert("tea");
//		trie.insert("ten");
//		trie.insert("in");
//		trie.insert("inn");
		Scanner scn = new Scanner(System.in);
		trie.buildTrie("/home/harishkrupo/Desktop/uc6.txt");
		while(true)
		{
			System.out.print("Please enter the text : ");
			String s = scn.nextLine();
			ArrayList<String> x = trie.AStarSearch(s, 7);
			System.out.println(x);
		}
//		ArrayList<String> x = trie.getCompletions("t");
		
		
//		System.out.println("Hold");
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
