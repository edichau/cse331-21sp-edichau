### Written Answers: 17/26

### Code Quality: 3/3

### Mechanics: 3/3

### General Feedback

### Specific Feedback
- for 1.2, the method specification lacks `@modifies` this, so it would violate the specification to modify the abstract state of this.
- for 1.3, `final` immutable fields cannot be modified after they are instantiated; the compiler would complain about any attempt to do so.  Therefore, we can reason that `RatNum` and `RatTerm` cannot contain any bugs with regard to the representation invariant as long as we ensure the coherency of the data at initialization.`int` is a strictly immutable data type
- for 2.3, need to also change `checkRep`
- for 3.1, 
    - Immutability is a property of the specification, and checkRep does not assume the specification was correctly implemented.  So, in general, regardless of whether or not they are immutable, ADTs need calls to checkRep at the beginning and end of all public methods.
    - `checkRep` does not assume that methods were implemented properly, regardless of how trivial or innocuous they seem.  So, in general, even observer methods need calls to `checkRep` at their beginning and end.
- 3.2 is not answered
- missing inv in line 399 `RatPoly.java`
- `RatPolyStack` has not been implemented
- Missing calls to `checkRep` at the beginning and end of every public method in `RatPoly` and/or `RatPolyStack`.
