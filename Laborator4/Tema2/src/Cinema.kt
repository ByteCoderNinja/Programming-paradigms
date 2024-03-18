import java.util.Calendar

class Cinema
    (
         val OpeningTime: Time,
         val ClosingTime: Time
    )
    {
        private val MovieList: MutableList<Movie> = mutableListOf()
        fun addMovie(movie: Movie, time: Time)
        {
            if (time.Hour >= OpeningTime.Hour && time.Hour <= ClosingTime.Hour)
            {
                if (time.Hour == ClosingTime.Hour)
                {
                    if (time.Minutes <= ClosingTime.Minutes)
                    {
                        if (time.Minutes == ClosingTime.Minutes)
                        {
                            if (time.Seconds < ClosingTime.Seconds)
                            {
                                MovieList.add(movie)
                            }
                        }
                        else
                        {
                            MovieList.add(movie)
                        }
                    }
                }
                else
                {
                    MovieList.add(movie)
                }
            }
        }
    }

class Movie
    (
            val Title: String,
            val Duration: Time,
            val StartHour: Time,
            val Room: Int,
            var TicketCount: Int,
            val payment_method: PaymentMethod
    )
    {
            fun buyTicket(ticket: Ticket, numberOfTickets: Int): Ticket?
            {
                if (payment_method.pay(ticket.Price, TicketCount - numberOfTickets)
                    && (TicketCount - numberOfTickets) >= 0 && ticket.Room == Room)
                {
                    TicketCount = TicketCount - numberOfTickets
                    println("Enjoy the movie $Title")
                    return ticket
                }
                else
                {
                    if ((TicketCount - numberOfTickets) < 0)
                    {
                        println("There are no more tickets for $Title!")
                    }
                    return null
                }
            }
    }

class Ticket
    (
            val Price: Double,
            val Room: Int
    )