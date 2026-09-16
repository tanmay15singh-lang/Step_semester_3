package inheritance_polymorphism.assignment_problems;

public class Week6Problems {

    public static String registerBatch(
            String[] bibNumbers,
            double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
               " | Rejected: " + rejected;
    }

    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry)
            return "Multilevel descendant (3 generations deep)";

        if (entry instanceof RelayTeamEntry)
            return "Hierarchical sibling (independent branch)";

        return "Base generation";
    }

    public static double getTotalBalanceDue(
            RaceEntry[] entries) {

        double total = 0;

        for (RaceEntry entry : entries)
            total += entry.getBalanceDue();

        return total;
    }

    public static String announceAll(RaceEntry[] entries) {
        StringBuilder report = new StringBuilder();

        for (RaceEntry entry : entries) {
            report.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay =
                        (RelayTeamEntry) entry;

                report.append(
                        " [Team size via downcast: "
                );

                report.append(relay.getTeamSize());
                report.append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        String[] bibNumbers = {
            "BIB1001",
            "B2",
            "BIB1002",
            null
        };

        System.out.println(
            registerBatch(bibNumbers, 100)
        );

        RunnerEntry runner =
            new RunnerEntry(
                "BIB2001",
                100,
                "Open 10K"
            );

        EliteRunnerEntry elite =
            new EliteRunnerEntry(
                "BIB3001",
                150,
                "Elite Full Marathon",
                500
            );

        RelayTeamEntry relay =
            new RelayTeamEntry(
                "BIB4001",
                300,
                4
            );

        RaceEntry[] entries = {
            runner,
            elite,
            relay
        };

        runner.pay(20);
        runner.applyLateFee(10);

        System.out.println(runner.announce());
        System.out.println(elite.announce());
        System.out.println(relay.announce());

        System.out.println(
            classifyGeneration(elite)
        );

        System.out.println(
            classifyGeneration(relay)
        );

        System.out.println(
            getTotalBalanceDue(entries)
        );

        System.out.println(
            announceAll(entries)
        );

        System.out.println(
            RaceEntry.isValidDiscountCode("M123A")
        );

        System.out.println(
            RaceEntry.isValidDiscountCode("M12A")
        );

        RaceEntry payment =
            new RaceEntry("BIB5001", 50);

        payment.pay(20, "UPI");

        RaceEntry[] nightly = {
            runner,
            null,
            relay
        };

        System.out.println(
            RaceEntry.settleNight(nightly)
        );
    }
}