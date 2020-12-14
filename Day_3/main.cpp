#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <numeric>
#include <vector>

using namespace std;

int main(int argc, char const *argv[])
{
    //Open input
    ifstream input;

    input.open("input.txt", ifstream::in);
    if (!input.is_open()){
        cerr << "Failed to open input.txt" << endl;
        return 1;
    }

    vector<pair<int, double>> slopes = {
        make_pair(1,1), make_pair(1,3),
        make_pair(1,5), make_pair(1,7),
        make_pair(2,0.5)
    };
    
    int slope_problem_1 = 1;
    vector<int> trees(slopes.size(), 0);
    vector<int> x_pos(slopes.size(), 0);
    transform(slopes.begin(), slopes.end(), x_pos.begin(), [](auto pair) -> auto {
        return pair.first;
    });

    string line;
    input >> line;
    int n_line = 1;

    while (input >> line){
        for(int i = 0; i < slopes.size(); i++){
            if(n_line % slopes[i].first == 0){
                int x_pos = slopes[i].second*n_line;
                if (line[x_pos % line.size()] == '#')
                    trees[i]++;
            }  
        }

        n_line++;
    }

    int i = 0;
    for (auto tree : trees){
        cout << "[" << slopes[i].first << ", " << slopes[i].second << "]: " << tree << endl;
        i++;
    }
    cout << "Part 1 Answer: " << trees[slope_problem_1] << endl;
    cout << "Part 2 Answer: " << accumulate(trees.begin(), trees.end(), (long int)1, multiplies<>()) << endl;

    input.close();
    return 0;
}
