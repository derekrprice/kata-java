# Java Kata
To run this kata with the default input file, clone this project to a machine
with Gradle and an Java 8 JDK installed.  Then:

```gradle run --args='Catalog.csv'```

To parse different, or even multiple, catalog files, simply add them as
space-delimited file names to the "--args=" argument, inside of the single quotes.
To run the project tests:

```gradle test```

## Assumptions
The problem definition left some leeway for interpretation, so these are the
additional assumptions that I made in the solution:
- Input lines with errors, including empty or non-numeric part #s, may be ignored.q
- "Paper related products" are all those with the word "paper" in the description
or the category field.  It would be a trivial exercise to extend that search with
other keywords, if required.
- For the purposes of this exercise, storage is only in-memory.  Parsing is done in
a memory-efficient manner, so if a larger data set needs to be processed, swapping
out the storage layer with a persistence layer such as a DB should be easy.

## Problem Definition
Write a small program which will load a sample catalog (a csv file) and return all
the paper related products which are sorted by sales price (after discount if there
is any). Feel free to handle duplicate or invalid input as you feel is appropriate.

### Input Data format – CSV
```
Category, Part #, Description, UOM, price, discount
```

### Sample output:
```
826609, Staples Adding Machine, Register and calculator Rolls, 1-ply, 2-1/4”x130’, Each, $0.30/Roll
FMC013, Toilet Paper, $0.45/Each
135848, SPLS 8.5”x11” Copy Paper, Case, $28.30/Box
```