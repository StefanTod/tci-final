public class Statistics {
    private String strategyUsed;
    private int nrOfPagesExplored;
    private double timeElapsed;
    private int searchDepth;
    private int requestId;

    public Statistics(String strategyUsed, int nrOfPagesExplored, double timeElapsed, int searchDepth, int requestId) {
        this.strategyUsed = strategyUsed;
        this.nrOfPagesExplored = nrOfPagesExplored;
        this.timeElapsed = timeElapsed;
        this.searchDepth = searchDepth;
        this.requestId = requestId;
    }

    // Getter Methods
    public String getStrategyUsed() { return strategyUsed; }
    public int getNrOfpagesExplored() { return nrOfPagesExplored; }
    public double getTimeElapsed() { return timeElapsed; }
    public int getSearchDepth() { return searchDepth; }
    public int getRequestId() { return requestId; }
}
