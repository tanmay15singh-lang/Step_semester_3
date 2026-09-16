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

class InternEmployee : public Employee {
private:
    double stipendCap;

public:
    InternEmployee(int empId, string empName, double salary, double stipendCap)
        : Employee(empId, empName, salary) {
        this->stipendCap = stipendCap;
    }

    double effectiveSalary() {
        if (getSalary() < stipendCap)
            return getSalary();

        return stipendCap;
    }
};

int main() {
    Employee employee(101, "Aditi", 40000);
    ManagerEmployee manager(102, "Rohan", 70000, 8000);
    InternEmployee intern(103, "Meera", 12000, 10000);

    cout << "Plain employee pay: Rs "
         << employee.getSalary() << endl;

    cout << "Manager effective pay: Rs "
         << manager.effectiveSalary() << endl;

    cout << "Intern effective pay: Rs "
         << intern.effectiveSalary() << endl;

    return 0;
}