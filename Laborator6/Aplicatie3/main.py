import os.path


class AudioFile:
    def __init__(self, filename):
        if not filename.endswith(self.ext):
            raise Exception("Format nesuportat")
        self.filename = filename


class MP3File(AudioFile):
    ext = "mp3"

    def play(self):
        print("Se canta {}".format(self.filename.split("/")[-1]))



class WavFile(AudioFile):
    ext = "wav"

    def play(self):
        print("Se canta {}".format(self.filename.split("/")[-1]))



class OggFile(AudioFile):
    ext = "ogg"

    def play(self):
        print("Se canta {}".format(self.filename.split("/")[-1]))


class FlacFile:
    def __init__(self, filename):
        if not filename.endswith(".flac"):
            raise Exception("Format necunoscut")
        self.filename = filename

    def play(self):
        print("Se canta {}".format(self.filename.split("/")[-1]))



class Reader:
    def __init__(self, path, lista):
        if os.path.exists(path):
            for i in lista:
                try:
                    i(path).play()
                except Exception as e:
                    pass


if __name__ == '__main__':
    filepath = ("/home/student/Facultate/An2/PP/pp-1207a-homeworks-TimofteConstantinAC/Laborator6/Aplicatie3/David "
                "Guetta & MORTEN - The Truth [Visualizer].mp3")
    lista = [MP3File, WavFile, OggFile, FlacFile]
    Reader(filepath, lista)
