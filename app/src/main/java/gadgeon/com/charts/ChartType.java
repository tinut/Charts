package gadgeon.com.charts;

/**
 * Created by tinu on 4/5/16.
 */
public enum ChartType {
    CHART_TYPE_LINE,
    CHART_TYPE_BAR,
    CHART_TYPE_PIE,
    CHART_TYPE_SCATTER,
    CHART_TYPE_STEP,
    CHART_TYPE_CANDLESTICK;

    @Override
    public String toString() {
        switch (this) {
            case CHART_TYPE_LINE:
                return "Line Chart";
            case CHART_TYPE_BAR:
                return "Bar Chart";
            case CHART_TYPE_PIE:
                return "Pie Chart";
            case CHART_TYPE_SCATTER:
                return "Scatter Chart";
            case CHART_TYPE_STEP:
                return "Step Chart";
            case CHART_TYPE_CANDLESTICK:
                return "Candle stick Chart";
        }
        throw  new IllegalArgumentException();
    }
}
