A software solution to permutate words from a phrase, provided via input.

### Software requirements
- Generate all possible permutations of input strings
- Calculate the total number of permutations
Nice to have
- Provide the permutations in the lexicographic order.
- It could be interesting to have generified solution, that could permutate any kind of objects, implemented, though, only for the strings.

### Non-functional requirements
- Java project
- No third-party libraries for the actual permutations algorithm
- Relevant use cases should be described and tested

### TBC (Assumptions)
- Command-line tool is the only expected interface.
- Input data is to be provided only in the encodings, supported by JDK: https://docs.oracle.com/en/java/javase/11/intl/supported-encodings.html#GUID-187BA718-195F-4C39-B0D5-F3FDF02C7205
- Lexicographic sorting can be done according to the executing machine Locale setup.
- Supported locales (and the relevant coding) are the Java supported locales: https://www.oracle.com/java/technologies/javase/jdk11-suported-locales.html

### TBA (Decisions)
- The CLI tool is to follow UNIX philosofy - do one thing, but do it well.
  - It will take the string from the _stdin_. The string terminator is `\n`.
  - It will output the permutations to the _stdout_, separated by `\n`
- Using previous decisions, the calculation of number of permutations can be done, using _wc_ tool from GNU coreutils. That would also keep API concise and unified.

### Algorithm Analisys
According to Sedgewick, https://www.princeton.edu/~rblee/ELE572Papers/p137-sedgewick.pdf, the optimal lexicographically sorted permutations generation algorithm is introduced by Ord-Smith.
This algorithm will be implemented.

### Configuration
- No configuration at this point.
- Future improvements - string separator could be configured

### Use cases
- Get permutations
- Get count
- Use custom locale

