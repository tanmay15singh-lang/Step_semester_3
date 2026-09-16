package inheritance_polymorphism.assignment_problems;

public class RaceEntry {
    private String bibNumber;
    private double entryFee;
    private double balanceDue;
    private double[] lateFeeHistory;
    private int lateFeeCount;

    private static int bibCounter = 0;
    private final int entryCode;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4)
            throw new IllegalArgumentException("Invalid bib number");

        if (entryFee <= 0)
            throw new IllegalArgumentException("Invalid entry fee");

        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
        this.lateFeeHistory = new double[10];
        this.lateFeeCount = 0;

        bibCounter++;
        entryCode = bibCounter;
    }

    public void pay(double amount) {
        if (amount > 0) {
            balanceDue -= amount;
            if (balanceDue < 0)
                balanceDue = 0;
        }
    }

    public void pay(double amount, String mode) {
        pay(amount);
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
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        double[] copy = new double[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++)
            copy[i] = lateFeeHistory[i];

        return copy;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber +
               " | Balance: " + balanceDue;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        if (code.charAt(0) != 'M')
            return false;

        if (!Character.isDigit(code.charAt(1)) ||
            !Character.isDigit(code.charAt(2)) ||
            !Character.isDigit(code.charAt(3)))
            return false;

        return Character.isUpperCase(code.charAt(4));
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

            if (entry instanceof RelayTeamEntry)
                relay++;
            else
                individual++;
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               relay + " relay | " +
               individual + " individual";
    }

    public static int getBibCounter() {
        return bibCounter;
    }
}