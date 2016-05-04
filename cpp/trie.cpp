#include<iostream>
#include<algorithm>
#include "trie.h"
#include <map>
#include <fstream>
#include <string.h>
using namespace std;

void Trie::insert(string s)
{

    unsigned int i;
    Node* nodeIterator;
    nodeIterator = root;
    for(i=0;i<s.size();i++)
    {
        if(nodeIterator->hm.find(s[i]) == nodeIterator->hm.end() )
        {
            Node *t = new Node();
            
            nodeIterator->hm[s[i]] = t;
            t->c = s[i];
            t->parent = nodeIterator;
            t->depth = nodeIterator->depth + 1 ;
            nodeIterator = t;
        }
        else
        {
            nodeIterator = nodeIterator->hm[s[i]];
        }
    }
    nodeIterator->isEnd = true;
    nodeIterator->count = nodeIterator->count + 1 ;
    int max = nodeIterator->count;
    Node* goingUp;
    goingUp = nodeIterator;
    while(goingUp != root)
    {

        if(goingUp->count < max)
        {
            goingUp->count = max;
        }
        else
            break;
        goingUp = goingUp->parent;
    }
}

Trie::Trie()
{
    root = new Node();
}


void Trie:: buildTrie(string filename)
{
    ifstream f(filename.c_str());
    unsigned int siz = 150;
    int x=0;
    string line;
    while(getline(f, line))
    {
        if(line.size() > siz)
        {
            continue;
        }
        char *pch = strtok(strdup(line.c_str()), "\t");
        pch = strtok(NULL,"\t");        
        this->insert(pch);
        
        if(x%100000==0)
        {
            cout << x<< endl;
        }
        x=x+1;
    }
}

vector<string>* Trie::AStarSearch(string s, int maxout)
{
    unsigned int i;
    Node* nodeIterator;
    vector<string> *arl=NULL;
    stateHolder = priority_queue<Node*,vector<Node*>,Node> ();
    nodeIterator = root;
    for(i=0;i<s.size();i++)
    {
        if(nodeIterator->hm.find(s[i]) == nodeIterator->hm.end() )
        {
            break;
        }
        else
        {
            nodeIterator = nodeIterator->hm[s[i]];
        }
    }

    int seencount=0;
    
    stateHolder.push(nodeIterator);
    while(!stateHolder.empty())
    { 
        nodeIterator = stateHolder.top();
        stateHolder.pop();
        if (seencount >= maxout)
        { 
            break; 
        }
             
        map<char,Node*>::iterator it;
        map<char,Node*> es = nodeIterator->hm;
        for(it=es.begin();it!=es.end();it++)
        {
            stateHolder.push(it->second);
        }
             
        if(nodeIterator->isEnd)
        { 
            Node* goingUp = nodeIterator;
            string temp = string();
            while(goingUp != root)
            { 
                temp = temp + goingUp->c;
                goingUp = goingUp->parent;
            }
            reverse( temp.begin(),temp.end() );
            if(arl == NULL)
            {
                arl = new vector<string>();
            }
            arl->push_back(temp);
            seencount = seencount+1;
            continue; 
        }
             
    } 
    return arl;
}
