public final class ImmutableLoanReceipt {

    private static boolean systemReady;

    static {
        systemReady = true;
    }

    private final String memberId;
    private final String[] bookIds;

    public ImmutableLoanReceipt(
            String memberId,
            String[] bookIds) {

        if (bookIds == null) {
            throw new IllegalArgumentException("Invalid book IDs");
        }

        this.memberId = memberId;
        this.bookIds = validateAndCopy(bookIds);
    }

    private static String[] validateAndCopy(String[] ids) {

        String[] copy = new String[ids.length];

        for (int i = 0; i < ids.length; i++) {

            String id = ids[i];

            if (!isValidBookId(id)) {
                throw new IllegalArgumentException(
                    "Invalid book ID"
                );
            }

            copy[i] = id;
        }

        return copy;
    }

    private static boolean isValidBookId(String id) {

        if (id == null || id.length() != 6) {
            return false;
        }

        if (!id.startsWith("BK-")) {
            return false;
        }

        return Character.isDigit(id.charAt(3)) &&
               Character.isDigit(id.charAt(4)) &&
               Character.isDigit(id.charAt(5));
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public ImmutableLoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException();
        }

        if (!isValidBookId(newId)) {
            throw new IllegalArgumentException(
                "Invalid book ID"
            );
        }

        String[] corrected = bookIds.clone();
        corrected[index] = newId;

        return new ImmutableLoanReceipt(
            memberId,
            corrected
        );
    }

    public static class ReferenceOnlyLoanReceipt
            extends ImmutableLoanReceipt {

        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    public static String processNightlyCirculation(
            ImmutableLoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (ImmutableLoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        try {
            new ImmutableLoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "bad"}
            );
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        ImmutableLoanReceipt r =
            new ImmutableLoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
            );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        ImmutableLoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"
            ),
            null,
            new ImmutableLoanReceipt(
                "LIB-002",
                new String[]{"BK-201"}
            )
        };

        System.out.println(
            processNightlyCirculation(receipts)
        );
    }
}