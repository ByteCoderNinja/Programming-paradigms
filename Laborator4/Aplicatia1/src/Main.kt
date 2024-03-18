class Content(private var author: String, private var text: String, private var name: String, private var publisher: String)
{
    public fun getAuthor(): String
    {
        return author
    }

    public fun setAuthor(au: String)
    {
        author = au
    }

    public fun getText(): String
    {
        return text
    }

    public fun setText(te: String)
    {
        text = te
    }

    public fun getName(): String
    {
        return name
    }

    public fun setName(na: String)
    {
        name = na
    }

    public fun getPublisher(): String
    {
        return publisher
    }

    public fun setPublisher(pub: String)
    {
        publisher = pub
    }
}


class Book(private var data: Content)
{
    public override fun toString(): String
    {
        val book : String = "author: ${data.getAuthor()}\nname: ${data.getName()}\n" +
                "publisher: ${data.getPublisher()}\ncontent: ${data.getText()}"
        return book
    }

    public fun getName(): String
    {
        return data.getName()
    }

    public fun getAuthor(): String
    {
        return data.getAuthor()
    }

    public fun getPublisher(): String
    {
        return data.getPublisher()
    }

    public fun getContent(): String
    {
        return data.getText()
    }

    public fun hasAuthor(au: String): Boolean
    {
        if (data.getAuthor() != au)
        {
            return false
        }
        return true
    }

    public fun hasTitle(na: String): Boolean
    {
        if (data.getName() != na)
        {
            return false
        }
        return true
    }

    public fun isPublisherBy(pub: String): Boolean
    {
        if (data.getPublisher() != pub)
        {
            return false
        }
        return true
    }
}


class Library(private var books: Set<Book>)
{
    fun getBooks(): Set<Book>
    {
        return books
    }

    fun addBook(b: Book)
    {
        books.plus(b)
    }

    fun findAllByAuthor(au: String): Set<Book>
    {
        val found: Set<Book> = HashSet<Book>()
        books.forEach { book ->
            if(book.getAuthor() == au)
            {
                found.plus(book)
            }
        }
        return found
    }

    fun findAllByName(na: String): Set<Book>
    {
        val found: Set<Book> = HashSet<Book>()
        books.forEach { book ->
            if (book.getName() == na)
            {
                found.plus(book)
            }
        }
        return found
    }

    fun findAllByPublisher(pub: String): Set<Book>
    {
        val found: Set<Book> = HashSet<Book>()
        books.forEach { book ->
            if (book.getPublisher() == pub)
            {
                found.plus(book)
            }
        }
        return found
    }
}


interface LibraryPrinter
{
    fun print(books : Set<Book>) {}
}

class BooksRawPrinter
{
    fun print(books : Set<Book>)
    {
        books.forEach { book ->
            println("BOOK ----------------\n${book.toString()}")
        }
    }
}


class HTMLPrinter {
    fun print(books: Set<Book>) {
        println(
            """
       <html>
           <head>
               <title>
                   Books
               </title>
           </head>
           <body>
               ${books.forEach { book -> "BOOK ----------------\n${book.toString()}"}}
           </body>
       </html>
       """
        )
    }
}

class JSONPrinter
{
    fun print(books : Set<Book>)
    {
        books.forEach { book ->
            println("name: ${book.getName()}")
            println("author: ${book.getAuthor()}")
            println("publisher: ${book.getPublisher()}")
            println("content: ${book.getContent()}")
            println()
        }
    }
}


fun main()
{

    val content1: Content = Content("Alex", "Iasi este un oras minunat", "Iasi description", "Iasi Writers")
    val content2: Content = Content("Sorin", "Cluj este un oras minunat", "Cluj description", "Cluj Writers")
    val book1: Book = Book(content1)
    val book2: Book = Book(content2)
    val books: MutableSet<Book> = mutableSetOf()
    books.add(book1)
    books.add(book2)
    var lib: Library = Library(books)
    val raw : BooksRawPrinter = BooksRawPrinter()
    raw.print(books)
    println()
    val HTML : HTMLPrinter = HTMLPrinter()
    HTML.print(books)
    println()
    val JSON = JSONPrinter()
    JSON.print(books)
    println()
}

