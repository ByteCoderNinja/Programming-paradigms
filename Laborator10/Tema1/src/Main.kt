import java.util.logging.Handler

interface Handler
{
    public fun handleRequest(messageToBeProcessed : String) {}
}

class CEOHandler : Handler()
{
    public val next1 : Handler? = null
    public val next2 : Handler? = null

    fun handleRequest(messageToBeProcessed: String)
    {
         
    }
}

abstract class AbstractFactory
{
    fun getHandler(handler : String) : Handler
    {
    }
}