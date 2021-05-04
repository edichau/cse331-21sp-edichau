### Written Answers: 19/20

- For #6, note that a list is mutable, and that if `cards` is stored in our
representation the client could modify `cards` after initialization.

### Design: 2/3

- Class names should be capitalized, like Edge.java and Node.java.

- Edge and Node might want equals()/hashCode() overridden for comparing to one another.

- Are you sure you want to have multiple top level classes? In general, it's bad style
to have a single abstraction spread across multiple top level classes.

- Your graph ADT should not implement any pathfinding. Clients should handle pathfinding themselves
if they want it.

### Documentation & Specification (including JavaDoc): 3/3

### Testing (test suite quality & implementation): 2/3

- In addition to testing standard cases for operations using something like the 0,1,many heuristic,
it's also important to test interesting edge cases that might break your implementation. Examples
of this include cyclical graphs, alphabetical ordering or nodes, etc.

### Code quality (code stubs/skeletons only, nothing else): 3/3

### Mechanics: 3/3

#### Overall Feedback

- Good work.

#### More Details

- None.
