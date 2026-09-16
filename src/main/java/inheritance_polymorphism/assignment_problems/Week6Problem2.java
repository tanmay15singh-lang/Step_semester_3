package inheritance_polymorphism.assignment_problems;

public class Week6Problem2 {

    static String classifyGeneration(RaceEntry entry) {

        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Base generation";
    }

    static double getTotalBalanceDue(
            RaceEntry[] entries) {

        double total = 0;

        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        RunnerEntry runnerEntry =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

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

        System.out.println(
                runnerEntry.announce()
        );

        System.out.println(
                eliteEntry.announce()
        );

        System.out.println(
                relayEntry.announce()
        );

        System.out.println(
                classifyGeneration(eliteEntry)
        );

        System.out.println(
                classifyGeneration(relayEntry)
        );

        RaceEntry[] entries = {
                runnerEntry,
                eliteEntry,
                relayEntry
        };

        System.out.println(
                getTotalBalanceDue(entries)
        );
    }
}