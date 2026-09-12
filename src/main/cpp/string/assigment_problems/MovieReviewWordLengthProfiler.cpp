#include <iostream>
#include <sstream>
#include <string>
using namespace std;

void classifyWordLengths(string review) {
    stringstream sentence(review);
    string word;

    int shortWords = 0;
    int mediumWords = 0;
    int longWords = 0;

    while (sentence >> word) {
        int wordLength = word.length();

        if (wordLength >= 1 && wordLength <= 4) {
            shortWords++;
        }
        else if (wordLength >= 5 && wordLength <= 8) {
            mediumWords++;
        }
        else {
            longWords++;
        }
    }

    cout << "Short: " << shortWords
         << " | Medium: " << mediumWords
         << " | Long: " << longWords << endl;
}

int main() {
    string review;

    cout << "Enter movie review: ";
    getline(cin, review);

    classifyWordLengths(review);

    return 0;
}