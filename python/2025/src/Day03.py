from Solution import Solution

part : int = 4

def getMax(s: str, minIndex: int, batteries: int) -> int:
    if batteries == 0:
        return 0
    
    index_of_next_biggest: int = -1
    for i in range(9,0,-1):
        if index_of_next_biggest > -1:
            break
        for index in range(minIndex, len(s)-batteries+1):
            if int(s[index]) == i:
                index_of_next_biggest = index
                break
    
    val: int = int(s[index_of_next_biggest]) * (10 ** (batteries - 1))
    return val + getMax(s, index_of_next_biggest + 1, batteries - 1)

class TodaysSolution(Solution):
    def part_1(self, data: list[str]) -> None:
        batteries: int = 2
        total: int = 0
        for line in data:
            result: int = getMax(line, 0, batteries)
            total += result
        print(f"Part 1: {total}")

    def part_2(self, data: list[str]) -> None:
        batteries: int = 12
        total: int = 0
        for line in data:
            result: int = getMax(line, 0, batteries)
            total += result
        print(f"Part 1: {total}")
        
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    main(__file__)
