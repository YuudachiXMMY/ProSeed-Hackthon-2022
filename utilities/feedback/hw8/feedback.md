## Program operation (correctness) and extra credit

### Correctness Score: 3/3

### Extra Credit Score: -/3

#### Program operation/extra credit feedback

None.

## Code quality and implementation

### Design: 2/3

### Code quality: 2/3

### Mechanics: 3/3

#### Code Review Feedback

- You should make a separate Edge interface you can use to encapsulate all the edge information for a given edge into a single object

- It's bad style to have huge methods in App that do a lot of processing that's specific to a single child component, like onEdgeDraw. Instead, refactor your code so that that code is in the relavent component (EdgeList in the case of onEdgeDraw) and the methods you pass as props to the child components simly update the state of App

- You should add comments to your code to improve understandability

