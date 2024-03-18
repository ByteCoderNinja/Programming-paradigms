package org.example
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

val rssFeedUrl = "https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898"


class Crawler(@get:JvmName("getAdapterContext") private var url: String)
{
    lateinit var parser: Parser
    fun getUrl(): String?
    {
        return url
    }

    fun setUrl(url1: String)
    {
        url = url1
    }

    fun getResource(): Document?
    {
        return Jsoup.connect(rssFeedUrl).get()
    }

    fun processContent(contentType: String)
    {
        val resource = getResource()
        if (resource != null)
        {
            val content = resource.text()
            when (contentType)
            {
                "json" -> parser = JsonParser()
                "xml" -> parser = XmlParser()
                "yaml" -> parser = YamlParser()
            }
            val parsedData = parser.parse(content)
            println("Parsed content:")
            parsedData.forEach { (key, value) ->
                println("$key: $value")
            }
        }
        else
        {
            println("Failed to retrieve resource")
        }
    }
}


fun main()
{
    val crawler = Crawler(rssFeedUrl)

    println("Procesare conținut ca JSON:")
    crawler.processContent("json")

    println("\nProcesare conținut ca XML:")
    crawler.processContent("xml")

    println("\nProcesare conținut ca YAML:")
    crawler.processContent("yaml")
}


