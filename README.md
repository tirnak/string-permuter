A software solution to permute words from a phrase, provided via input.

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
- There won't be string sanitization, as it is not requested. Also, "Split the string into tokens", not words, so non-alphabetic characters are expected.

### Configuration
- No configuration at this point.
- Future improvements - string separator could be configured

### Usage
Create a Java archive, using maven
```
$ sudo apt-get install maven # if needed
$ mvn package
```
Resulting JAR artefact would be located in the `./target` folder. 

### Use cases
- Get permutations \
`$ echo "This is a test" | java -jar target/string-permutator-*.jar`
- Get count \
`$ echo "This is a test" | java -jar target/string-permutator-*.jar | wc -l`
- Use custom collation
  - Compare English and (accents for) French collations.
```
rm  permutations-*
echo "peach péché pêche sin" | java  -Duser.language=fr -Duser.country=FR -jar target/string-permutator-*.jar > permutations-fr.txt
echo "peach péché pêche sin" | java  -Duser.language=en -Duser.country=US -jar target/string-permutator-*.jar > permutations-en.txt
diff permutations-*
```

NOTE! By default JDK uses default collation rules for many locales. In order to provide a locale for the language,
add collation rules to the CustomCollationsBootstrap. Rules should comply with the [grammar](https://docs.oracle.com/javase/8/docs/api/java/text/RuleBasedCollator.html)
In this project, a collation for traditional Spanish was added. 
```
rm  permutations-*
echo "chalina curioso llama luz" | java  -Duser.language=es -Duser.country=ES  -jar target/string-permutator-*.jar > permutations-es.txt
echo "chalina curioso llama luz" | java  -Duser.language=en -Duser.country=US -jar target/string-permutator-*.jar > permutations-en.txt
diff permutations-*
```
```
rm  permutations-*
echo "Muffler Müller MXSystems MySQL" | java  -Duser.language=de -Duser.country=DE  -jar target/string-permutator-*.jar > permutations-de.txt
echo "Muffler Müller MXSystems MySQL" | java  -Duser.language=en -Duser.country=US -jar target/string-permutator-*.jar > permutations-en.txt
diff permutations-*
```

