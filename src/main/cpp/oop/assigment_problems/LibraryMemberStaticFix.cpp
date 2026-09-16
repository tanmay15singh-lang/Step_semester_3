#include <iostream>
#include <string>
using namespace std;

class BrokenLibraryMember {
public:
    static string name;
    static string memberId;
    static int booksIssued;

    BrokenLibraryMember(string name, string memberId, int booksIssued) {
        BrokenLibraryMember::name = name;
        BrokenLibraryMember::memberId = memberId;
        BrokenLibraryMember::booksIssued = booksIssued;
    }

    void printMember() {
        cout << name << endl;
    }
};

string BrokenLibraryMember::name = "";
string BrokenLibraryMember::memberId = "";
int BrokenLibraryMember::booksIssued = 0;

class LibraryMember {
private:
    string name;
    string memberId;
    int booksIssued;

    static string libraryName;
    static int memberCount;

public:
    LibraryMember(string name, int booksIssued) {
        this->name = name;
        this->booksIssued = booksIssued;

        memberCount++;
        this->memberId = "LM-" + to_string(1000 + memberCount);
    }

    void printMemberCard() {
        cout << name << " | " << memberId << endl;
    }

    static void printTotalMembers() {
        cout << "Total members: "
             << memberCount << endl;
    }
};

string LibraryMember::libraryName = "Central Library";
int LibraryMember::memberCount = 0;

int main() {
    cout << "Broken version:" << endl;

    BrokenLibraryMember member1("Aditi", "LM-1001", 2);
    BrokenLibraryMember member2("Rohan", "LM-1002", 3);

    member1.printMember();
    member2.printMember();

    cout << endl;

    cout << "Fixed version:" << endl;

    LibraryMember member3("Aditi", 2);
    LibraryMember member4("Rohan", 3);

    member3.printMemberCard();
    member4.printMemberCard();

    LibraryMember::printTotalMembers();

    return 0;
}