# QueryAutoSuggestion
## Introduction
Query auto suggestion uses a trie data structure to store the previous queries. The retreival of the best query is done using the A* search algorithm with a hueristic function.

## Usage
The trie class contains methods:
- void insert(String)
- void buildTrie(filename)
- ArrayList<String> AStarSearch(String)
The usage is very simple. Usage:
```java
/* Java */
import com.krupo.Trie;

public static void main(String []args)
{
        Trie t = new Trie();
        t.buildTrie(<list of queries>);
        Arraylist<String> x = t.AStarSearch(S);
}
```
```cpp
/* cpp */
#include "cpp/trie.h"
int main()
{
        Trie t = new Trie();
        t.buildTrie(<list of queries>);
        Arraylist<String> x = t.AStarSearch(S);
}
```
TODOS:
- [ ] Make the trie model serializable so that the model can be stored on the disk rather than building it everytime.