#include<iostream>
#include <vector>
#include "trie.h"
#include <algorithm>
int main()
{
    Trie trie = Trie();
    trie.insert("ted"); 
    trie.insert("to"); 
	trie.insert("tea"); 
	trie.insert("ten"); 
	trie.insert("in"); 
	trie.insert("inn");
    time_t t1,t2;
    time(&t1);
    trie.buildTrie("/home/harishkrupo/Desktop/uc6.txt");

    time(&t2);
    cout << "Build completed in "<< difftime(t2,t1) << " seconds"<<endl;
    string s;
    while(true)
    {
        cout << "Enter the keyword : ";
        getline(cin,s);
        if(cin.eof())
        {
            cout << endl;
            break;
        }
        vector<string>* x = trie.AStarSearch(s, 20);
        unsigned int i;
    
        for(i=0;i<x->size();i++)
        {
            cout << x->at(i) << endl;
        }    
    }
    
    return 0;
}
