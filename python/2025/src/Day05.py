from Solution import Solution

part : int = 4

class TodaysSolution(Solution):
    def part_1(self, data: list[str]) -> None:
        ranges: List[Tuple[int, int]] = [ x.split('-') for x in data if '-' in x ]
        values: List[int] = [ int(x) for x in data if x.isdigit() ]

        count: int = 0
        for value in values:
            for left, right in ranges:
                if int(left) <= value <= int(right):
                    count += 1
                    break
        print(f"Count: {count}")

    def part_2(self, data: list[str]) -> None:
        ranges: List[Tuple[int, int]] = [ x.split('-') for x in data if '-' in x ]
        sorted_ranges: List[Tuple[int, int]] = sorted( ( (int(left), int(right)) for left, right in ranges ), key=lambda x: x[0] )

        count = 0
        max_right = 0
        for range in sorted_ranges:
            left = max(range[0], max_right + 1)
            max_right = max(max_right, range[1])
            count += max(0, max_right - left + 1)

        print(f"Count: {count}")
    
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    main(__file__)