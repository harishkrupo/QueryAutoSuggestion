package com.krupo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import com.opencsv.CSVReader;

public class Trie {
	private class Node implements Comparable<Node>
	{
		public HashMap<Character, Node> hm;
		public char c;
		public Node parent;
		public boolean isEnd;
        public int count;
        public Node()
		{
			hm = new HashMap<Character,Node>();
			parent = null;
			isEnd=false;
            this.count = 1 ;
		}

		@Override
		public int compareTo(Node node)
        {
			return this.count - node.count;
		}
        
        
	}
	
	private Node root;
	private Stack<Node> stateHolder;
    private PriorityQueue<Node> state;
    public long totalelems;
    
	public Trie()
	{
		stateHolder = new Stack<Node>();
		root= new Node();
		root.c='\0';
		root.parent=null;
        totalelems = 0;
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
				totalelems = totalelems +1;
			}
		}
        
		nodeIterator.isEnd=true;
        nodeIterator.count = nodeIterator.count +1;
        
        int max=nodeIterator.count;

        Node goingUp = nodeIterator;
        while(goingUp != root)
        {
            if(goingUp.count < max)
            {
                goingUp.count = max;
                goingUp = goingUp.parent;
            }
            else
            {
                break;
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
		int siz = 150;
		FileReader fr = null;
		Scanner scn = null;
		try {
			fr = new FileReader(new File("uc6.txt"));
			scn = new Scanner(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       	
		while(scn.hasNext())
		{
			String line = scn.nextLine();
			String toins = line.split("\t")[1];
			if(toins.length() > siz)
			{
				continue;
			}
			insert(toins);
		}
		try {
			scn.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    public ArrayList<String> AStarSearch(String in, int maxout)
    {
        int seencount = 0;
        char []pa = in.toCharArray();
		ArrayList<String> arl= new ArrayList<String>();
		Node nodeIterator = root;
        
		int i;
		for(i=0;i<in.length();i++)
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
        state = new PriorityQueue<Node>();
        state.add(root);
        
        while(!state.isEmpty())
        {
            nodeIterator = state.remove();
            if (seencount >= maxout)
            {
                break;
            }
            
            if(nodeIterator.isEnd)
            {
                Node goingUp = nodeIterator;
				String temp = new String();
				while(goingUp != root)
				{
					temp = temp + goingUp.c;
					goingUp = goingUp.parent;
				}
				arl.add( new StringBuffer().append(temp).reverse().toString());
                seencount = seencount+1;
                continue;
            }
            
            Set<Entry<Character,Node>> es = nodeIterator.hm.entrySet();
            Iterator<Entry<Character,Node>> it = es.iterator();
            while(it.hasNext())
            {
                Map.Entry<Character,Node> pair= it.next();
                state.add((Node)pair.getValue());
            }
        }

        return arl;
    }
}
