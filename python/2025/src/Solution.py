import os
import timeit
import Vars
from pathlib import Path
from typing import Callable

class Solution:
    def load_data(filename: str) -> list[str]:
        with open(filename,'r') as f:
            data: list[str] = f.read().splitlines()
        return data

    def format_seconds(seconds: float) -> str:
        if seconds < 1:
            return f"{seconds * 1000:.3f} ms"
        elif seconds < 60:
            return f"{seconds:.3f} s"
        else:
            minutes: int = int(seconds // 60)
            secs: float = seconds % 60
            return f"{minutes} m {secs:.3f} s"
        
    def part_1(self, data: list[str]) -> int:
        pass

    def part_2(self, data: list[str]) -> int:
        pass
    
    def run(self, part: int, solver: Callable, data: list[str]) -> None:
        print(f"\nPart {1 if part <  3 else 2}{"e" if part % 2 == 1 else ""}:")
        time: float = timeit.timeit(lambda: solver(data), number=1)
        print(Solution.format_seconds(time))
    
    def solve(self, filename: str, part: int = 4) -> None:
        print(f"{type(filename)}, {type(part)}")
        file_path: Path = Path(filename)
        bare_filename: str = file_path.stem
        example_data_file: str = os.path.join(Vars.AOC_ROOT_PATH, "Input", str(Vars.YEAR), f"{bare_filename}.e1.txt")
        data_file: str = os.path.join(Vars.AOC_ROOT_PATH, "Input", str(Vars.YEAR), f"{bare_filename}.txt")
        example_data_file_2: str = os.path.join(Vars.AOC_ROOT_PATH, "Input", str(Vars.YEAR), f"{bare_filename}.e2.txt")
        if not os.path.exists(example_data_file_2):
            example_data_file_2 = example_data_file
            
        example_data: list[str] = Solution.load_data(example_data_file)
        data: list[str] = Solution.load_data(data_file)
        
        example_data_2: list[str] = Solution.load_data(example_data_file_2)
        if len(example_data_2) == 0 or (len(example_data_2) == 1 and len(example_data_2[0].strip()) == 0):
            example_data_2 = Solution.load_data(example_data_file)
        
        all_data: list[list[str]] = [example_data, data, example_data_2, data]
        
        for i in range(1, part + 1):
            self.run(i, self.part_1 if i < 3 else self.part_2, all_data[i - 1])