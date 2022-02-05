### Design: 3/3

### Documentation & Specification (including JavaDoc): 2/3

### Code quality (code and internal comments including RI/AF when appropriate): 3/3

### Testing (test suite quality & implementation): 3/3

### Mechanics: 3/3

#### Overall Feedback

#### More Details
The graph building routine should be factored out into its own method.
Otherwise, you cannot test the graph building on arbitrary data files or some
mock parser.

- Missing generic type documentation for Node, Edge, and Graph, and Path
