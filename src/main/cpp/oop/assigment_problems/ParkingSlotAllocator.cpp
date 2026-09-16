#include <iostream>
#include <string>
using namespace std;

class ParkingSlot {
private:
    string slotNo;
    int capacity;
    int occupiedCount;

public:
    ParkingSlot(string slotNo, int capacity, int occupiedCount) {
        this->slotNo = slotNo;
        this->capacity = capacity;
        this->occupiedCount = occupiedCount;
    }

    bool allot(string vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            cout << vehicleNo << " allotted to slot "
                 << slotNo << endl;
            return true;
        }

        return false;
    }

    bool isAvailable() {
        return occupiedCount < capacity;
    }

    static int findAvailableSlot(ParkingSlot slots[], int size) {
        for (int i = 0; i < size; i++) {
            if (slots[i].isAvailable())
                return i;
        }

        return -1;
    }

    static void safeAllot(ParkingSlot slots[], int size, string vehicleNo) {
        int index = findAvailableSlot(slots, size);

        if (index != -1)
            slots[index].allot(vehicleNo);
        else
            cout << "No slots available for "
                 << vehicleNo << endl;
    }
};

int main() {
    ParkingSlot availableSlots[2] = {
        ParkingSlot("A1", 4, 3),
        ParkingSlot("A2", 5, 5)
    };

    ParkingSlot fullSlots[2] = {
        ParkingSlot("A1", 4, 4),
        ParkingSlot("A2", 5, 5)
    };

    ParkingSlot::safeAllot(
        availableSlots,
        2,
        "TN09AB1234"
    );

    ParkingSlot::safeAllot(
        fullSlots,
        2,
        "TN09AB1234"
    );

    return 0;
}