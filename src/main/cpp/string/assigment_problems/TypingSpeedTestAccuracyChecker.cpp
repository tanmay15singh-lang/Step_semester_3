#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

void checkTypingAccuracy(string original, string typed) {
    int matchedCharacters = 0;
    int firstMismatchPosition = -1;

    for (int i = 0; i < original.length(); i++) {
        if (original[i] == typed[i]) {
            matchedCharacters++;
        }
        else if (firstMismatchPosition == -1) {
            firstMismatchPosition = i + 1;
        }
    }

    int totalCharacters = original.length();

    double accuracy = (matchedCharacters * 100.0) / totalCharacters;

    cout << fixed << setprecision(2);

    cout << "Matched: " << matchedCharacters
         << "/" << totalCharacters
         << " | Accuracy: " << accuracy << "%";

    if (firstMismatchPosition == -1) {
        cout << " | No Mismatches" << endl;
    }
    else {
        cout << " | First Mismatch at position "
             << firstMismatchPosition
             << " ('" << original[firstMismatchPosition - 1]
             << "' vs '" << typed[firstMismatchPosition - 1]
             << "')" << endl;
    }
}

int main() {
    string original;
    string typed;

    cout << "Enter original passage: ";
    getline(cin, original);

    cout << "Enter typed passage: ";
    getline(cin, typed);

    checkTypingAccuracy(original, typed);

    return 0;
}