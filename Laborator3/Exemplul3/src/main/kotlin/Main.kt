class Birth(val year: Int, val Month: Int, val Day: Int){
    override fun toString() : String{
        return "($Day.$Month.$year)"
    }
}
class Contact(val Name: String, var Phone: String, val BirthDate: Birth){
    fun Print() {
        println("Name: $Name, Mobile: $Phone, Date: $BirthDate")
    }
}

fun MutableList<Contact>.cautareNume(x : String) : Boolean
{
    for (i in this)
    {
        if (x == i.Name)
        {
            return true
        }
    }
    return false
}

fun Contact.actualizare_nr_telefon(x : String)
{
    if (x[0] == '0' && x[1] == '7' && x.length == 10)
    {
        this.Phone = x
    }
    else
    {
        println("Numarul de telefon nu este bun!")
    }
}

fun main(args : Array<String>){
    val agenda = mutableListOf<Contact>()
    agenda.add(Contact("Mihai", "0744321987", Birth(1900, 11, 25)))
    agenda += Contact("George", "0761332100", Birth(2002, 3, 14))
    agenda += Contact("Liviu" , "0231450211", Birth(1999, 7, 30))
    agenda += Contact("Popescu", "0211342787", Birth(1955, 5, 12))
    for (persoana in agenda){
        persoana.Print()
    }
    agenda.cautareNume("Mihai")
    agenda[2].actualizare_nr_telefon("0752818717")
    println("Agenda dupa eliminare contact [George]:")
    agenda.removeAt(1)
    for (persoana in agenda){
        persoana.Print()
    }
    agenda.remove(Contact("Liviu" , "0231450211", Birth(1999, 7, 30)))
    println("Agenda dupa eliminare contact [Liviu]:")
    agenda.removeAt(1)
    for (persoana in agenda){
        persoana.Print()
    }
}