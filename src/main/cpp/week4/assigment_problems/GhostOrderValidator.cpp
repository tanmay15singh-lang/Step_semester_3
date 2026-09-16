#include <iostream>
#include <string>
#include <vector>
#include <stdexcept>
using namespace std;

class FoodOrder {
private:
    string studentName;
    string dishName;
    bool delivered;

public:
    FoodOrder(string studentName, string dishName) {
        if (studentName.empty() || dishName.empty() ||
            studentName.find_first_not_of(' ') == string::npos ||
            dishName.find_first_not_of(' ') == string::npos) {
            throw invalid_argument("Invalid order");
        }

        this->studentName = studentName;
        this->dishName = dishName;
        delivered = false;
    }

    void markDelivered() {
        if (!delivered) {
            delivered = true;
            cout << "Order marked delivered" << endl;
        } else {
            cout << "Order was already delivered" << endl;
        }
    }

    static void processBatch(string rawOrders[][2], int size) {
        int valid = 0;
        int rejected = 0;

        for (int i = 0; i < size; i++) {
            try {
                FoodOrder order(rawOrders[i][0], rawOrders[i][1]);
                valid++;
            } catch (const invalid_argument&) {
                rejected++;
            }
        }

        cout << "Valid: " << valid
             << " | Rejected: " << rejected << endl;
    }
};

int main() {
    string rawOrders[4][2] = {
        {"Ravi", "Paneer Butter Masala"},
        {"", "Chole Bhature"},
        {"Meera", " "},
        {"Divya", "Veg Biryani"}
    };

    FoodOrder::processBatch(rawOrders, 4);

    FoodOrder order("Ravi", "Paneer Butter Masala");
    order.markDelivered();
    order.markDelivered();

    return 0;
}