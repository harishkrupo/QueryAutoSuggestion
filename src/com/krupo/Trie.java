package com.krupo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class Trie {
	private class Node
	{
		public HashMap<Character, Node> hm;
		public char c;
		public Node parent;
		public Node()
		{
			hm = new HashMap<Character,Node>();
			parent = null;
		}
	}
	
	private Node root;
	private Stack<Node> stateHolder;
	public Trie()
	{
		stateHolder = new Stack<Node>();
		root= new Node();
		root.c='\0';
		root.parent=null;
	}
	public void insert(String in)
	{
		int i;
		char []ina= in.toCharArray();
		Node nodeIterator = root;
		for(i=0;i<in.length();i++)
		{
			if(nodeIterator.hm.containsKey(ina[i]))
			{
				nodeIterator = nodeIterator.hm.get(ina[i]);
				continue;
			}
			else
			{
				Node t= new Node();
				t.parent = nodeIterator;
				t.c = ina[i];
				nodeIterator.hm.put(ina[i], t);
				nodeIterator=t;
			}
		}
	}

	public ArrayList<String> getCompletions(String pre)
	{
		char []pa = pre.toCharArray();
		ArrayList<String> arl= new ArrayList<String>();
		Node nodeIterator = root;
		int i;
		for(i=0;i<pre.length();i++)
		{
			if(nodeIterator.hm.containsKey(pa[i]))
			{
				nodeIterator = nodeIterator.hm.get(pa[i]);
			}
			else
			{
				return null;
			}
		}
		stateHolder.push(nodeIterator);
		
		while(!stateHolder.isEmpty())
		{
			nodeIterator = stateHolder.pop();
			Iterator<Map.Entry<Character, Node>> it = nodeIterator.hm.entrySet().iterator();
			if(nodeIterator.hm.isEmpty())
			{
				Node goingUp = nodeIterator;
				String temp = new String();
				while(goingUp != root)
				{
					temp = temp + goingUp.c;
					goingUp = goingUp.parent;
				}
				arl.add(new StringBuffer().append(temp).reverse().toString());
				continue;
			}
			while(it.hasNext())
			{
				Map.Entry<Character, Node> et = it.next();
				stateHolder.push(et.getValue());
			}
			
		}
		return arl;
	}
}
