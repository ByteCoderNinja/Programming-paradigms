import os.path


class Celula:
   def __init__(self, nume):
       self.nume = nume


   def get_nume(self):
       return self.nume


class FibraMusculara(Celula):
    def __init__(self, masa_musculara, nume):
        self.masa_musculara = masa_musculara
        super().__init__(nume)
        print(f"Masa musculara a muschilor [{nume}] = {masa_musculara}")

    def get_masa_musculara(self):
        return self.masa_musculara


class MuschiGeneric(FibraMusculara):
    def __init__(self, scop, masa_musculara, nume):
        self.scop = scop
        super().__init__(masa_musculara, nume)


    def get_nume(self):
        return self.nume


    def get_masa_musculara(self):
        return self.masa_musculara


    def get_scop(self):
        return self.scop


class FibraNervoasa(Celula):
   def __init__(self, lungime, nume):
       self.lungime = lungime
       super().__init__(nume)
       print(f"Lungimea sistemului nervos [{nume}] = {lungime}")


   def get_lungime(self):
       return self.lungime


class TrunchiNervos(FibraNervoasa):
   lista = []


   def __init__(self, lungime, nume, specializare):
       super().__init__(lungime, nume)
       self.specializare = specializare


   def get_nume(self):
       return self.nume


   def get_lungime(self):
       return self.lungime


   def get_specializare(self):
       return self.specializare


if __name__ == "__main__":
    lista = []
    muschi1 = MuschiGeneric("locomotor", 0.49429999999999996, "Biceps Stang")
    lista.append(muschi1)
    muschi2 = MuschiGeneric("locomotor", 0.3666, "Biceps Drept")
    lista.append(muschi2)
    muschi3 = MuschiGeneric("locomotor", 0.4447, "Triceps Stang")
    lista.append(muschi3)
    muschi4 = MuschiGeneric("locomotor", 0.4915999999999999, "[Triceps Drept")
    lista.append(muschi4)
    muschi5 = MuschiGeneric("locomotor", 0.43329999999999996, "Gamba Stanga")
    lista.append(muschi5)
    muschi6 = MuschiGeneric("locomotor", 0.4665, "Gamba Dreapta")
    lista.append(muschi6)
    muschi7 = MuschiGeneric("locomotor", 1.2517999999999996, "Stomac")
    lista.append(muschi7)
    masa_totala = 0.0
    for i in lista:
        masa_totala += i.get_masa_musculara()
    print(f"Masa totala a muschilor = {masa_totala}\n")

    lista1 = []
    trunchi1 = TrunchiNervos(1439.1999999999998, "Emisfera Stanga", "Judecarea pozitiei in spatiu a obiectelor")
    lista1.append(trunchi1)
    trunchi2 = TrunchiNervos(1672.8, "Emisfera Dreapta", "Judecarea pozitiei in spatiu a obiectelor")
    lista1.append(trunchi2)
    trunchi3 = TrunchiNervos(1088.5, "Cerebel", "Miscare")
    lista1.append(trunchi3)
    trunchi4 = TrunchiNervos(1210.9, "Maduva", "Transportarea impulsurilor")
    lista1.append(trunchi4)
    lungimea_totala = 0.0
    for i in lista1:
        lungimea_totala += i.get_lungime()
    print(f"Lungimea axonilor din sistemul nervos = {lungimea_totala}\n")

    print("Urmatorii muschi au functie locomotorie:")
    for i in lista:
        if i.get_scop() == "locomotor":
            print(i.get_nume())



