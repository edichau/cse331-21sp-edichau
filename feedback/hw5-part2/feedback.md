### Written Answers: 8/10

Graph Representations:
 - "coherent as a graph" is kinda vague - what does this mean? How do these implementations
 impact the runtime complexity or space complexity of different operations?

Test Design:
 - Good

### Design: 3/3

- Java convention is for class names to be in `PascalCase`, so `Node` and `Edge` instead of `node` and `edge`.
- The fields in your classes should probably be private.

### Documentation & Specification (including JavaDoc): 2/3

- You need class comments on the node and edge classes.
- The explanation of `listChildren`'s return type is insufficient - what are the mapping's keys and values? Just
saying "a map" doesn't help a client understand how to use it.
- Missing some details about what happens in cases like duplicate nodes - how is your graph specified to
respond/behave in these cases?

### Code quality (code and internal comments including RI/AF): 2/3

- None of your classes have an AF or RI.
- `node#equals` could just `return n.getName().equals(this.name)` instead of writing the `if` statement.
    - Similar for `edge#equals`
- `n1` and `n2` aren't super helpful field names, why not just use `start` and `end`?
- Your `DEBUG` flag should be turned off when you turn in your code, or it might be too slow for us to test.
    - This is _super important_ for hw6 and hw7.
- Your `hashCode` in Graph is not super high-quality, `equals` uses both the node and edge data, while hashCode
only involves the node data.
- Your code is fairly inconsistently indented - try to clean up the file to make it more readable.
- `removeNode`: what happens to the edges that point to the node you're removing? Right now - they're just left
in the graph pointing to a nonexistent node.
- `listChildren`: is there ever more than one key in the HashMap being returned? This seems redundant as-written.

### Testing (test suite quality & implementation): 2/3

- Your test driver needs to sort the output of ListNodes and ListChildren before printing. See the hw spec for
details about the required sorting order.
- `n1`, `n2`, etc.. aren't particularly helpful variable names in your test class, and make it harder to read your tests.
- Almost all of your script tests don't actually check anything - they're basically only checking that your code
doesn't crash when adding nodes or edges. You probably need to add ListNodes or ListChildren commands to your scripts
to verify that the nodes/edges are actually being added properly, since that's what you're trying to test for.
- You could benefit from a few more complex test cases, like making sure a large (5-8 node) graph can be built with a number
of edges, that reverse edges work properly, that the graph works well in the case of cycles, islands, etc...

### Mechanics: 3/3

#### Overall Feedback

- Overall, you're on the right track. It looks like your main areas to focus on are:
    - General code cleanup - simplify, indent properly, use Java naming conventions and better variable names, etc...
    - Make sure you follow the hw spec instructions carefully.
    - Improve your test suite so you have higher confidence that your graph implementation is correct. This will save
    you lots of time on future assignments.
