import java.io.File

fun main()
{
    val input = "input.txt"
    val output_file = "output.txt"

    var content = File("/home/student/Facultate/An2/PP/pp-1207a-homeworks-TimofteConstantinAC/Laborator12/Tema2/input.txt").readText().trim()
    content = content.substring(1)
    val contents = content.split(" ")
    val caesarContent = mutableListOf<String>()
    for (word in contents)
    {
        if (word.length in 4..7)
        {
            val newWord = StringBuilder()
            for (j in word)
            {
                var newChar = 'a'
                if (j in 'a'..'v' || j in 'A'..'V')
                {
                    newChar = (j.code + 3).toChar()
                }
                when (j)
                {
                    'x' -> newChar = 'a'
                    'y' -> newChar = 'b'
                    'z' -> newChar = 'c'
                    'X' -> newChar = 'A'
                    'Y' -> newChar = 'B'
                    'Z' -> newChar = 'C'
                }
                newWord.append(newChar)
            }
            caesarContent.add(newWord.toString())
        }
        else
        {
            caesarContent.add(word)
        }
    }
    val output =  caesarContent.joinToString(" ")
    println(content)
    println(output)
    File(output_file).writeText(output)
}