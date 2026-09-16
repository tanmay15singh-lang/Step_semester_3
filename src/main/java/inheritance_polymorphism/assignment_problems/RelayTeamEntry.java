package inheritance_polymorphism.assignment_problems;

public class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(
            String bibNumber,
            double entryFee,
            int teamSize) {

        super(bibNumber, entryFee);

        if (teamSize <= 0)
            throw new IllegalArgumentException("Invalid team size");

        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber() +
               " | Team Size: " + teamSize +
               " | Balance: " + getBalanceDue();
    }
}