from Solution import Solution

part : int = 4

class TodaysSolution(Solution):
    def part_1(self, data: list[str]) -> None:
        dial: int = 50
        count: int = 0

        for line in data:
            direction: int = 1 if line[0] == 'R' else -1
            steps: int = int(line[1:]) * direction
            
            dial += steps
            dial = dial % 100
            if dial == 0:
                count += 1
        print(f'Dial hit 0 a total of {count} times.')

    def part_2(self, data: list[str]) -> None:
       dial: int = 50
       count: int = 0

       for line in data:
           direction: int = 1 if line[0] == 'R' else -1
           steps: int = int(line[1:]) * direction
           
           for _ in range(abs(steps)):
               dial += direction
               dial = dial % 100
               if dial == 0:
                   count += 1
            
       print(f'Dial hit 0 a total of {count} times.') 
       
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    main(__file__)