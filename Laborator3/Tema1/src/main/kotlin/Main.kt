import org.jsoup.Jsoup


class RSSItem(val title: String, val link: String, val description: String, val pubDate: String)


fun main()
{
    val rssFeedUrl = "http://rss.cnn.com/rss/edition.rss"
    val rssItems = mutableListOf<RSSItem>()


    try
    {
        val doc = Jsoup.connect(rssFeedUrl).get()
        val items = doc.select("item")
        for (item in items)
        {
            val title = item.selectFirst("title")?.text() ?: ""
            val link = item.selectFirst("link")?.text() ?: ""
            val description = item.selectFirst("description")?.text() ?: ""
            val pubDate = item.selectFirst("pubDate")?.text() ?: ""

            rssItems.add(RSSItem(title, link, description, pubDate))
        }


        rssItems.forEach { item ->
            println("Titlu: ${item.title}")
            println("Link: ${item.link}")
            println("Descriere: ${item.description}")
            println("Data: ${item.pubDate}")
            println()
        }
    }
    catch (e: Exception)
    {
        println("A intervenit o eroare: ${e.message}")
    }
}





