import sys
import random
import os
from PyQt5.QtWidgets import QApplication, QMainWindow, QWidget, QVBoxLayout, QTextEdit, QPushButton, QLabel
from PyQt5.QtCore import QDateTime


class JurnalApp(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Aplicație Jurnal")
        self.resize(400, 300)


        self.central_widget = QWidget()
        self.setCentralWidget(self.central_widget)
        self.layout = QVBoxLayout()
        self.central_widget.setLayout(self.layout)


        self.citat_label = QLabel()
        self.layout.addWidget(self.citat_label)


        self.load_button = QPushButton("Load")
        self.load_button.clicked.connect(self.load_journal)
        self.layout.addWidget(self.load_button)


        self.save_button = QPushButton("Save")
        self.save_button.clicked.connect(self.save_journal)
        self.layout.addWidget(self.save_button)

        self.addText_button = QPushButton("Add Text")
        self.addText_button.clicked.connect(self.addText)
        self.layout.addWidget(self.addText_button)

        self.jurnal_edit = QTextEdit()
        self.layout.addWidget(self.jurnal_edit)

        self.citat_aleator = self.load_quote()

        self.last_saved_file = ""


    def load_quote(self):
        with open("citate.txt", "r") as file:
            citate = file.readlines()
            citat_aleator = random.choice(citate).strip()
            self.citat_label.setText(citat_aleator)
        return citat_aleator


    def load_journal(self):
        if self.last_saved_file:
            with open(self.last_saved_file, "r") as file:
                content = file.read()
                self.jurnal_edit.setPlainText(content)
        else:
            self.jurnal_edit.setPlainText(self.citat_aleator)

    def save_journal(self):
        file_name = self.get_file_name()
        if file_name:
            content = self.jurnal_edit.toPlainText()
            with open(file_name, "w") as file:
                file.write(content)
            self.last_saved_file = file_name

    def addText(self):
        with open("citate.txt", "a+") as file:
            file.seek(0)
            content = file.read()
            if len(content):
                file.write("\n")
            file.write(self.jurnal_edit.toPlainText())

    def get_file_name(self):
        timestamp = QDateTime.currentDateTime().toString("yyyy-MM-dd_hh-mm-ss")
        return f"{timestamp}.txt"


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = JurnalApp()
    window.show()
    sys.exit(app.exec_())