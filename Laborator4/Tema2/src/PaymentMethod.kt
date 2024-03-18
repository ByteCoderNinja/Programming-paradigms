interface PaymentMethod
{
    fun pay(fee: Double, tickets: Int): Boolean
}

class CashPayment(private var availableAmount: Double) : PaymentMethod
{
    override fun pay(fee: Double, tickets: Int): Boolean
    {
        if (availableAmount < fee || tickets < 0)
        {
            println("Not enough money or there are no more tickets!")
        }
        else
        {
            println("Transaction Complete")
        }
        return (availableAmount >= fee)
    }
}

class CardPayment(private var bankAccount: BankAccount) : PaymentMethod
{
    override fun pay(fee: Double, tickets: Int): Boolean
    {
        if (bankAccount.availableAmount < fee || tickets < 0)
        {
            println("Not enough money!")
        }
        else
        {
            println("Transaction Complete")
        }
        return (bankAccount.availableAmount >= fee)
    }
}

class BankAccount
    (
    var availableAmount: Double,
    val cardNumber: String,
    val expirationDate: Date,
    val cvvCode: Int,
    val userName: String
)
{
    fun updateAmount(value: Double): Boolean
    {
        availableAmount = value
        return (availableAmount >= 0)
    }
}

class Time
    (
    val Hour: Int,
    val Minutes: Int,
    val Seconds: Int
)

class Date
    (
    val Year: Int,
    val Month: Int,
    val Day: Int,
)