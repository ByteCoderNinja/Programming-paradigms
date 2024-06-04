class functor(val Map: MutableMap<Int, String>)
{
    fun map(): functor
    {
        for (i in Map.keys)
        {
            Map[i] = "Test ${Map[i]}"
        }
        return functor(Map)
    }

    fun toPascalCase(): functor
    {
        for (i in Map.keys)
        {
            /*val words = Map[i]?.split(" ")?.toMutableList()
            if (words != null)
            {
                for (j in words.indices)
                {
                    if (words[j][0].isLowerCase())
                    {
                        words[j] = words[j][0].titlecase() + words[j].substring(1)
                    }
                }
                val word = words.joinToString("")
                Map[i] = word
            }*/
            Map[i]?.split(" ")?.map {it.capitalize()}?.joinToString("")?.let { Map.put(i, it) }
        }
        return functor(Map)
    }
}

fun main()
{
    val Map : MutableMap<Int, String> = mutableMapOf(
        1 to "hello world",
        2 to "laborator 12",
        3 to "problema 4"
    )

    println(functor(Map).map().toPascalCase().Map)
}