import java.io.File

fun main()
{
    val file1 = File("/home/student/Facultate/An2/PP/pp-1207a-homeworks-TimofteConstantinAC/Laborator3/Exemplul4/src/main/kotlin/Dictionar.txt")
    val file3 = File("/home/student/Facultate/An2/PP/pp-1207a-homeworks-TimofteConstantinAC/Laborator3/Exemplul4/src/main/kotlin/Dictionar1.epub")
    val dictionar = hashMapOf<String, String>()

    dictionar.put("gloves", "manusi")

    file3.readText().split(",").forEach { entry ->
        val (key, value) = entry.split("->")
        dictionar[key.trim('"')] = value.trim('"')
    }

    val poveste = "Once hello upon a time there was an old woman who loved baking gingerbread. She would bake gingerbread cookies, cakes, houses and gingerbread people, all decorated with chocolate and peppermint, caramel candies and colored ingredients."

    val words1 = poveste.split(" ")
    println("Cuvintele din poveste [${words1.count()}]:")
    println(words1.joinToString(" "))

    val words2 = mutableListOf<String>()
    for (word in words1)
    {
        words2.add(word.trim(',', '.'))
    }
    println()

    val file2 = File("/home/student/Facultate/An2/PP/pp-1207a-homeworks-TimofteConstantinAC/Laborator3/Exemplul4/src/main/kotlin/Poveste_tradusa.txt")
    println("Povestea tradusa ar suna cam asa:")

    val writer = file2.bufferedWriter()

    for (item in words2)
    {
        val word = if (dictionar.containsKey(item))
        {
            dictionar[item]
        }
        else
        {
            "[$item]"
        }
        print("$word ")
        writer.write(word)
        writer.write(" ")
    }

    writer.close()
}


