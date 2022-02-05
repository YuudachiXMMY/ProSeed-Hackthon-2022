### Written Answers: 19/26

# Representation Invariants
## 2b Changes
In part 2b, the changes required are limited to the following methods:
`checkRep`, `equals`, `toString`, `getExpt`, and `hashCode`.

## 2c Changes
In part 2c, the changes required are limited to the following methods: the
constructor, and `checkRep`.


# `checkRep`
## Immutable ADT Needs `checkRep`
Immutability is a property of the specification, and `checkRep` does not assume
the specification was correctly implemented.  So, in general, regardless of
whether or not they are immutable, ADTs need calls to `checkRep` at the
beginning and end of all public methods.


## Observer Methods Need `checkRep`
`checkRep` does not assume that methods were implemented properly, regardless of
how trivial or innocuous they seem.  So, in general, even observer methods need
calls to `checkRep` at their beginning and end.

## Immutability Guaranteed By The Compiler
Final immutable fields cannot be modified after they are instantiated; the
compiler would complain about any attempt to do so.  Therefore, we can reason
that `RatNum` and `RatTerm` cannot contain any bugs with regard to the
representation invariant as long as we ensure the coherency of the data at
initialization.  Therefore, `RatNum` and `RatTerm` are special cases that do not
need calls to `checkRep` at the beginning and end of every public method, aside
from the constructor.
### Code Quality: 3/3
Clean code and good implementation!

### Mechanics: 3/3

### General Feedback
Good job in this homework!

### Specific Feedback
## Missing Calls to checkRep in RatPoly and/or RatPolyStack
Missing calls to `checkRep` at the beginning and end of every public method in `RatPoly` and/or `RatPolyStack`.
