from collections import OrderedDict
import traceback

def main():
    try:
        file = open("../Day_1/input.txt", "r")

        numbers = list(map(lambda line: int(line), file.read().splitlines()))
        ocurrences = OrderedDict()
        target = 2020

        for i in numbers:
            for j in numbers:
                if target - i - j in ocurrences:
                    print("{0} * {1} * {2} = {3}".format(i, j, target-i-j, i * j * (target-i-j)))
                    file.close()
                    return 0
                else:
                    ocurrences[j] = None

        print("Fail")
        file.close()
        return 1

    except:
        traceback.print_exc()
        return 1

if __name__ == "__main__":
    main()
