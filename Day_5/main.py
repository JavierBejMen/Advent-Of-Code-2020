from sys import argv
from os import linesep
from math import floor, ceil

N_ROWS = 127
N_COLUMNS = 7

def decode(line):
    line = line.strip()
    min_row = 0
    max_row = N_ROWS
    min_colum = 0
    max_colum = N_COLUMNS
    for ch in line:
        if (ch == 'B'):
            min_row += ceil((max_row-min_row)/2)
        elif (ch == 'F'):   
            max_row -= ceil((max_row-min_row)/2)
        elif (ch == 'R'):
            min_colum += ceil((max_colum-min_colum)/2)
        elif (ch == 'L'):
            max_colum -= ceil((max_colum-min_colum)/2)

    if (min_colum != max_colum or min_row != max_row):
        print("Error procesing line:{0}{1}rows:[{2},{3}]{4}columns:[{5},{6}]{7}"
            .format(line, linesep, min_row, max_row, linesep, min_colum, max_colum, linesep)
        )
        return None, None
    else:
        return min_row, min_colum
    

def seat_generator(file):
    for line in file:
        yield decode(line)
        

def main(data_path):
    with open(data_path, "r") as file:
        highest_seat = 0
        seats_taken = {}

        for row, column in seat_generator(file):
            id_seat = row * 8 + column
            if (id_seat > highest_seat): highest_seat = id_seat
            if (row in seats_taken):
                seats_taken[row].append(column)
            else:
                seats_taken[row] = [column]

        print("Part 1: {0}".format(highest_seat))

        seats_free = {row:free_column for row, column in seats_taken.items()
            for free_column in range(8) if free_column not in column}
        target_row = sorted(seats_free)[1:-1].pop()
        print(f"Part 2: {target_row * 8 + seats_free[target_row]}")
        
            
if __name__ == "__main__":
    if (len(argv) != 2):
        print("Usage: python main.py path_to_input.txt" + linesep)
    else:   
        main(argv[1])