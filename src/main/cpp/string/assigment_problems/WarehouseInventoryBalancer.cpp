#include <iostream>
#include <string>
using namespace std;

int calculateTotal(int quantities[], int size) {
    int total = 0;

    for (int i = 0; i < size; i++) {
        total += quantities[i];
    }

    return total;
}

void analyzeInventory(int sectionA[], int sectionB[], int size) {
    int sectionATotal = calculateTotal(sectionA, size);
    int sectionBTotal = calculateTotal(sectionB, size);

    string status;

    if (sectionATotal == sectionBTotal) {
        status = "Balanced";
    }
    else {
        status = "Not Balanced";
    }

    int highestQuantity = sectionA[0];
    string highestSection = "Section A";
    int highestIndex = 0;

    for (int i = 0; i < size; i++) {
        if (sectionA[i] > highestQuantity) {
            highestQuantity = sectionA[i];
            highestSection = "Section A";
            highestIndex = i;
        }

        if (sectionB[i] > highestQuantity) {
            highestQuantity = sectionB[i];
            highestSection = "Section B";
            highestIndex = i;
        }
    }

    cout << "Section A Total: " << sectionATotal << endl;
    cout << "Section B Total: " << sectionBTotal << endl;
    cout << "Status: " << status << endl;

    cout << "Highest Quantity: "
         << highestQuantity
         << " (" << highestSection
         << ", Item " << highestIndex + 1
         << ")" << endl;
}

int main() {
    int sectionA[100];
    int sectionB[100];
    int size;

    cout << "Enter number of items: ";
    cin >> size;

    cout << "Enter quantities for Section A:" << endl;

    for (int i = 0; i < size; i++) {
        cin >> sectionA[i];
    }

    cout << "Enter quantities for Section B:" << endl;

    for (int i = 0; i < size; i++) {
        cin >> sectionB[i];
    }

    analyzeInventory(sectionA, sectionB, size);

    return 0;
}