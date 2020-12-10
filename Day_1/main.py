from collections import OrderedDict

def main():
    with open("input.txt", "r") as file:
        numbers = list(map(lambda line: int(line), file.read().splitlines()))
    
    ocurrences = OrderedDict()
    target = 2020

    for i in numbers:
        for j in numbers:
            if target - i - j in ocurrences:
                print("{0} * {1} * {2} = {3}".format(i, j, target-i-j, i * j * (target-i-j)))
                return 0
            else:
                ocurrences[j] = None

    print("Fail")
    return 1

if __name__ == "__main__":
    main()
