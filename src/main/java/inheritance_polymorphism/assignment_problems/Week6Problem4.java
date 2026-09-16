package inheritance_polymorphism.assignment_problems;

public class Week6Problem4 {

    static String announceAll(
            RaceEntry[] entries) {

        StringBuilder report =
                new StringBuilder();

        for (RaceEntry entry : entries) {

            report.append(
                    entry.announce()
            );

            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entry;

                report.append(
                        " [Team size via downcast: "
                );

                report.append(
                        relay.getTeamSize()
                );

                report.append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        RunnerEntry runnerEntry =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        RaceEntry[] fleet = {
                runnerEntry,
                relayEntry
        };

        System.out.println(
                announceAll(fleet)
        );
    }
}