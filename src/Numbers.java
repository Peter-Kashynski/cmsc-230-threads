// hands out the numbers below limit, one at a time
public class Numbers {
    private final int limit;
    private int next;

    public Numbers(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("a limit below 0: " + limit);
        }
        this.limit = limit;
    }

    // the next number nobody has taken, or -1 when there is none
    public synchronized int next() {
        if (next >= limit) {
            return -1;
        }
        int n = next;
        next = next + 1;
        return n;
    }
}
