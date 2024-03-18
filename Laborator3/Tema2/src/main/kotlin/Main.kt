package org.example
import java.io.File

fun main()
{
    val file = File("/home/student/Facultate/An2/PP/pp-1207a-homeworks-TimofteConstantinAC/Laborator3/Tema2/src/main/kotlin/ebook.epub")
    val text = file.readText()
    val regex = Regex("\\b\\d+\\b")
    val newText = text.replace(regex, " ")

    val words = newText.split(" ", "\n").filter{it.isNotBlank()}

    val writer = file.bufferedWriter()

    val words1 = mutableListOf<String>()
    for (word in words)
    {
        words1.add(word.toString())
    }

    for (item in words1)
    {
        writer.write(item)
        writer.write(" ")
    }
    writer.close()
}