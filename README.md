# Advent of Code 2020

Here are my solutions to Advent of Code 2020.

## Day 1

### Problem

Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.

### Solution

Language: C++

Aproach: unnordered hash set to identify unique pairs.

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

## Day 2

### Problem

Find three numbers in your expense report that meet the same criteria.

### Solution

Language: Python

Aproach:

<details>
<summary>Code</summary>
<br>

```Python
```

</details>

<details>
<summary>Answer</summary>
<br>

</details>
