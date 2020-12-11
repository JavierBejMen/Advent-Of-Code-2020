# Advent of Code 2020

Here are my solutions to Advent of Code 2020.

## Day 1

### Part 1

#### Problem

Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.

#### Solution

Language: C++

Aproach: unnordered hash set to identify unique pairs. O(n) on average.

*If we use ordered set we get O(nlog(n)).*

<details>
<summary>Code</summary>
<br>

```c++
#include <iostream>
#include <fstream>
#include <unordered_set>

using namespace std;

int main(int argc, char *argv[]){
    //Open input
    ifstream input;

    input.open("input.txt", ifstream::in);
    if (!input.is_open()){
        cerr << "Failed to open input.txt" << endl;
        return 1;
    }
    
    int sum = 2020;
    unordered_set<int> sums;

    int number;
    while (input >> number){
        cout << number << endl;
        if (sums.find(sum - number) != sums.end()){
            cout << "Pair found: [" << sum - number << ", " << number << "]" << endl;
            cout << "Answer = " << (sum -number) * number << endl;

            input.close();
            return 0;
        }
        else{
            sums.insert(number);
        }
    }

    input.close();
    cout << "Pair not found" << endl;

    return 0;
}
```

</details>

<details>
<summary>Answer</summary>
<br>

756 * 1264 = **955584**

</details>

### Part 2

#### Problem

Find three numbers in your expense report that meet the same criteria.

#### Solution

Language: Python

Aproach: two-pointer technique on sorted array. O(n²).

<details>
<summary>Code</summary>
<br>

```Python
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

```

</details>

<details>
<summary>Answer</summary>
<br>

817 * 502 * 701 = **287503934**

</details>

## Day 2
