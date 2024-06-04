fun main() {
    val lista = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)
    print(lista.filter{ it >= 5 }.chunked(2){ if (it.size == 2) it[0] to it[1] else null}.filterNotNull().map{ it.first * it.second}.sum())
}