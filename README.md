# Advent of Code 2020

Here are my solutions to Advent of Code 2020. Language choose on each solution is for practice.

## Day 1

<details>
<summary> Content </summary>
<br>

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

</details>

## Day 2

<details>
<summary> Content </summary>
<br>

### Part 1

#### Problem

Find valid passwords, where *x-y c: password*, *x* describes min ocurrences and *y* max ocurrences of letter *c* in string *password*

### Solution

Language: Java

Aproach: Functional programing using parallel stream to find ocurrences that match criteria.

<details>
<summary>Answer</summary>
<br>

**607**

</details>

### Part 2

#### Problem

Find valid passwords, where *x-y c: password*, *x* describes first position and *y* second position of letter *c* in string *password*, *c* has to appear exactly one time at one of the positions.

### Solution

Language: Java

Aproach: Functional programing using parallel stream to find ocurrences that match criteria.

<details>
<summary>Answer</summary>
<br>

**321**

</details>

<details>
<summary>Code Part 1 and 2</summary>
<br>

```java

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;

public class PasswordChecker {

    private static boolean checkPassword1(String passwordLine){
        String[] elements = passwordLine.split("[\\s-]");
        int min = Integer.parseInt(elements[0]);
        int max = Integer.parseInt(elements[1]);
        char letter = elements[2].charAt(0);
        long ocurrences = elements[3].chars().filter(ch -> ch == letter).count();
        //System.out.format("%d-%d %c: %s %d\n", min, max, letter, elements[3], ocurrences);

        return  (ocurrences >= min) && (ocurrences <= max);
    }

    private static boolean checkPassword2(String passwordLine){
        String[] elements = passwordLine.split("[\\s-]");
        int first = Integer.parseInt(elements[0])-1;
        int second = Integer.parseInt(elements[1])-1;
        char letter = elements[2].charAt(0);
        boolean check = false;
        
        try{
            if (elements[3].charAt(first) == letter) check = true;
        }catch (Exception e){}
        try{
            if (elements[3].charAt(second) == letter) check = !check;
        }catch (Exception e){}
        //System.out.format("%d-%d %c: %s %b\n", first, second, letter, elements[3], check);

        return check;
    }

    public static void main(String[] args) {
        
        if (args.length != 2){
            System.out.println("Usage: PasswordChecker path policy[0, 1]");
            System.exit(0);
        }

        Path path = Paths.get(args[0]);
        if (!Files.exists(path)){
            System.out.println("File " + args[0] + " Does not exists");
            System.exit(0);
        }

        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
            switch (args[1]){
                case "0":
                    System.out.println(
                        lines.parallel().filter(PasswordChecker::checkPassword1).count()
                    );
                    break;
                case "1":
                    System.out.println(
                        lines.parallel().filter(PasswordChecker::checkPassword2).count()
                    );
                    break;
                default:
                    System.out.println("Unrecognized Policy, values accepted are 0 or 1");
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }finally{
            if (lines != null) lines.close();
        }
    }
}
```

</details>

</details>


## Day 3

<details>
<summary>Content</summary>
<br>

</details>