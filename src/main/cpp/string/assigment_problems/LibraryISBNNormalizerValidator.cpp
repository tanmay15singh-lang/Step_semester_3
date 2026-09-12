#include <iostream>
#include <string>
#include <cctype>
using namespace std;

string normalizeCode(string raw) {
    // Remove leading and trailing spaces
    int start = raw.find_first_not_of(' ');
    int end = raw.find_last_not_of(' ');

    if (start == string::npos) {
        return "";
    }

    string code = raw.substr(start, end - start + 1);

    // Convert first 3 characters to uppercase
    for (int i = 0; i < 3 && i < code.length(); i++) {
        code[i] = toupper(code[i]);
    }

    return code;
}

string validateAndFormat(string code) {
    if (code.length() != 13) {
        return "Invalid: code must be exactly 13 characters";
    }

    // Check first 3 characters are letters
    for (int i = 0; i < 3; i++) {
        if (!isalpha(code[i])) {
            return "Invalid: publisher code must be 3 letters";
        }
    }

    // Check remaining 10 characters are digits
    for (int i = 3; i < 13; i++) {
        if (!isdigit(code[i])) {
            return "Invalid: code body must contain only digits";
        }
    }

    string publisherCode = code.substr(0, 3);
    string year = code.substr(3, 4);
    string catalogNumber = code.substr(7, 6);

    string result = "[" + publisherCode + "] YEAR: "
                    + year + " | CATALOG: "
                    + catalogNumber;

    return result;
}

int main() {
    string rawCode;

    cout << "Enter ISBN-style code: ";
    getline(cin, rawCode);

    string normalizedCode = normalizeCode(rawCode);
    string result = validateAndFormat(normalizedCode);

    cout << result << endl;

    return 0;
}