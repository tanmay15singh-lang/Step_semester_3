#include <iostream>
#include <string>
using namespace std;

void findLongestStreak(string signalLog) {
    int currentStreak = 1;
    int longestStreak = 1;
    char longestColor = signalLog[0];

    for (int i = 1; i < signalLog.length(); i++) {
        if (signalLog[i] == signalLog[i - 1]) {
            currentStreak++;
        }
        else {
            currentStreak = 1;
        }

        if (currentStreak > longestStreak) {
            longestStreak = currentStreak;
            longestColor = signalLog[i];
        }
    }

    cout << "Longest Streak: '"
         << longestColor
         << "' repeated "
         << longestStreak
         << " times" << endl;
}

int main() {
    string signalLog;

    cout << "Enter signal log: ";
    cin >> signalLog;

    findLongestStreak(signalLog);

    return 0;
}