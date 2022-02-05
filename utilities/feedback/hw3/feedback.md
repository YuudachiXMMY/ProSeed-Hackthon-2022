### Written Answers: 6/6

### Code Quality: 2/3

### Mechanics: 3/3

### General Feedback
- When selecting a greeting in `RandomHello`, the best style would use the length
of the array to specify the maximum value for the random integer generation:
```
String nextGreeting = greetings[rand.nextInt(greetings.length)];
```
Notice how this benefits us later on if we wanted to change the number of
possible greetings in the array.

- The standard style for class names is PascalCase with an upper case first
letter, not camelCase. The name `compareBalls` violates this style.


### Specific Feedback
- Often, you are not in control of the source code for a class, or the object
does not have a natural ordering, so it would be stylistically incorrect to
implement `Comparator` insde the Ball class. Try implementing the `Comparator`
in a new class or in the same class where the sorting is taking place.

- It would be very time consuming to calculate the total volume
inside `BallContainer` every time the method is called. Consider using
a private variable to store this value and using add and remove methods
appropriately to update this value.
