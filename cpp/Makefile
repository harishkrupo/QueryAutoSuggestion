all: trie.o main.o
	g++ -o trie trie.o main.o

trie.o : trie.cpp
	g++ -c -g trie.cpp

main.o: main.cpp

	g++ -c -g main.cpp

clean: 
	-rm trie trie.o main.o
