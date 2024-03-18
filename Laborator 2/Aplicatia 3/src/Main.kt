import com.sun.jdi.IntegerType
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.Value

//clasa principala - aplicatie JAVA
internal object Polyglot
{
    private fun generateNumbers(): List<Int>
    {
        val polyglot = Context.newBuilder().allowAllAccess(true).build()
        val result : MutableList<Int> = mutableListOf()
        for (i in 0 until 20)
        {
            result.add( polyglot.eval("python", "import random; random.randint(1,20)").asInt())
        }
        polyglot.close()

        return result
    }

    private fun afisareLista(result: List<Int>)
    {
        val polyglot = Context.newBuilder().allowAllAccess(true).build()
        polyglot.eval("js", " console.log(' $result ');")
        polyglot.close()
    }

    private fun prelucrareLista(result: List<Int>): Double {
        val polyglot = Context.newBuilder().allowAllAccess(true).build()
        val v: String = result.joinToString(separator = ",")
        val a : String = """v <- c($v);
v <- v[order(v)];
v <- v[ceiling(length(v) * 0.2):(floor(length(v) * 0.8))];
mean(v)
             """.trimMargin()
        val result1 : Value = polyglot.eval("R", a)
        val x : Double = result1.asDouble()
        polyglot.close()
        return x
    }

    //Tema 2
    private fun plot(fileName: String, path: String, line_color: String, value_color : String)
    {
        val polyglot = Context.newBuilder().allowAllAccess(true).build()
        val X : String = "$"
        polyglot.eval("R",
            """
        dataset <- read.csv("/home/student/Facultate/An2/PP/pp-1207a-homeworks-TimofteConstantinAC/Laborator 2/Aplicatia 3/src/dataset.txt")
        print(dataset)
        model <- lm(y ~ x, data = dataset)
        file <- "test"
        location <- "."
        library(lattice)
        png(file.path(location, paste0(file, ".png")))  # Salvarea în format PNG
        xyplot(dataset${X}x ~ dataset${X}y, main="Regresie liniară", xlab="X", ylab="Y",
       panel = function(x, y, ...) {
         panel.xyplot(x, y, col="$value_color")
         panel.abline(model, col="$line_color")
       })
        dev.off()

        system(paste("open", file.path(location, paste0(file, ".png"))))
        """
        )
        polyglot.close()
    }

    //Tema 3
    private fun monede()
    {
        val n1 : Int
        val n2 : Int
        val polyglot = Context.newBuilder().allowAllAccess(true).build()
        n1 = polyglot.eval("python", "int(input('Numarul de aruncari: '))").asInt()
        n2 = polyglot.eval("python",
            """
y = int(input("Da un numar cuprins intre 1 si numarul de aruncari: "))
while (y < 1 or y > $n1):
    y = int(input("Numarul nu e bun. Reintrodu: "))
y
                """).asInt()
        polyglot.eval("R",
            """
               numar_aruncari <- $n1
               probabilitate_pajura <- 0.5
               x <- $n2
               probabilitate_x_ori_pajura <- pbinom(x, numar_aruncari, probabilitate_pajura)
               print(paste("Probabilitatea de a obtine de cel mult", x, "ori pajura din", numar_aruncari, "aruncari este:", probabilitate_x_ori_pajura))
            """)
        polyglot.close()
    }

    @JvmStatic
    fun main(args: Array<String>)
    {
        val x : List<Int> = generateNumbers()
        print("Dati numele fisierului: ")
        val nume_fisier = readLine()
        print("Dati culoarea liniei: ")
        val line_color = readLine()
        print("Dati culoarea valorilor: ")
        val value_color = readLine()
        var media : Double = 0.0
        afisareLista(x)
        media = prelucrareLista(x)
        monede()
        println("Media listei este $media")
        if (nume_fisier != null && line_color != null && value_color != null)
        {
                plot(nume_fisier, "/home/student/Facultate/An2/PP/pp-1207a-homeworks-TimofteConstantinAC/Laborator 2/Aplicatia 3/src/dataset.txt", line_color, value_color)
        }

    }
}