import java.sql.Timestamp
import java.io.File

class HistoryLogRecord(private val timestamp: Timestamp, private val command: String) : Comparable<Timestamp>, Source<HistoryLogRecord>
{
    override fun compareTo(other: Timestamp): Int
    {
        return if (other.time == timestamp.time)
            0
        else if (other.time > timestamp.time)
            -1
        else
            1
    }

    override fun getTimestamp() : Timestamp {return timestamp}
    fun equals(H: HistoryLogRecord) : Boolean {return (H.getTimestamp() == this.getTimestamp())}

}


fun <T:Comparable<T>> maxim(x : T, y : T) : T  = if ((x.compareTo(y)) <= 0) x else y


fun string_timestamp(text: String) : Timestamp
{
    //2024-04-01  13:54:17
    val text1 : List<String> = text.split("  ")
    val text2 : List<Int> = text1[0].split("-").map{it.toInt()}
    val text3 : List<Int> = text1[1].split(":").map{it.toInt()}
    val time = Timestamp(text2[0] - 1900, text2[1], text2[2], text3[0], text3[1], text3[2], 0)
    return time
}


interface Source<out T>
{
    fun getTimestamp() : Timestamp
}


fun <T> find_change(x: Source<T>, y: T, z: MutableMap<Timestamp, T>)
{
    for ((key,value) in z)
    {
        if (value == x)
        {
            z[key] = y
        }
    }
}


fun main()
{
    val file = File("/var/log/apt/history.log")
    val text = file.readText()
    val text_records : List<String> = text.split("\n\n")
    val x = if (text_records.size >= 50) 50 else text_records.size
    val lista = mutableListOf<HistoryLogRecord>()
    val text_records2 = mutableListOf<String>()
    text_records.forEach {
        it.split("\n").filter {it != ""}.subList(0, 2).forEach {
            text_records2.add(it.split(": ")[1])
        } }
    var j = 0
    var k = 0
    val h = mutableMapOf<Timestamp, HistoryLogRecord>()
    for (i in text_records.size - 1 downTo  text_records.size - x)
    {
        lista.add(HistoryLogRecord(string_timestamp(text_records2[j]), text_records2[j+1]))
        h.put(string_timestamp(text_records2[j]), lista[k])
        j += 2
        ++k
    }

    val compare1 : Timestamp = maxim<Timestamp>(lista.elementAt(0).getTimestamp(), lista.elementAt(1).getTimestamp())
    println(compare1)
    println(h)
    find_change<HistoryLogRecord>(lista.elementAt(0), lista.elementAt(1), h)
    println(h)
}
