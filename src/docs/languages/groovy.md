![Apache Groovy Logo][logo]

**Apache Groovy** is an object-oriented, imperative, scripting programming language. 

[![Go Back][backmark]][backlink]
[![Go Home][homemark]][homelink]

## Contents

1. [Lists and Maps](#Lists-and-Maps)
2. [Ranges](#Ranges)
3. [Functions](#Functions)
4. [Closures](#Closures)
5. [Classes](#Classes)
6. [Operators](#Operators)
7. [Files](#Files)
8. [SQL](#SQL)
9. [XML](#XML)

## Lists and Maps

```groovy
def list = [1, 2, 3]
def map = [1: a, 2: b, 3: c]
```
## Ranges

```groovy
def string = "abcdefghij"
```

| Range             | Result    |
|:-----------------:|:---------:|
| `string[1..5]`    | bcdef     |
| `string[1..-1]`   | bcdefghij |
| `string[-1..1]`   | jihgfedcb |
| `string[0, 2, 5]` | acf       |

## Functions

```groovy
def function(arg) {
    print arg
}
```

## Closures

Closure is an anonymous function

```groovy
def closure = {
    x -> print x
}
```

## Classes

```groovy
class Person {
    def name
    def age
}
```

Default constructor, setters and getters are available:

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

## Operators

Some operators:

Operator  | Usage                               | Meaning                                   
:--------:|:-----------------------------------:|:---
`?:`      | `def b = a ?: b`                    | returns `b`, if `a` is null
`?.`      | `person?.name`                      | returns null, if `person` is null
`*.` `*:` | `persons*.getName()`                | gets name for each person in `persons`
`.@`      | `person.@name`                      | force usage of `name` instead of getter
`.&`      | `def getter = person.&getName()`    | stores the reference of the method
`~`       | `Pattern pattern = ~'regexp'`       | returns a Pattern with regular expression
`=~`      | `Matcher matcher = text =~ pattern` | returns a Matcher against the `text` and `pattern`
`==~`     | `Boolean = text ==~ pattern`        | matches the `text` with the regular expression (match must be strict)
`..`      | `0..7`, `2..<9`, `-1..4`            | see [Ranges](#Ranges)
`<=>`     | `1 <=> 2`                           | compares elements with `compareTo` method
`in`      | `person in company`                 | calls `isCase` (`contains`) method
`is`      | `person1.is(person2)`               | compares references equality
`as`      | `person as Student`                 | coerces (cast) `person` to a `Student`     
`()`      | `phone(number)`                     | calls `.call()` method without `.call`

Groovy allows you to **overload** the various operators.
List of the operators and their corresponding methods:

Operator   | Method     
:---------:|:---
`+`        | `a.plus(b)`
`-`        | `a.minus(b)`
`*`        | `a.multiply(b)`
`/`        | `a.div(b)`
`%`        | `a.mode(b)`
`**`       | `a.power(b)`
`&`        | `a.and(b)`
*pipe*     | `a.or(b)`
`^`        | `a.xor(b)`
`as`       | `a.asType(b)`
`in`       | `a.isCase(b)`
`()`       | `a.call()`
`a[b]`     | `a.getAt(b)`
`a[b] = c` | `a.putAt(b, c)`
`>>`       | `a.rigntShift(b)`
`<<`       | `a.rightShift(b)`
`>>>`      | `a.rigthShiftUnsigned(b)`
`+a`       | `a.positive()`
`-a`       | `a.negative()`
`~a`       | `a.bitwiseNegate()`
`++`       | `a.next()`
`--`       | `a.previous()`

## Files

### Getting files:

Now `File` have next Groovy methods:

```
eachFile
eachFileRecurse
eachFileMatch
```

For example:

```groovy
new File('.').eachFile {
    println it
}
```

Should print something like this:

```
./pom.xml
./.gitignore
./.git
./src
./readme.md
```

### Write file:

```groovy
def file = new File("filename")
```

Writing to the `file`:

```groovy
file.write line 
    // or
file << line
```

Writing to the `file` with a writer:

```groovy
file.withWriter('UTF-8') { writer -> 
    writer.write(line)
}
```

Or just setting new value to `file.text` property:

```groovy
file.text = text
```

### Read file

Reading from the `file` to an array of lines:

```groovy
def lines = file.readLines()
```

Reading from the `file` with a reader:

```groovy
file.withReader { reader -> 
    while (line = reader.readLine()) {
        println line
    }
}
```

Or just getting value from `file.text` property:

```groovy
def text = file.text
```

### Remove file

```groovy
file.delete()
```

## SQL

### Getting started:

```groovy
import groovy.sql.Sql

sql = Sql.newInstance(adress, username, password, driver)
```

### Execute:

**Creating** table with `execute` method:

```groovy
sql.execute """
    create table persons (
        id integer not null,
        name varchar(100)
    )
"""
```

**Inserting** values into table with `execute` method:

```groovy
sql.execute """
    insert into persons (name)
    values (${person.getName()})
"""
```

**Updating** values in table with `executeUpdate` method:

```groovy
sql.executeUpdate """
    update persons set
        name = ${person.getName()}
    where id = ${person.getId()}
"""
```

### Select:

Selecting with `rows`:

```groovy
def rows = sql.rows("""
    select * from persons
    where name like 'Alex%'
""")

println rows.join('\n')
```

Select in `eachRow` method:

```groovy
sql.eachRow("select * from persons") { person ->
    println "${person.id} - ${person.name}"
}
```

### Close connection:

```groovy
sql.close()
```

## XML

### Build XML

Builder initialization:

```groovy
import groovy.xml.MarkupBuilder

def builder = new MarkupBuilder()
```

Building:

```groovy
builder.html() {
    head() {
        title('Page title')
    }
    body() {
        div('class': 'main') {
            p('Hello, World!')
        }
    }
}
```

### Parse XML

Parser initialization:

```groovy
import groovy.util.XmlParser

def parser = new XmlParser()
```

Parsing:

```groovy
def doc = parser.parse('helloworld.xml')

println doc.body.div[0].p[0]   // returns Node
println doc.body.div[0].p      // returns list of Nodes
println doc.body.div["@class"] // returns list of values div's class-attribute 
```

[logo]:     https://github.com/FokinAlex/knowledgebase/blob/master/src/resources/logos/groovy.png?raw=true
[homelink]: https://github.com/FokinAlex/knowledgebase
[homemark]: https://github.com/FokinAlex/knowledgebase/blob/master/src/resources/marks/home.png?raw=true
[backlink]: https://github.com/FokinAlex/knowledgebase/blob/master/readme.md
[backmark]: https://github.com/FokinAlex/knowledgebase/blob/master/src/resources/marks/backward.png?raw=true
