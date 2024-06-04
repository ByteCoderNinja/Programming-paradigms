import subprocess

class Handler:
    def __init__(self, next_handler=None):
        self.next_handler = next_handler

    def set_next(self, next_handler):
        self.next_handler = next_handler

    def handle(self, content):
        if self.next_handler:
            return self.next_handler.handle(content)
        else:
            raise Exception("Tipul de fisier nu a putut fi determinat.")


class Kotlin(Handler):
    def handle(self, content):
        items = {"when", "fun", "val", "var"}
        contents1 = content.split("\n")
        for i in contents1:
            contents = i.split(" ")
            for x in contents:
                if x in items:
                    return "Kotlin"
        return super().handle(content)


class Python(Handler):
    def handle(self, content):
        items = {"import", "def", "or", "and"}
        contents1 = content.split("\n")
        for i in contents1:
            contents = i.split(" ")
            for x in contents:
                if x in items:
                    return "Python"
        return super().handle(content)


class Bash(Handler):
    def handle(self, content):
        items = {"case", "coproc", "do", "done", "elif", "else", "esac", "fi", "for", "function"}
        contents1 = content.split("\n")
        for i in contents1:
            contents = i.split(" ")
            for x in contents:
                if x in items:
                    return "Bash"
        return super().handle(content)


class Java(Handler):
    def handle(self, content):
        items = {"System", "System.out.println", "public", "final", "package"}
        contents1 = content.split("\n")
        for i in contents1:
            contents = i.split(" ")
            for x in contents:
                if x in items:
                    return "Java"
        return super().handle(content)


def create_file(content, filename, extension):
    with open(filename + extension, "w") as file:
        file.write(content)


class CommandExecutor:
    def execute(self, language, filename):
        with open(filename, 'r') as file:
            content = file.read()
        if language == "Python":
            create_file(content, filename, ".py")
            command = ["python", filename + ".py"]
        elif language == "Bash":
            command = ["bash", filename]
        elif language == "Kotlin":
            command = ["kotlinc", filename]
        elif language == "Java":
            command = ["javac", filename]
        else:
            raise Exception("Limbaj necunoscut.")

        result = subprocess.run(command, capture_output=True, text=True, shell=True).stdout
        return result


if __name__ == "__main__":
    filename = input("Introduceți numele fisierului: ")

    with open(filename, 'r') as file:
        content = file.read()

    print("Continutul fisierului:")
    print(content)

    java = Java()
    kotlin = Kotlin(java)
    bash = Bash(kotlin)
    python = Python(bash)

    language = python.handle(content)

    print(f"Limbajul determinat: {language}")

    executor = CommandExecutor()
    result = executor.execute(language, filename)

    print("Rezultatul executiei:")
    print(result)
