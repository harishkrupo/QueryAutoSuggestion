package com.krupo;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import com.opencsv.CSVReader;

public class Trie {
	private class Node
	{
		public HashMap<Character, Node> hm;
		public char c;
		public Node parent;
		public boolean isEnd;
		public Node()
		{
			hm = new HashMap<Character,Node>();
			parent = null;
			isEnd=false;
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
		Node t=new Node();
		for(i=0;i<in.length();i++)
		{
			if(nodeIterator.hm.containsKey(ina[i]))
			{
				nodeIterator = nodeIterator.hm.get(ina[i]);
				continue;
			}
			else
			{
				t= new Node();
				t.parent = nodeIterator;
				t.c = ina[i];
				nodeIterator.hm.put(ina[i], t);
				nodeIterator=t;
			}
		}
		t.isEnd=true;
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
			else if(nodeIterator.isEnd)
			{
				Node goingUp = nodeIterator;
				String temp = new String();
				while(goingUp != root)
				{
					temp = temp + goingUp.c;
					goingUp = goingUp.parent;
				}
				arl.add(new StringBuffer().append(temp).reverse().toString());
			}
			while(it.hasNext())
			{
				Map.Entry<Character, Node> et = it.next();
				stateHolder.push(et.getValue());
			}
			
		}
		return arl;
	}
	
	
	public void buildTrie(String filename)
	{
		CSVReader csvreader=null;
		String []nextline ;
		int i =0;
		try {
			csvreader = new CSVReader(new FileReader(filename),'\t','\'',1);
			while((nextline=csvreader.readNext()) !=null && i<100000)
			{
				insert(nextline[1]);
				//System.out.println(nextline[1]);
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
