import java.util.Objects;

public class Statistics {
    private String strategyUsed;
    private int nrOfPagesExplored;
    private double timeElapsed;
    private int searchDepth;
    private int requestId;

    /**
     * This constructs a Statistic report with information about the strategy used, pages explored, elapsed time,
     * search depth and the according request id
     *
     * @param strategyUsed the algorithm used for the crawl request
     * @param nrOfPagesExplored the pages explored in this crawl request
     * @param timeElapsed the time it took to crawl for this request
     * @param searchDepth how deep did the crawler go into the website for this request
     * @param requestId the according id for a particular request
     */
    public Statistics(String strategyUsed, int nrOfPagesExplored, double timeElapsed, int searchDepth, int requestId) {
        this.strategyUsed = strategyUsed;
        this.nrOfPagesExplored = nrOfPagesExplored;
        this.timeElapsed = timeElapsed;
        this.searchDepth = searchDepth;
        this.requestId = requestId;
    }

    // Getter Methods
    /**
     * Get the algorithm strategy used in this statistic.
     *
     * @return String: strategyUsed
     */
    public String getStrategyUsed() { return strategyUsed; }

    /**
     * Get the number of pages explored in this statistic.
     *
     * @return String: nrOfPagesExplored
     */
    public int getNrOfpagesExplored() { return nrOfPagesExplored; }

    /**
     * Get the time elapsed in this statistic.
     *
     * @return String: timeElapsed
     */
    public double getTimeElapsed() { return timeElapsed; }

    /**
     * Get the search depth reached in this statistic.
     *
     * @return String: searchDepth
     */
    public int getSearchDepth() { return searchDepth; }

    /**
     * Get the request id of this statistic.
     *
     * @return String: requestId
     */
    public int getRequestId() { return requestId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return requestId == that.requestId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId);
    }
}
