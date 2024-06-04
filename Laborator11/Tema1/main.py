#Să se realizeze calculul simultan pentru (suma de la 0 la n)
# patru valori diferite ale lui n luate dintr-o coadă de către
# patru corutine diferite (se va utiliza modulul asyncio).

import asyncio

async def suma(n):
    return sum(range(n+1))

async def worker(queue):
    while not queue.empty():
        n = await queue.get()
        value = await suma(n)
        await asyncio.sleep(1)
        print(f"Sum of first {n} numbers is {value}")

async def main():
    queue = asyncio.Queue()

    values = [10, 15, 33, 42]
    for value in values:
        await queue.put(value)

    await worker(queue)

if __name__ == "__main__":
    asyncio.run(main())
