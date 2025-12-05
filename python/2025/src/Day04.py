from Solution import Solution

part : int = 4

class TodaysSolution(Solution):
    def get_neighbors_values(grid: dict[int, dict[int, int]], row: int, col: int) -> list[int]:
        neighbor_values: list[int] = []
        for dr in range(-1, 2):
            for dc in range(-1, 2):
                if dr == 0 and dc == 0:
                    continue
                neighbor_row = row + dr
                neighbor_col = col + dc
                if neighbor_row in grid and neighbor_col in grid[neighbor_row]:
                    neighbor_values.append(grid[neighbor_row][neighbor_col])
        return neighbor_values

    def part_1(self, data: list[str]) -> None:
        grid: dict[int, dict[int, int]] = {}

        for row in range (len(data)):
            grid[row] = {}
            for col in range(len(data[row])):
                if data[row][col] == "@":
                    grid[row][col] = 1
        
        count: int = 0
        for row in range(len(data)):
            for col in range(len(data[row])):
                if row in grid and col in grid[row]:
                    if len(TodaysSolution.get_neighbors_values(grid, row, col)) < 4:
                        count += 1
        
        print(f"Count: {count}")

    def part_2(self, data: list[str]) -> None:
        grid: dict[int, dict[int, int]] = {}

        for row in range (len(data)):
            grid[row] = {}
            for col in range(len(data[row])):
                if data[row][col] == "@":
                    grid[row][col] = 1
        
        count: int = 0
        
        is_any_change: bool = True
        while is_any_change:
            is_any_change = False
            for row in range(len(data)):
                for col in range(len(data[row])):
                    if row in grid and col in grid[row]:
                        if len(TodaysSolution.get_neighbors_values(grid, row, col)) < 4:
                            count += 1
                            grid[row].pop(col)
                            is_any_change = True
                            
        print(f"Count: {count}")
    
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    main(__file__)