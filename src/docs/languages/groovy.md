# Groovy

**Apache Groovy** is an object-oriented, imperative, scripting programming language.

#### Lists and Maps

```groovy
def list = [1, 2, 3]
def map = [1: a, 2: b, 3: c]
```

#### Ranges

```groovy
def string = "abcdefghij"
```

| Range             | Result    |
|:-----------------:|:---------:|
| `string[1..5]`    | bcdef     |
| `string[1..-1]`   | bcdefghij |
| `string[-1..1]`   | jihgfedcb |
| `string[0, 2, 5]` | acf       |

#### Functions

```groovy
def function(arg) {
    print arg
}
```

#### Closures
Closure is an anonymous function

```groovy
def closure = {
    x -> print x
}
```

:-1: TODO: Default closures

#### Classes

```groovy
class Person {
    def name
    def age
}
```
Default constructor, setters and getters:
```groovy
def person = new Person(name: "Alex", age: 23)
def name = person.getName()
person.setName("Alex Fokin")
```
Immutable classes:
```groovy
@Immutable
class ImmutablePerson {
    String name
    Integer age
}
```

#### Operators

| Operator | Usage              | Meaning                       |
|:--------:|:------------------:|:-----------------------------:|
| `?:`     | `def b = a ?: b`   | returns b, if a is null       |
| `?.`     | `user?.name`       | returns null, if user is null |
| `*.`     | `threads*.start()` | do `start` for each thread    |

#### Files

```groovy
new File('.').eachFile {
    println it
}
// Result: 
//  ./README.md
//  ./pom.xml
//  ./.gitignore
//  ./.git
//  ./src

new File('./src/groovy').eachFileRecurse {
    println it
}
// Result:
//  ./src/groovy/observer
//  ./src/groovy/observer/AlgorithmsObserver.groovy
//  ./src/groovy/observer/GMain.groovy
//  ./src/groovy/observer/AlgorithmsStatuses.groovy
//  ./src/groovy/observer/Algorithm.groovy

def printWriter = new File('helloworld.txt').newPrintWriter()
printWriter.println("Hello, World!")
```
#### SQL

TODO: SQL

#### XML

TODO: XML

