import datetime
from functional import seq
class Person:
    def __init__(self, firstName, lastName, dateOfBirth : datetime, emailAddress):
        self.firstName = firstName
        self.lastName = lastName
        self.dateOfBirth = dateOfBirth
        self.emailAddress = emailAddress

    def toString(self):
        return self.firstName + " " + self.lastName + ", " + str(self.dateOfBirth)


    def age(self):
        today = datetime.date.today()
        return today.year - self.dateOfBirth.year


    def name(self):
        return self.firstName + " " + self.lastName

def main():
    people = []
    people.append(Person("John", "Doe", datetime.date(1960, 11, 3), "jdoe@example.com"))
    people.append(Person("Ellen", "Smith", datetime.date(1992, 5, 13), "ellensmith@example.com"))
    people.append(Person("Jane", "White", datetime.date(1986, 2, 1), "janewhite@example.com"))
    people.append(Person("Bill", "Jackson", datetime.date(1999, 11, 6), "bjackson@example.com"))
    people.append(Person("John", "Smith", datetime.date(1975, 7, 14), "johnsmith@example.com"))
    people.append(Person("Jack", "Williams", datetime.date(2015, 5, 28), ""))

    functional = seq(people)

    youngest = functional.min_by(lambda p: p.dateOfBirth)
    print(f"Cea mai tanara persoana: {youngest.toString()}\n")

    oldest = functional.max_by(lambda p: p.dateOfBirth)
    print(f"Cea mai batrana persoana: {oldest.toString()}\n")

    underage = functional.filter(lambda p: p.age() < 18).map(lambda p: p.name()).to_list()
    print(f"Persoanele sub 18 ani: {underage}")
    print()

    emails = functional.map(lambda p : p.emailAddress).to_list()
    print(f"Email-urile: {emails}")
    print()

    emailsMap = functional.map(lambda p : (p.name(), p.emailAddress)).to_dict()
    print(f"Numele + email-ul:\n {emailsMap}")
    print()

    emailPersonMap = functional.map(lambda p : (p.emailAddress, p.name())).to_dict()
    print("Mapa de persoane:")
    for k, v in emailPersonMap.items():
        print(f"{k}: {v}")
    print()

    peopleToCelebrateEachMonth = functional.map(lambda p : p.dateOfBirth.month).distinct().sorted().to_list()
    for i in peopleToCelebrateEachMonth:
        print(f"Persoanele nascute in luna {i}: ", end="")
        for j in people:
            if j.dateOfBirth.month == i:
                print(f"{j.name()}, ", end="")
        print("\b\b")
    print()

    mapByBirthYear = functional.filter(lambda p : p.dateOfBirth.year <= 1980).map(lambda p: p.name()).to_list()
    print(f"Persoanele nascute inainte de 1980 inclusiv sunt:\n {mapByBirthYear}\n")

    names = functional.map(lambda p: p.firstName).to_list()
    distinct_names = list(filter(lambda p: names.count(p) == 1, names))
    print(f"Numele distincte sunt: {distinct_names}\n")

    averageAge = functional.map(lambda p : p.age()).average()
    print(f"Varsta medie este {averageAge}\n")

    smiths = functional.filter(lambda p : p.lastName == "Smith").to_list()
    print(f"Sunt {len(smiths)} persoane cu numele Smith\n")

    john = functional.filter(lambda p : p.firstName == "John").to_list()
    if not john:
        print("Nu este nimeni cu numele John\n")
    else:
        print("Sunt persoane cu numele John\n")

    thomas = functional.filter(lambda p : p.firstName == "Thomas").to_list()
    if not thomas:
        print("Nu este nimeni cu numele Thomas\n")
    else:
        print("Sunt persoane cu numele Thomas\n")

    noEmail = functional.filter(lambda p : p.emailAddress == "").to_list()
    if noEmail:
        print("Exista persoane fara email")
    else:
        print("Nu exista persoane fara email")

if __name__ == '__main__':
    main()