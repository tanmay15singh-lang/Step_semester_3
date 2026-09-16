package inheritance_polymorphism.assignment_problems;

public class RaceEntry {

    private String bibNumber;
    private double entryFee;
    private double balanceDue;

    protected double[] lateFeeHistory;
    private int lateFeeCount;

    private static int bibCounter = 0;
    private final int entryCode;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        if (entryFee <= 0) {
            throw new IllegalArgumentException("Invalid entry fee");
        }

        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.balanceDue = entryFee;

        lateFeeHistory = new double[10];
        lateFeeCount = 0;

        bibCounter++;
        entryCode = bibCounter;
    }

    public void pay(double amount) {

        if (amount <= 0) {
            return;
        }

        balanceDue -= amount;

        if (balanceDue < 0) {
            balanceDue = 0;
        }
    }

    public void pay(double amount, String mode) {

        pay(amount);
        System.out.println("Paying via " + mode);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public int getEntryCode() {
        return entryCode;
    }

    protected void applyLateFee(double amount) {

        balanceDue += amount;

        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }

    public double[] getLateFeeHistory() {

        double[] result = new double[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {
            result[i] = lateFeeHistory[i];
        }

        return result;
    }

    public String announce() {

        return "Race Entry | Bib: " +
                bibNumber +
                " | Balance: " +
                balanceDue;
    }

    public static boolean isValidDiscountCode(String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) ||
            !Character.isDigit(code.charAt(2)) ||
            !Character.isDigit(code.charAt(3))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(4));
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static String settleNight(RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed +
                " processed | " +
                nullSkipped +
                " null skipped | " +
                relay +
                " relay | " +
                individual +
                " individual";
    }
}

class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(
            String bibNumber,
            double entryFee,
            String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }

    @Override
    public String announce() {

        return "Runner Entry | Bib: " +
                getBibNumber() +
                " | Category: " +
                category +
                " | Balance: " +
                getBalanceDue();
    }
}

class EliteRunnerEntry extends RunnerEntry {

    private double sponsorBonus;

    public EliteRunnerEntry(
            String bibNumber,
            double entryFee,
            String category,
            double sponsorBonus) {

        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public String announce() {

        return "Elite Runner | Bib: " +
                getBibNumber() +
                " | Category: " +
                getCategory() +
                " | Sponsor Bonus: " +
                sponsorBonus +
                " | Balance: " +
                getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(
            String bibNumber,
            double entryFee,
            int teamSize) {

        super(bibNumber, entryFee);

        if (teamSize <= 0) {
            throw new IllegalArgumentException("Invalid team size");
        }

        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {

        return "Relay Team | Bib: " +
                getBibNumber() +
                " | Team Size: " +
                teamSize +
                " | Balance: " +
                getBalanceDue();
    }
}