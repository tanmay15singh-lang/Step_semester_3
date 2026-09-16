#include <iostream>
#include <stdexcept>
#include <algorithm>
using namespace std;

class SurgeFeeCalculator {
private:
    const double minimumSurgePercent;

public:
    SurgeFeeCalculator(double minimumSurgePercent)
        : minimumSurgePercent(minimumSurgePercent) {
    }

    final:
    double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0)
            throw invalid_argument("Invalid order value or delay");

        if (delayMinutes == 0)
            return 0;

        int firstBracket = min(delayMinutes, 5);
        int secondBracket = min(max(delayMinutes - 5, 0), 10);
        int thirdBracket = max(delayMinutes - 15, 0);

        double tieredFee =
            orderValue * 0.005 * firstBracket +
            orderValue * 0.01 * secondBracket +
            orderValue * 0.02 * thirdBracket;

        double floorFee =
            orderValue * minimumSurgePercent / 100.0;

        return max(tieredFee, floorFee);
    }
};

int main() {
    try {
        SurgeFeeCalculator calculator(1.0);

        cout << "Rs "
             << calculator.calculateSurgeFee(500, 0)
             << endl;

        cout << "Rs "
             << calculator.calculateSurgeFee(500, 1)
             << endl;

        cout << "Rs "
             << calculator.calculateSurgeFee(500, 16)
             << endl;
    }
    catch (const invalid_argument& error) {
        cout << error.what() << endl;
    }

    return 0;
}