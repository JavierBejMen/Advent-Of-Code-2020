# Advent of Code 2020

Here are my solutions to Advent of Code 2020.

## Day 1

Language: c++
Aproach: hash set to identify unique pairs.

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
