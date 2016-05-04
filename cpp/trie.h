#ifndef __trie_h__
#define __trie_h__
#endif

#include <iostream>
#include <map>
#include <queue>

using namespace std;

class Node
{
public:
    Node* parent;
    char c;
    int count;

    int depth;
    bool isEnd;
    map<char,Node*> hm;
        
    Node()
    {
            
        c = '\0';
        count = 1;
        isEnd = false;
        depth = 0;
        parent = NULL;
    }

    bool operator()(Node* n1, Node* n2)
    {
        double mfn, hfn;
        mfn = 0.2*n1->depth + 1.0/n1->depth + 1.0/n1->count;
        hfn = 0.2*n2->depth + 1.0/n2->depth + 1.0/n2->count;
        return int(mfn - hfn);
    }
};

class Trie
{
public:
    Trie();
    void insert(string);
    vector<string>* AStarSearch(string,int);
    void buildTrie(string);

private:
    Node* root;
    priority_queue<Node*,vector<Node*>, Node > stateHolder;
};
