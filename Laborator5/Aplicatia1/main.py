import os
import queue
import tkinter as tk
import pygubu
from multiprocessing import Process, Queue

class Parser:
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self, master):
        self.master = master
        # 1: Create a builder
        self.builder = builder = pygubu.Builder()

        # 2: Load an ui file
        ui_path = os.path.join(self.ROOT_DIR, 'parser0.ui')
        builder.add_from_file(ui_path)

        # 3: Create the widget using a master as parent
        self.parser = builder.get_object('Parser', master)

        self.white_box = self.builder.get_object('text2')
        self.add_list_btn = self.builder.get_object('add_list_btn')
        self.filter_odd_btn = self.builder.get_object('button2')
        self.filter_prime_btn = self.builder.get_object('button3')
        self.sum_btn = self.builder.get_object('button4')

        self.integer_list_text = self.builder.get_object('integer_list_text')

        self.add_list_btn['command'] = self.add_list
        self.filter_odd_btn['command'] = self.odd_process
        self.filter_prime_btn['command'] = self.prim_process
        self.sum_btn['command'] = self.sum_process

        builder.connect_callbacks(self)
        self.integer_list = None
        self.queue = Queue()
        self.queue_pop()

    def add_list(self):
        result = self.integer_list_text.get("1.0", tk.END)
        result = result.strip().replace(' ', '')
        result = [int(item) for item in result.split(',')]
        self.integer_list = result
        self.white_box.insert(tk.END, str(result) + "\n")

    @staticmethod
    def filter_odd(q: Queue, lista):
        lista2 = []
        for i in lista:
            if i % 2 != 0:
                lista2.append(i)
        q.put(lista2)

    def odd_process(self):
        process = Process(target = self.filter_odd, args = (self.queue, self.integer_list))
        process.start()
    @staticmethod
    def prim(num):
        flag = False
        if num == 1:
            return 0
        elif num > 1:
            # check for factors
            for i in range(2, num):
                if (num % i) == 0:
                    # if factor is found, set flag to True
                    flag = True
                    # break out of loop
                    break
            # check if flag is True
            if flag:
                return 0
            else:
                return 1

    def prim_process(self):
        process = Process(target = self.filter_prime, args=(self.queue, self.integer_list))
        process.start()
    @staticmethod
    def filter_prime(q : Queue, lista):
        lista2 = []
        for i in lista:
            if Parser.prim(i) == 1:
                lista2.append(i)
        q.put(lista2)

    @staticmethod
    def sum(q : Queue, lista):
        sum = 0
        for i in lista:
            sum += i
        q.put(str(sum))

    def sum_process(self):
        process = Process(target = self.sum, args = (self.queue, self.integer_list))
        process.start()

    def GUI(self, sir):
        self.white_box.insert(tk.END, sir + "\n")
    def queue_pop(self):
        while self.queue.empty() != True:
            self.GUI(str(self.queue.get()))
        self.master.after(100, self.queue_pop)

if __name__ == '__main__':
    root = tk.Tk()
    root.title('Exemplul 1 cu Tkinter si PyGubu')
    app = Parser(root)
    root.mainloop()