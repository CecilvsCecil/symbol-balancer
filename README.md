`SymbolBalance` reads through a code (Java) file and checks for simple syntatical errors.

The first method, `setFile`, takes in a String representing the path to the file that should be checked.

The second method, `checkFile`, reads in the file character by character and checks to make sure that all { }'s, ( )'s, [ ]'s, " "'s, and /\* \*/'s are properly balanced. Characters within literal strings (" ") and comment blocks (/\* \*/) are ignored.

Single line comments (those that start with //), literal characters (things in single quotes), and the diamond operator(<>) are not handled in this version.

There are three types of errors that can be encountered:

* The file ends with one or more opening symbols missing their corresponding closing symbols.
* There is a closing symbol without an opening symbol.
* There is a mismatch between closing and opening symbols (for example: { [ } ] ).

Once an error is encountered, a `BalanceError` object is returned containing appropriate error information. Each error type has its own class that descends from `BalanceError` and each has its own required parameters:

- Symbol mismatch after popping stack: return `MismatchError(int lineNumber, char currentSymbol, char symbolPopped)`
- Empty stack popped: `EmptyStackError(int lineNumber)`
- Non-empty stack after parsing entire file: `NonEmptyStackError(char topElement, int sizeOfStack)`

If no error is found, `null` is returned.
