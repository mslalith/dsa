<details>
<summary>Stacks</summary>
<br>

[Problems](/src/main/kotlin/dev/mslalith/stacks/problems)

### Build Stack using
- [ArrayList](/src/main/kotlin/dev/mslalith/stacks/impl/StackUsingArrayList.kt)
- [LinkedList](/src/main/kotlin/dev/mslalith/stacks/impl/StackUsingLinkedList.kt)


</details>


<details>
<summary>Trees</summary>
<br>

[Solved Problems](/src/main/kotlin/dev/mslalith/trees/problems)

### Traversal
- [Iterative](/src/main/kotlin/dev/mslalith/trees/traversal/IterativeTreeTraversal.kt)
- [Recursive](/src/main/kotlin/dev/mslalith/trees/traversal/RecursiveTreeTraversal.kt)

</details>


<details>
<summary>Graph</summary>
<br>

[Solved Problems](/src/main/kotlin/dev/mslalith/graph/problems)


### Traversal

#### Breadth First Search (BFS)
- Time Complexity: O(V + 2E)
- Space Complexity: O(V)

#### Depth First Search (DFS)
- Time Complexity: O(V + E)
- Space Complexity: O(V)


### Detect Cycle

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

</details>
