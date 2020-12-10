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

Aproach: ordered set to identify unique pairs on each number. O(n² log(n)).

<details>
<summary>Code</summary>
<br>

```Python
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
```

</details>

<details>
<summary>Answer</summary>
<br>
817 * 502 * 701 = **287503934**
</details>
