package gadgeon.com.charts.charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.xy.BarFormatter;
import com.androidplot.xy.BarRenderer;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.Arrays;

import gadgeon.com.charts.R;

public class BarChartFragment extends Fragment {

    private XYPlot plot;
    private BarFormatter formatter;
    private XYSeries series;
    Number[] series1Numbers = {2, null, 5, 2, 7, 4, 3, 7, 4, 5};

    public BarChartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_bar_chart, container, false);
        plot = (XYPlot) view.findViewById(R.id.plot);
        formatter = new BarFormatter(Color.RED, Color.LTGRAY);

        plot.setTicksPerRangeLabel(3);
        plot.setRangeLowerBoundary(0, BoundaryMode.FIXED);
        plot.getGraphWidget().getGridBox().setPadding(30, 10, 30, 0);
        plot.setTicksPerDomainLabel(2);

        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setAlpha(100);
        plot.getGraphWidget().setRangeOriginLinePaint(borderPaint);
        plot.getGraphWidget().setDomainOriginLinePaint(borderPaint);

        Paint rangeSubGridLinePaint = new Paint();
        rangeSubGridLinePaint.setColor(Color.WHITE);
        plot.getGraphWidget().setRangeSubGridLinePaint(rangeSubGridLinePaint);
        Paint domainGridLinePaint = new Paint();
        domainGridLinePaint.setColor(Color.WHITE);
        plot.getGraphWidget().setDomainSubGridLinePaint(domainGridLinePaint);
        updatePlot();
        return  view;
    }

    private void updatePlot() {

        plot.clear();

        series = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "US");
        plot.addSeries(series, formatter);

        BarRenderer renderer = ((BarRenderer)plot.getRenderer(BarRenderer.class));
        renderer.setBarWidthStyle(BarRenderer.BarWidthStyle.FIXED_WIDTH,20);
        plot.setRangeTopMin(0);

        plot.redraw();

    }

}

