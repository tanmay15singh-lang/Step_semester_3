#include <iostream>
#include <sstream>
#include <string>
using namespace std;

void parseInventoryRecord(string csvLine) {
    stringstream input(csvLine);

    string productName;
    string sku;
    string quantity;

    getline(input, productName, ',');
    getline(input, sku, ',');
    getline(input, quantity, ',');

    string extraField;

    if (getline(input, extraField, ',')) {
        cout << "Invalid Record" << endl;
        return;
    }

    if (productName.empty() || sku.empty() || quantity.empty()) {
        cout << "Invalid Record" << endl;
        return;
    }

    cout << "Product: " << productName
         << " | SKU: " << sku
         << " | Qty: " << quantity << endl;
}

int main() {
    string csvLine;

    cout << "Enter inventory record: ";
    getline(cin, csvLine);

    parseInventoryRecord(csvLine);

    return 0;
}