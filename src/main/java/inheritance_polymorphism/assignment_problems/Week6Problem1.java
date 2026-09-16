public package inheritance_polymorphism.assignment_problems;

public class Week6Problem1 {

    static String registerBatch(
            String[] bibNumbers,
            double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {

            try {
                new RaceEntry(bib, entryFee);
                registered++;
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " +
                registered +
                " | Rejected: " +
                rejected;
    }

    public static void main(String[] args) {

        try {
            new RaceEntry("B1", 50);
        }
        catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        r.pay(30);

        System.out.println(
                r.getBalanceDue()
        );

        String[] bibs = {
                "BIB1",
                "B1",
                "BIB2"
        };

        System.out.println(
                registerBatch(bibs, 80)
        );
    }
} {

import inheritance_polymorphism.assignment_problems.RaceEntry;

}
