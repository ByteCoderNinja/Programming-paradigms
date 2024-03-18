fun main()
{
    val account1 = BankAccount(3140.23, "4140 7321 2134 1239",
        Date(2026, 11, 23), 571, "Matache Alin")
    val card1 = CardPayment(account1)

    val open = Time(8, 0, 0)
    val close = Time(23, 30, 0)
    val Cinema_City = Cinema(Time(8,0,0), Time(23, 30,0))

    val movie1 = Movie("Scary Movie", Time(1,8,23), Time(17,23,13),
        5, 1, card1)
    movie1.buyTicket(Ticket(35.32, 5), 1)
    Cinema_City.addMovie(movie1, Time(17,23,13))

    val movie2 = Movie("Hangover", Time(2,32,12), Time(23,32,11),
        4,3, CashPayment(231.32))
    movie2.buyTicket(Ticket(35.12, 4), 10)
    Cinema_City.addMovie(movie2, Time(23,32,11))
}
