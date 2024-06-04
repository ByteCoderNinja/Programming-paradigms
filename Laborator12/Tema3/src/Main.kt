import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

fun main()
{
    val n : Int = readlnOrNull()?.toIntOrNull() ?: return
    val x = IntArray(n)
    val y = IntArray(n)
    for (i in 0..< n)
    {
        x[i] = readlnOrNull()?.toIntOrNull() ?: 0
        y[i] = readlnOrNull()?.toIntOrNull() ?: 0
    }
    for (i in 0..< n)
    {
        if (i < n - 1 && i > 0)
        {
            if (x[i] != x[i + 1] && y[i] != y[i + 1])
            {
                var aux : Int = x[i]
                x[i] = x[i - 1]
                x[i - 1] = aux
                aux = y[i]
                y[i] = y[i - 1]
                y[i - 1] = aux
            }
        }
    }
    val coord = x.zip(y)
    var p : Double = 0.0
    for (i in 0..< n)
    {
        val xValue1 = coord[i].first.toDouble()
        val yValue1 = coord[i].second.toDouble()
        var xValue2 = 0.0
        var yValue2 = 0.0
        if (i < n - 1)
        {
            xValue2 = coord[i + 1].first.toDouble()
            yValue2 = coord[i + 1].second.toDouble()
        }
        else
        {
            xValue2 = coord[0].first.toDouble()
            yValue2 = coord[0].second.toDouble()
        }
        p += sqrt((abs(xValue1 - xValue2).pow(2.0) ?: 0.0) + (abs(yValue1 - yValue2).pow(2.0) ?: 0.0))
    }
    println(p)
}