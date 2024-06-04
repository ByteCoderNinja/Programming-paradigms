import more_itertools
import datetime
from pprint import pprint
from decimal import Decimal


''' more_itertools.make_decorator() si more_itertools.map_except()'''
mapper_except = more_itertools.make_decorator(more_itertools.map_except,
                                              result_index=1)


@mapper_except(float, ValueError, TypeError)
def read_file(f):
    # Citeste text care contine si numere
    # ...
    return ['1.5', '6', 'not-important', '11', '1.23E-7', 'remove-me', '25', 'trash']
# list(read_file("file.txt")) -> [1.5, 6.0, 11.0, 1.23e-07, 25.0]


if __name__ == '__main__':
    ''' more_itertools.divide(n, iterable) -> imparte un iterator in n
    sub-iteratori'''
    [list(iterator) for iterator in more_itertools.divide(3, list(range(8)))]
    # [[0, 1, 2], [3, 4, 5], [6, 7]]

    ''' more_itertools.partition(func, iterable) ->'''
    files = ["file0.jpg", "file1.exe", "file2.gif", "file3.txt", "file4.bin"]
    ALLOWED_EXTENSIONS = ('jpg', 'jpeg', 'gif', 'bmp', 'png')
    is_allowed = lambda file: file.split('.')[1] in ALLOWED_EXTENSIONS
    forbidden, allowed = more_itertools.partition(is_allowed, files)
    print(list(allowed), list(forbidden))

    ''' more_itertools.flatten(list_of_lists) -> despacheteaza listele intr-una singura'''
    print(list(more_itertools.flatten([[1, 2], [3, 4], [5, 6]])))  # [1, 2, 3, 4, 5, 6]

    ''' more_itertools.collapse(iterable) -> similar cu flatten, dar permite
    despachetarea mai multor niveluri de imbricari'''
    tree = [40, [25, [10, 3, 17], [32, 30, 38]], [78, 50, 93]]
    print(list(more_itertools.collapse(tree)))

    ''' more_itertools.split_at(iterable, predicate) -> similar cu split,
    dar pentru colectii'''
    lines = ['abc', 'def', None, 'ghi', None, 'jkl', 'mno']
    print(list(more_itertools.split_at(lines, lambda item: item is None)))

    ''' more_itertools.map_reduce(iterable, key_func, value_func, reduce_func)'''
    data = 'This sentence has words of various lengths in it, both short ones and long ones'.split()
    key_func = lambda word: len(word)
    pprint(more_itertools.map_reduce(data, key_func))
    '''defaultdict(None,
            {2: ['of', 'in'],
             3: ['has', 'it,', 'and'],
             4: ['This', 'both', 'ones', 'long', 'ones'],
             5: ['words', 'short'],
             7: ['various', 'lengths'],
             8: ['sentence']})'''
    value_func = lambda word: 1
    pprint(more_itertools.map_reduce(data, key_func, value_func))
    '''defaultdict(None,
            {2: [1, 1],
             3: [1, 1, 1],
             4: [1, 1, 1, 1, 1],
             5: [1, 1],
             7: [1, 1],
             8: [1]})'''
    reduce_func = sum
    pprint(more_itertools.map_reduce(data, key_func, value_func, reduce_func))
    # defaultdict(None, {4: 5, 8: 1, 3: 3, 5: 2, 2: 2, 7: 2})

    ''' more_itertools.sort_together() -> sortare dupa coloane'''
    cols = [
        ("John", "Ben", "Andy", "Mary"),  # Name
        ("1994-02-06", "1985-04-01", "2000-06-25", "1998-03-14"),  # Date of birth
        ("2020-05-14", "2019-05-15", "2020-05-16", "2018-08-17")  # updated at
    ]
    pprint(more_itertools.sort_together(cols, key_list=(1, 2)))

    ''' more_itertools.filter_except()'''
    data = ['1.5', '6', 'not-important', '11', '1.23E-7', 'remove-me', '25', 'trash']
    print(list(map(float, more_itertools.filter_except(float, data, TypeError, ValueError))))
    '''se filtreaza elementele din iterator, incercand convertirea acestora la float
    prin functia float(item), eliminand elementele pentru care se genereaza exceptie'''
    #  [1.5, 6.0, 11.0, 1.23e-07, 25.0]

    ''' more_itertools.numeric_range(start, stop, step) -> similar cu range, dar
    functioneaza si cu alte tipuri de date (nu doar int)'''
    print(list(more_itertools.numeric_range(Decimal(1.5), Decimal(3.6), Decimal(0.5))))
    # [Decimal('1.5'), Decimal('2.0'), Decimal('2.5'), Decimal('3.0'), Decimal('3.5')]
    start = datetime.datetime(2020, 5, 15)
    stop = datetime.datetime(2020, 5, 23)
    step = datetime.timedelta(days=2)
    print(list(more_itertools.numeric_range(start, stop, step)))
    '''[datetime.datetime(2020, 5, 15, 0, 0),
    datetime.datetime(2020, 5, 17, 0, 0),
    datetime.datetime(2020, 5, 19, 0, 0),
    datetime.datetime(2020, 5, 21, 0, 0)]'''

    ''' more_itertools.roundrobin(*iterables)'''
    list(more_itertools.roundrobin('ABC', 'D', 'EF'))  # [A, D, E, B, F, C]
