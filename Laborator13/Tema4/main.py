class StateMachine():
    def __init__(self, lista):
        self.stare = 0
        self.lista = lista
        self.procese = [
            lambda num: list(filter(lambda x: self.prim(x), num)),  # Starea 0
            lambda num: list(filter(lambda x: x % 2 == 1, num)),  # Starea 1
            lambda num: list(filter(lambda x: x < 50, num)),  # Starea 2
            lambda num: num # Starea STOP
        ]

    def prim(self, num):
        if num == 1:
            return False
        elif num > 1:
            for i in range(2, num):
                if (num % i) == 0:
                    return False
        return True

    def proces(self):
        while(self.stare != 3):
            self.lista = self.procese[self.stare](self.lista)
            print(self.lista)
            self.stare += 1

def main():
    lista = [12, 13, 321, 1, 51, 2, 14, 15, 51, 67]
    StateMachine(lista).proces()

if __name__ == '__main__':
    main()