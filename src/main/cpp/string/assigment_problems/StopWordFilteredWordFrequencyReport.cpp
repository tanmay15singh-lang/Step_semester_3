#include <iostream>
#include <sstream>
#include <string>
#include <map>
#include <vector>
#include <algorithm>
#include <cctype>
using namespace std;

void printFilteredWordFrequency(string feedback) {
    // Convert the text to lowercase
    for (int i = 0; i < feedback.length(); i++) {
        feedback[i] = tolower(feedback[i]);
    }

    // Remove punctuation
    for (int i = 0; i < feedback.length(); i++) {
        if (feedback[i] == '.' || feedback[i] == ',') {
            feedback[i] = ' ';
        }
    }

    stringstream input(feedback);
    string word;

    map<string, int> wordFrequency;

    while (input >> word) {
        if (word != "the" &&
            word != "was" &&
            word != "and" &&
            word != "a" &&
            word != "is" &&
            word != "of" &&
            word != "in") {

            wordFrequency[word]++;
        }
    }

    vector<pair<string, int>> words(
        wordFrequency.begin(),
        wordFrequency.end()
    );

    sort(words.begin(), words.end(),
         [](pair<string, int> first, pair<string, int> second) {
             return first.second > second.second;
         });

    for (auto word : words) {
        cout << word.first << ": "
             << word.second << endl;
    }
}

int main() {
    string feedback;

    cout << "Enter feedback: ";
    getline(cin, feedback);

    printFilteredWordFrequency(feedback);

    return 0;
}