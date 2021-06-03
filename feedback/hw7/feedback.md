### Design: 1/3

### Documentation & Specification (including JavaDoc): 2/3

### Code quality (code and internal comments including RI/AF when appropriate): 1/3

### Testing (test suite quality & implementation): 1/3

### Mechanics: 3/3

#### Overall Feedback

Good start, though you should take a second look at how you plan to approach
things.

#### More Details

- You never document your generic types in class documentation.

- Node inner static class generic type should have different name from outer
  class type name for readability.

- `node` class should be `Node`, PascalCase. Same goes for `edge`.

- Your CampusMap is incomplete. But based on what I see:
    - You should not be re-constructing the same graph every time you find the
      path between two buildings. 
    - You should not have to iterate through every building to find if it
      exists.
    - You do not know that start and end are nodes in your graph at the end of
      what you currently have for your pathfinding method.

- Your test suite is lacking in coverage. Here's a few ideas for interesting
  test cases:
    - Cyclic graphs with path finding.
    - Paths where the difference between BFS and Dijkstra's algorithm is
      apparent.
    - Graphs with multiple edges of varying costs between the same two nodes.
