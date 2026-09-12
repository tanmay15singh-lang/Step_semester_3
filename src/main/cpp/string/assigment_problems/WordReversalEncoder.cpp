#include <iostream>
#include <sstream>
#include <string>
using namespace std;

string reverseEachWord(string sentence) {
    stringstream input(sentence);
    string word;
    string result;

    while (input >> word) {
        string reversedWord = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reversedWord += word[i];
        }

        result += reversedWord + " ";
    }

    return result;
}

int main() {
    string sentence;

    cout << "Enter a sentence: ";
    getline(cin, sentence);

    string result = reverseEachWord(sentence);

    cout << "Reversed sentence: " << result << endl;

    return 0;
}