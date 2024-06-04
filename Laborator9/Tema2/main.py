class State:
    pass


class DisplayObserver:
    def update(self, money: int):
        pass


class SelectProduct(State):
    state_machine : 'SelectProductSTM'
    price : float

    def choose(self):
        choice = input("Select a product. CocaCola - 0, Pepsi - 1, Sprite - 2\n")
        if choice in ['0', '1', '2']:
            return choice
        else:
            print("Bad Choice...")



class Observable:
    observers = []

    def attach(self, observer):
        self.observers.append(observer)

    def detach(self, observer):
        self.observers.remove(observer)

    def notifyAll(self):
        for observer in self.observers:
            print(observer)


class SelectProductSTM(Observable):
    select_product_state : SelectProduct
    coca_cola_state : 'CocaCola'
    pepsi_state : 'Pepsi'
    sprite_state : 'Sprite'
    current_state : 'State'

    def __init__(self, select_product_state, coca_cola_state, pepsi_state, sprite_state, current_state):
        self.select_product_state = select_product_state
        self.coca_cola_state = coca_cola_state
        self.pepsi_state = pepsi_state
        self.sprite_state = sprite_state
        self.current_state = current_state

    def choose_another_product(self):
        self.select_product_state.choose()


class CocaCola(State):
    state_machine : SelectProductSTM
    price : float

    def __init__(self, price):
        self.price = price

class Pepsi(State):
    state_machine : SelectProductSTM
    price : float

    def __init__(self, price):
        self.price = price

class Sprite(State):
    state_machine : SelectProductSTM
    price : float

    def __init__(self, price):
        self.price = price

class WaitingForClient(State):
    state_machine: 'TakeMoneySTM'

    def client_arrived(self):
        pass

    def __init__(self, state_machine):
        self.state_machine = state_machine


class InsertMoney(State):
    state_machine: 'TakeMoneySTM'

    def insert_10bani(self):
        pass
    def insert_50bani(self):
        pass
    def insert_1leu(self):
        pass
    def insert_5lei(self):
        pass
    def insert_10lei(self):
        pass

    def __init__(self, state_machine):
        self.state_machine = state_machine


class TakeMoneySTM(Observable):
    wait_state: WaitingForClient
    insert_money_state: InsertMoney
    current_state: State
    money: int

    def __init__(self, wait_state, insert_money_state, current_state, money):
        self.wait_state = wait_state
        self.insert_money_state = insert_money_state
        self.current_state = current_state
        self.money = money


class ChoiceObserver:
    def update(self, select_product_stm):
        select_product_stm.detach()


class VendingMachineSTM:
    take_money_stm: TakeMoneySTM
    select_product_stm: SelectProductSTM
    choice_observer: ChoiceObserver
    display_observer: DisplayObserver

    def __init__(self, take_money_stm, select_product_stm):
        self.take_money_stm = take_money_stm
        self.select_product_stm = select_product_stm


def main():
    select_product = SelectProduct()
    cola = CocaCola(5.1)
    pepsi = Pepsi(4.5)
    sprite = Sprite(4.7)
    select_product_state = SelectProductSTM(select_product, cola, pepsi, sprite, State())

    cola2 = CocaCola(5.15)
    select_product_state.attach(cola2)

    cola.state_machine = select_product_state
    sprite.state_machine = select_product_state
    pepsi.state_machine = select_product_state
    cola2.state_machine = select_product_state

    price_list = []
    price_list.append(cola.price)
    price_list.append(pepsi.price)
    price_list.append(sprite.price)
    price_list.append(cola2.price)


    option = select_product.choose()
    insert_money = InsertMoney(select_product_state)
    waiting_for_client = WaitingForClient(select_product_state)

    take_money = TakeMoneySTM(waiting_for_client, insert_money,State(), sum(price_list))
    vending_machine = VendingMachineSTM(take_money, select_product_state)

if __name__ == "__main__":
    main()
