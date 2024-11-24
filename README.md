# Stacks

- build Stack using
  - [ArrayList](https://github.com/mslalith/dsa/blob/main/stacks/impl/StackUsingArrayList.java)
  - [LinkedList](https://github.com/mslalith/dsa/blob/main/stacks/impl/StackUsingLinkedList.java)

- [Problems](/src/main/kotlin/dev/mslalith/stacks/problems)



# Trees

- Traversal
    - [Iterative](https://github.com/mslalith/dsa/blob/main/trees/traversal/IterativeTreeTraversal.java)
    - [Recursive](https://github.com/mslalith/dsa/blob/main/trees/traversal/RecursiveTreeTraversal.java)

- [Problems](/src/main/kotlin/dev/mslalith/trees/problems)



# Graph

- [Problems](/src/main/kotlin/dev/mslalith/graph/problems)


## Traversal

#### Breadth First Search (BFS)
  - Time Complexity: O(V + 2E)
  - Space Complexity: O(V)

#### Depth First Search (DFS)
  - Time Complexity: O(V + E)
  - Space Complexity: O(V)


## Detect Cycle

Refer [Detect Cycle Demo](/src/main/kotlin/dev/mslalith/graph/demo/DetectCycleDemo.kt) for all implementations

#### DFS
  - Undirected Graph
    - Time Complexity: O(V + 2E)
    - Space Complexity: O(V)
  - Directed Graph
    - Time Complexity: O(V + E)
    - Space Complexity: O(V)


#### BFS
  - Undirected Graph
    - Time Complexity: O(V + 2E)
    - Space Complexity: O(V)
  - Directed Graph
    - Not supported


#### Kahn's Algorithm
  - Undirected Graph
    - Not supported (this algo relies on Topological Sort which doesn't work with Undirected Graph)
  - Directed Graph
    - Time Complexity: O(V + E)
    - Space Complexity: O(V)
