import requests


class Response:
    def GET(self, URL):
        raise NotImplementedError("Interface!")


class RealResponse(Response):
    def __init__(self, URL, timestamp, response):
        self.URL = URL
        self.timestamp = timestamp
        self.response = response

    def get(self, URL):
        self.URL = URL
        self.response = requests.get(self.url).status_code
        self.timestamp = time()
