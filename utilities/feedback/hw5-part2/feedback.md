### Written Answers: 10/10

### Design: 3/3

### Documentation & Specification (including JavaDoc): 3/3

### Code quality (code and internal comments including RI/AF): 3/3

### Testing (test suite quality & implementation): 3/3

### Mechanics: 3/3

#### Overall Feedback

- Great work this week! Make sure to take a look at my couple comments for next week! Great
job putting together a robust test suite!

#### More Details

- Your RI should also state if you can have null edges, if values in your map can be null etc.

- You were not supposed to use generics on this portion of the homework assignment. We asked that
you simply implement the graph with nodes that are Strings and edge labels that are Strings
as well. Make sure to follow directions closely in the fututre.

- Modifying the Set returned by keySet() will actually modify the underlying Map,
so just returning the keySet() will lead to rep exposure.

- Make sure to comment all your fields, even those in inner classes
