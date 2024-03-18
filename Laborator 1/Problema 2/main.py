def read_file():
    with open('Text.txt', 'r') as file:
        return file.read()


def remove_punctuation(text):
    for char in text:
        if char in ".,!?;:":
            text = text.replace(char, "")
    return text


def convert_to_upper(text):
    return text.upper()


def letter_number_filter(text, n):
    words = text.split(" ")
    for c in words:
        if len(c) == n:
            text = text.replace(c, "")
    return text


def main():
    text = read_file()
    text1 = remove_punctuation(text)
    text2 = convert_to_upper(text)
    text3 = letter_number_filter(text, 5)
    print(text, "\n")
    print(text1, "\n")
    print(text2, "\n")
    print(text3)

if __name__ == '__main__':
    main()