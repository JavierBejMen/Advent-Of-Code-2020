def main():
    with open("input.txt", "r") as file:
        numbers = [int(line) for line in file]
    
    
    numbers.sort()
    target = 2020

    for i in range(0, len(numbers)-2):
        li = i+1
        ri = len(numbers)-1
        while(li < ri):
            sum_partial = numbers[i] + numbers[li] + numbers[ri]
            if (sum_partial == target):
                print("{0} * {1} * {2} = {3}".format(
                    numbers[i], numbers[li], numbers[ri],
                    numbers[i] * numbers[li] * numbers[ri]
                ))
                return 0

            elif (sum_partial < target):
                li += 1

            else:
                ri -= 1

    print("Fail")
    return 0


if __name__ == "__main__":
    main()
