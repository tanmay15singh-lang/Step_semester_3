package inheritance_polymorphism.assignment_problems;

public class Week6Problem5 {

    public static void main(String[] args) {

        System.out.println(
                RaceEntry.isValidDiscountCode("M123A")
        );

        System.out.println(
                RaceEntry.isValidDiscountCode("M12A")
        );

        System.out.println(
                RaceEntry.isValidDiscountCode("X123A")
        );

        RaceEntry r =
                new RaceEntry(
                        "BIB5001",
                        50
                );

        r.pay(10, "UPI");

        EliteRunnerEntry eliteEntry =
                new EliteRunnerEntry(
                        "BIB3001",
                        150,
                        "Elite Full Marathon",
                        500
                );

        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        RaceEntry[] entries = {
                eliteEntry,
                null,
                relayEntry
        };

        System.out.println(
                RaceEntry.settleNight(entries)
        );

        System.out.println(
                RaceEntry.getBibCounter()
        );
    }
}