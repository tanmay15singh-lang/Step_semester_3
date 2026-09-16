#include <iostream>
#include <string>
using namespace std;

class BookIssue {
private:
    string title;
    string borrowerName;
    int daysOverdue;

public:
    BookIssue(string title, string borrowerName, int daysOverdue) {
        this->title = title;
        this->borrowerName = borrowerName;
        this->daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0)
            return daysOverdue * 5;
        return 0;
    }

    bool isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    string getTitle() {
        return title;
    }

    int getDaysOverdue() {
        return daysOverdue;
    }

    static double totalFineCollected(BookIssue issues[], int size) {
        double total = 0;

        for (int i = 0; i < size; i++)
            total += issues[i].fineAmount();

        return total;
    }
};

int main() {
    BookIssue issues[5] = {
        BookIssue("Clean Code", "Aditi", 18),
        BookIssue("Effective Java", "Rohan", 5),
        BookIssue("Refactoring", "Karan", 0),
        BookIssue("DSA Handbook", "Meera", 21),
        BookIssue("Design Patterns", "Arjun", 9)
    };

    for (int i = 0; i < 5; i++) {
        cout << issues[i].getTitle() << " - "
             << issues[i].getDaysOverdue() << " days - ";

        if (issues[i].isSeverelyOverdue())
            cout << "Severely overdue";
        else
            cout << "OK";

        cout << endl;
    }

    cout << "Total fine collected: Rs "
         << BookIssue::totalFineCollected(issues, 5)
         << endl;

    return 0;
}