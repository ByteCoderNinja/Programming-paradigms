class ContactList(list):
    def search(self, name):
        matching_contacts = []
        for contact in self:
            if name in contact.name:
                matching_contacts.append(contact)
        return matching_contacts


class Contact:
    def __init__(self, name, email, phoneNumber):  # constructor
        self.name = name
        self.email = email
        self.phoneNumber = phoneNumber

    # echivalentul supraincarcarii operator<< din c++
    def __repr__(self):
        return "Contact({}, {}, {})".format(self.name, self.email, self.phoneNumber)


class Friend(Contact):
    def __init__(self, name, email, phone):
        Contact.__init__(name, email)
        self.phone = phone


class Agenda:
    all_contacts = ContactList()

    def add_contact(self, contact):
        self.all_contacts.append(contact)

    def print_agenda(self):
        for contact in self.all_contacts:
            print(contact)


# acest bloc se executa doar cand se apeleaza acest script
# se recomanda folosirea acestui bloc pentru a nu se executa la import
if __name__ == '__main__':
    agenda = Agenda()
    agenda.add_contact(Contact('Ion Popescu', 'ion_popescu@gmail.com', '0745818717'))
    agenda.add_contact(Contact('Alin Ionescu', 'alin_ionescu@gmail.com', '0721321415'))
    agenda.add_contact(Contact('Alex Constantinescu', 'alex_constantinescu@gmail.com', '0702491124'))
    agenda.print_agenda()
