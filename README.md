# BackwardRegex

BackwardRegex is a project that provides a tool that takes in a Regular Expression and

Program :

```kotlin
fun main() {

    val decimalNumberRegex = """^(-[1-9]\d*|0)(\.\d*[1-9])?$""".toRegex()

    val compiledRegex = BackwardRegexCompiler().generate(decimalNumberRegex)

    for (i in 1..10)
        println(">\t" + compiledRegex.generateMatchingText())
}
```

Output :

```
>	-5
>	-13
>	-649.2
>	-358913572
>	-658.79
>	0.51161317
>	0.5294
>	0.93823
>	-917408.61964
>	-9482954
```