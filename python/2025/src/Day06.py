from Solution import Solution
import re
import math

part : int = 4

class TodaysSolution(Solution):
    def part_1(self, data: list[str]) -> None:
        s: int = 0

        stacks: list[list[int]] = []
        for row in data:
            vals: list[str] = [x for x in re.split(r' +', row.strip())]
            
            for i in range(len(vals)):
                if len(stacks) <= i:
                    stacks.append([])
                if vals[i] != '*' and vals[i] != '+':
                    stacks[i].append(int(vals[i]))
                else:
                    if vals[i] == '*':
                        val: int = math.prod(stacks[i])
                        print(val)
                        s += val
                    elif vals[i] == '+':
                        print(stacks[i])
                        val: int = sum(stacks[i])
                        print(val)
                        s += val
        print(f"Sum: {s}")
                

    def collapse_buffers(self, buffers: list[list[str]], op: str) -> int:
        vals: list[int] = []
        for buffer in buffers:
            if len(buffer) > 0:
                vals.append(int(''.join(buffer)))
        if op == "*":
            return math.prod(vals)
        return sum(vals)

    def part_2(self, data: list[str]) -> None:
        buffers: list[list[str]] = []

        s: int = 0
        problem_col = 0
        op: str = ' '
        for col in range(len(data[0])):
            all_blank: bool = True
            for row in range(len(data)):
                if len(buffers) <= col-problem_col:
                    buffers.append([])
                if data[row][col] != ' ':
                    all_blank = False
                    if data[row][col] == '*' or data[row][col] == '+':
                        op = data[row][col]
                    else:
                        buffers[col - problem_col].append(data[row][col])
            if all_blank:
                problem_col = col + 1
                s += self.collapse_buffers(buffers, op)
                buffers = []
        
        s += self.collapse_buffers(buffers, op)
        print(f"Sum: {s}")
                
            
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    print(__file__)
    main(__file__)