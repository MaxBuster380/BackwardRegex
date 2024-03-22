# BackwardRegex

BackwardRegex is a project that provides a tool that takes in a Regular Expression and

Program :

```kotlin
fun main() {

    val decimalNumberRegex = """^(-[1-9]\d*|0)(\.\d*[1-9])?$""".toRegex()

    val backwardRegexCompiler = BackwardRegexCompiler()
    val compiledRegex = backwardRegexCompiler.generate(decimalNumberRegex)

    for (i in 1..10)
        println(">\t" + compiledRegex.generateMatchingText())
}
```

Output :

```
>	-724410.8007109022
>	0.5689
>	0
>	0.3
>	-7515200120
>	-3061417
>	-453169
>	-5785.6972224017
>	0.72937
>	-55229.34735278
```