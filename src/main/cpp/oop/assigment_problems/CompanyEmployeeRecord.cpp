#include <iostream>
#include <string>
using namespace std;

class Employee {
private:
    int empId;
    string empName;
    double salary;

public:
    Employee(int empId, string empName, double salary) {
        this->empId = empId;
        this->empName = empName;
        this->salary = salary;
    }

    double getSalary() {
        return salary;
    }
};

class ManagerEmployee : public Employee {
private:
    double teamBonus;

public:
    ManagerEmployee(int empId, string empName, double salary, double teamBonus)
        : Employee(empId, empName, salary) {
        this->teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
};

class CompanyEmployeeRecord {
private:
    string name;
    string empId;
    double effectivePay;
    string slotNumber;

public:
    static int totalRecords;

    CompanyEmployeeRecord(
        string name,
        string empId,
        Employee employee,
        string slotNumber = ""
    ) {
        this->name = name;
        this->empId = empId;
        this->effectivePay = employee.getSalary();
        this->slotNumber = slotNumber;
        totalRecords++;
    }

    CompanyEmployeeRecord(
        string name,
        string empId,
        ManagerEmployee manager,
        string slotNumber = ""
    ) {
        this->name = name;
        this->empId = empId;
        this->effectivePay = manager.effectiveSalary();
        this->slotNumber = slotNumber;
        totalRecords++;
    }

    string fullProfile() {
        if (!slotNumber.empty()) {
            return name + " | Pay: Rs " +
                   to_string(effectivePay) +
                   " | Slot: " + slotNumber;
        }

        return name + " | Pay: Rs " +
               to_string(effectivePay) +
               " | Slot: no parking assigned";
    }
};

int CompanyEmployeeRecord::totalRecords = 0;

int main() {
    Employee employee1(101, "Karan", 40000);
    ManagerEmployee employee2(102, "Divya", 70000, 8000);
    Employee employee3(103, "Meera", 12000);

    CompanyEmployeeRecord record1(
        "Divya", "102", employee2, "A1"
    );

    CompanyEmployeeRecord record2(
        "Karan", "101", employee1, "A2"
    );

    CompanyEmployeeRecord record3(
        "Meera", "103", employee3
    );

    cout << record1.fullProfile() << endl;
    cout << record2.fullProfile() << endl;
    cout << record3.fullProfile() << endl;

    cout << "Total records: "
         << CompanyEmployeeRecord::totalRecords
         << endl;

    return 0;
}