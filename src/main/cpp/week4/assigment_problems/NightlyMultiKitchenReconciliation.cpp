#include <iostream>
#include <string>
using namespace std;

class DeliveryAccount {
private:
    string studentId;
    double orderValue;

    static double defaultOrderValue;

public:
    static void initialize() {
        defaultOrderValue = 0;
    }

    DeliveryAccount(string studentId, double orderValue) {
        this->studentId = studentId;
        this->orderValue = orderValue;
    }

    DeliveryAccount(string studentId)
        : DeliveryAccount(studentId, defaultOrderValue) {
    }

    virtual double calculateSurgeFee(int delayMinutes) const {
        if (delayMinutes <= 0)
            return 0;

        return orderValue * 0.01 * delayMinutes;
    }

    double getOrderValue() const {
        return orderValue;
    }

    virtual bool isPremium() const {
        return false;
    }

    virtual ~DeliveryAccount() = default;
};

double DeliveryAccount::defaultOrderValue = 0;

class PremiumAccount : public DeliveryAccount {
public:
    PremiumAccount(string studentId, double orderValue)
        : DeliveryAccount(studentId, orderValue) {
    }

    double calculateSurgeFee(int delayMinutes) const override {
        if (delayMinutes <= 0)
            return 0;

        return DeliveryAccount::calculateSurgeFee(delayMinutes) * 0.5;
    }

    bool isPremium() const override {
        return true;
    }
};

class ReconciliationProcessor {
public:
    static void processAccount(
        DeliveryAccount& account,
        double amount,
        int delayMinutes
    ) {
        double fee = account.calculateSurgeFee(delayMinutes);

        cout << "Processed amount: Rs "
             << amount
             << " | Surge fee: Rs "
             << fee << endl;
    }

    static void processBatch(
        DeliveryAccount* accounts,
        double amounts[],
        int delayMinutesArray[],
        int size
    ) {
        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0;

        for (int i = 0; i < size; i++) {
            if (accounts[i] == nullptr) {
                nullSkipped++;
                continue;
            }

            if (accounts[i]->isPremium())
                premium++;
            else
                regular++;

            double fee =
                accounts[i]->calculateSurgeFee(delayMinutesArray[i]);

            grandTotal += fee;
            processed++;

            processAccount(
                *accounts[i],
                amounts[i],
                delayMinutesArray[i]
            );
        }

        cout << processed
             << " processed | "
             << nullSkipped
             << " null skipped | "
             << premium
             << " premium | "
             << regular
             << " regular | grand total surge fees = Rs "
             << grandTotal
             << endl;
    }
};

int main() {
    DeliveryAccount::initialize();

    DeliveryAccount regular("STU002", 300);
    PremiumAccount premium("STU001", 500);

    DeliveryAccount* accounts[3] = {
        &premium,
        nullptr,
        &regular
    };

    double amounts[3] = {
        500, 400, 300
    };

    int delayMinutes[3] = {
        10, 5, 0
    };

    ReconciliationProcessor::processBatch(
        accounts,
        amounts,
        delayMinutes,
        3
    );

    return 0;
}