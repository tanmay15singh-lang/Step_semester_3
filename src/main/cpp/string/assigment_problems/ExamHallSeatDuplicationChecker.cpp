#include <iostream>
using namespace std;
void checkDuplicateSeats(int seatNumbers[], int size) {
    bool duplicateFound = false;
    for (int i = 0; i < size; i++) {
        for (int j = i + 1; j < size; j++) {
            if (seatNumbers[i] == seatNumbers[j]) {
                cout << "Duplicate Seat Number Found: "
                     << seatNumbers[i] << endl;

                duplicateFound = true;
                break;
            }
        }
    }

    if (!duplicateFound) {
        cout << "No Duplicate Seats Found" << endl;
    }
}
int main() {
    int seatNumbers[100];
    int size;
    cout << "Enter number of students: ";
    cin >> size;
    cout << "Enter seat numbers:" << endl;
    for (int i = 0; i < size; i++) {
        cin >> seatNumbers[i];
    }
    checkDuplicateSeats(seatNumbers, size);
    return 0;
}