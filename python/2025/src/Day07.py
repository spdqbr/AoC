from Solution import Solution
from functools import cache

part : int = 4

class TodaysSolution(Solution):
    def part_1(self, data: list[str]) -> None:
        lasers: list[bool] = [ False for _ in range(len(data[0])) ]
        next_lasers: list[bool] = [ False for _ in range(len(data[0])) ]

        lasers[data[0].find('S')] = True
        
        split_count: int = 0
        for i in range(1, len(data)):
            for j in range(len(data[0])):
                if lasers[j]:
                    if data[i][j] == '^':
                        next_lasers[j-1] = True
                        next_lasers[j+1] = True
                        split_count += 1
                    else:
                        next_lasers[j] = True
            lasers, next_lasers = next_lasers, lasers
            next_lasers = [ False for _ in range(len(data[0])) ]
        
        print(f"Split count: {split_count}")
        
    
    data: list[str]
    @cache
    def memo_count(self, row: int, col: int) -> int:
        if row >= len(self.data):
            return 1
        count: int = 0
        if self.data[row][col] == '^':
            count += self.memo_count(row + 1, col - 1)
            count += self.memo_count(row + 1, col + 1)
        else:
            count += self.memo_count(row + 1, col)
        return count

    def part_2(self, data: list[str]) -> None:
        self.data = data
        self.memo_count.cache_clear()
        col: int = data[0].find('S')
        total_count: int = self.memo_count(1, col)
        print(f"Total ways to reach the bottom: {total_count}")
    
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    print(__file__)
    main(__file__)