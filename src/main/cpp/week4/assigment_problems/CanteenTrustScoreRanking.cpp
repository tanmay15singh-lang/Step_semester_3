#include <iostream>
#include <string>
#include <cctype>
using namespace std;

class Canteen {
private:
    string canteenCode;
    string canteenName;
    int trustScore;

public:
    Canteen(string canteenCode, string canteenName, int trustScore) {
        this->canteenCode = canteenCode;
        this->canteenName = canteenName;
        this->trustScore = trustScore;
    }

    Canteen(string canteenCode, string canteenName)
        : Canteen(canteenCode, canteenName, 3) {
    }

    int compareTo(Canteen other) {
        if (trustScore != other.trustScore)
            return trustScore > other.trustScore ? -1 : 1;

        string code1 = canteenCode;
        string code2 = other.canteenCode;

        for (char& c : code1)
            c = tolower(c);

        for (char& c : code2)
            c = tolower(c);

        if (code1 < code2)
            return -1;

        if (code1 > code2)
            return 1;

        if (canteenName.length() < other.canteenName.length())
            return -1;

        if (canteenName.length() > other.canteenName.length())
            return 1;

        return 0;
    }

    string getCode() {
        return canteenCode;
    }

    static void rankCanteens(Canteen canteens[], int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (canteens[j].compareTo(canteens[j + 1]) > 0) {
                    Canteen temp = canteens[j];
                    canteens[j] = canteens[j + 1];
                    canteens[j + 1] = temp;
                }
            }
        }
    }
};

int main() {
    Canteen canteens[3] = {
        Canteen("HB3-C", "Spice Junction", 3),
        Canteen("hb1-c", "Grand Mess", 5),
        Canteen("HB2-C", "Southern Treats")
    };

    Canteen::rankCanteens(canteens, 3);

    for (int i = 0; i < 3; i++)
        cout << canteens[i].getCode() << endl;

    return 0;
}