from Solution import Solution
import math

part : int = 4

class TodaysSolution(Solution):
    def distance(self, a: tuple[int, int, int], b: tuple[int, int, int]) -> float:
        return ((a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2 + (a[2] - b[2]) ** 2) ** 0.5

    def part_1(self, data: list[str]) -> None:
        grid: list[tuple[int, int, int]] = []
        for line in data:
            grid.append(tuple(map(int, line.split(","))))
        
        distances: list[tuple[float, tuple, tuple]] = []
        for i in range(len(grid)):
            for j in range(i + 1, len(grid)):
                dist: float = self.distance(grid[i], grid[j])
                distances.append((dist, grid[i], grid[j]))
        
        distances.sort()
        
        max_connections = 10
        if len(data) > 50:
            max_connections = 1000
            
        circuits: list[set[tuple[int, int, int]]] = [ {x} for x in grid ]
        
        for i in range(max_connections):
            connection_a = distances[i][1]
            connection_b = distances[i][2]
            circuit_a = None
            circuit_b = None

            for circuit in circuits:
                if connection_a in circuit:
                    circuit_a = circuit
                if connection_b in circuit:
                    circuit_b = circuit
                if circuit_a is not None and circuit_b is not None:
                    break
                
            if circuit_a != circuit_b:
                circuit_a |= circuit_b
                circuit_b.clear()
                circuits.remove(circuit_b)
                
        lengths = [ len(circuit) for circuit in circuits ]
        lengths.sort(reverse=True)
        print(math.prod(lengths[:3]))

    def part_2(self, data: list[str]) -> None:
        grid: list[tuple[int, int, int]] = []
        for line in data:
            grid.append(tuple(map(int, line.split(","))))
        
        distances: list[tuple[float, tuple, tuple]] = []
        for i in range(len(grid)):
            for j in range(i + 1, len(grid)):
                dist: float = self.distance(grid[i], grid[j])
                distances.append((dist, grid[i], grid[j]))
        
        distances.sort()
        
        circuits: list[set[tuple[int, int, int]]] = [ {x} for x in grid ]
        
        for i in range(len(distances)):
            connection_a = distances[i][1]
            connection_b = distances[i][2]
            circuit_a = None
            circuit_b = None

            for circuit in circuits:
                if connection_a in circuit:
                    circuit_a = circuit
                if connection_b in circuit:
                    circuit_b = circuit
                if circuit_a is not None and circuit_b is not None:
                    break
                
            if circuit_a != circuit_b:
                circuit_a |= circuit_b
                circuit_b.clear()
                circuits.remove(circuit_b)
                if len(circuit_a) == len(grid):
                    print(connection_a)
                    print(connection_b)
                    print(connection_a[0]*connection_b[0])
                    break
                
    
def main(filename: str) -> None:
    today: TodaysSolution = TodaysSolution()
    today.solve(filename, part)

if __name__ == '__main__':
    print(__file__)
    main(__file__)