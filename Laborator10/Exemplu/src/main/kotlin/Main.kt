package com.pp.laborator
import kotlinx.coroutines.*
import kotlin.system.*

suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit)
{
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        val jobs = List(n)
        {
            launch { repeat(k) { action() } }
        }
        jobs.forEach { it.join() }
    }
    println("S-au efectuat ${n * k} operatii in $time ms")
}

val mtContext = newFixedThreadPoolContext(2, "mtPool")
var counter : Int = 0
fun main() = runBlocking<Unit> {
    var List = mutableListOf(Int)
    CoroutineScope(mtContext).massiveRun {
        counter++
    }
    println("Numarator = $counter")
}