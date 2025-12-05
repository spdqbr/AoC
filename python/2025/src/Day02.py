from Solution import Solution
import re

part : int = 4

class TodaysSolution(Solution):
    def part_1(self, data: list[str]) -> None:
        data = data[0].split(",")
        pattern: Pattern = re.compile(r'^(.+)\1$')
        sum: int = 0

        for datum in data:
            left, right = datum.split("-")
            for i in range(int(left), int(right)+1):
                if pattern.match(str(i)):
                    sum += i
        print(f"Sum: {sum}")

    def part_2(self, data: list[str]) -> None:
        data = data[0].split(",")
        pattern: Pattern = re.compile(r'^(.+)\1+$')
        sum: int = 0

        for datum in data:
            left, right = datum.split("-")
            for i in range(int(left), int(right)+1):
                if pattern.match(str(i)):
                    sum += i
        print(f"Sum: {sum}")
    
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    main(__file__)