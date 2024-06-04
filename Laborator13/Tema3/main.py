lista = [1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8]
numereFiltrate = list(filter(lambda x: x >= 5, lista))
perechi = list(zip(numereFiltrate[::2], numereFiltrate[1::2]))
perechiInmultite = list(map(lambda x: x[0] * x[1], perechi))
print(sum(perechiInmultite))