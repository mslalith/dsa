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


<details>
<summary>Traversal</summary>

#### Breadth First Search (BFS)
- Time Complexity: O(V + 2E)
- Space Complexity: O(V)

#### Depth First Search (DFS)
- Time Complexity: O(V + E)
- Space Complexity: O(V)

</details>


<details>
<summary>Detect Cycle</summary>
<br>

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


<details>
<summary>Shorted Path</summary>
<br>

Refer [Shortest Path Demo](/src/main/kotlin/dev/mslalith/graph/demo/ShortestPathDemo.kt) for all implementations

#### Relaxation

If a better distance to `v` is found, consider the shortest distance to `v` is from `u` and update the distance.

```kotlin
if (distance[u] + weight < distance[v]) {
  distance[v] = distance[u] + weight
}
```


#### [Dijkstra's Algorithm](/src/main/kotlin/dev/mslalith/graph/impl/algorithms/shortedpath/DijkstraAlgorithm.kt)

- Finds the shortest path from single source to all vertices.
- ❌ Dijkstra's Algorithm will not work with -ve weights and goes into infinite loop.
- Time Complexity: O(E * logV)

##### Intuition

- Find the smallest distance using `PriorityQueue` and apply relaxation for each neighbour.
- Eventually `PriorityQueue` would've visited all the vertices via the shortest way possible.


#### [Bellman Ford Algorithm](/src/main/kotlin/dev/mslalith/graph/impl/algorithms/shortedpath/BellmanFordAlgorithm.kt)

- Finds the shortest path from single source to all vertices.
- Only works with Directed Graph. When an Undirected Graph is given, it needs to be converted to a Directed Graph.
- Works with -ve weights and helps in detecting -ve cycles.
- To detect a -ve cycle, Vth iteration would still decrease the distance.
- Time Complexity: O(V * E)

##### Intuition

- Apply relaxation for each vertex neighbours for `V - 1` times.
- In each iteration, a new shortest distance from `u -> v` is found or updated.
- Since we have `V` vertices, looping for at-least `V - 1` times is required to find shortest distance between all vertices.


#### [Floyd Warshall Algorithm](/src/main/kotlin/dev/mslalith/graph/impl/algorithms/shortedpath/FloydWarshallAlgorithm.kt)

- Finds the shortest path from multiple sources to all vertices.
- Only works with Directed Graph. When an Undirected Graph is given, it needs to be converted to a Directed Graph.
- Works with -ve weights and helps in detecting -ve cycles.
- To detect a -ve cycle, one of the vertex self loop would be negative.
- Time Complexity: O(V ^ 3)

##### Intuition

- Finds the distance between `u` to `v` by going via all other vertices.
  - `distance[i][j] = min(distance[i][j], distance[i][k] + distance[k][j])`


</details>


<details>
<summary>Minimum Spanning Tree (MST)</summary>
<br>

Refer [Minimum Spanning Tree Demo](/src/main/kotlin/dev/mslalith/graph/demo/MinimumSpanningTreeDemo.kt) for all implementations

- Spanning Tree means a tree having `V` vertices and `V - 1` edges and all vertices are reachable from each other.
- Spanning Tree will not form a cycle.
- A graph can have multiple spanning trees.
- The spanning tree having minimum cost (path sum) is called Minimum Spanning Tree (MST).


#### [Prim's Algorithm](/src/main/kotlin/dev/mslalith/graph/impl/algorithms/spanningtree/PrimsAlgorithm.kt)

- Finds MST.
- This is a Greedy algorithm.
- Time Complexity: O(E * logE)

##### Intuition

- Find the smallest distance using `PriorityQueue` and forms an edge to a neighbour having the smallest weight.
- Eventually all the vertices will be visited and smallest edge to it's neighbour would be formed.
- This ensures that each vertex is reachable from each other without forming a cycle.


#### [Kruskal's Algorithm](/src/main/kotlin/dev/mslalith/graph/impl/algorithms/spanningtree/KruskalAlgorithm.kt)

- Finds MST.
- This is a Greedy algorithm.
- Time Complexity: O(E * logE) + O(E * 4α * 2)

##### Intuition

- Sort all edges by weight. Picks the edge having the smallest weight which upon adding, the graph doesn't form a cycle.
- `Disjoint Set` is used to check upon adding an edge between `u` and `v` whether a cycle will be formed.
- Eventually all the vertices will be visited and smallest edge to it's neighbour would be formed.
- This ensures that each vertex is reachable from each other without forming a cycle.


</details>


</details>
