from Solution import Solution

part : int = 1

class TodaysSolution(Solution):
    def part_1(self, data: list[str]) -> None:
        pass

    def part_2(self, data: list[str]) -> None:
        pass
    
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    print(__file__)
    main(__file__)