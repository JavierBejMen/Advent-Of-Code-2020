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