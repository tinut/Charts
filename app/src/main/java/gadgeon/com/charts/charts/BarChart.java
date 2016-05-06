package gadgeon.com.charts.charts;

import android.graphics.Color;
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

public class BarChart extends Fragment {

    private XYPlot plot;
    private BarFormatter formatter;
    private XYSeries series;
    Number[] series1Numbers = {2, null, 5, 2, 7, 4, 3, 7, 4, 5}; //placeholder value

    public BarChart() {
        // Required empty public constructor
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
        formatter = new BarFormatter(Color.argb(200, 100, 150, 100), Color.LTGRAY);

        plot.setTicksPerRangeLabel(3);
        plot.setRangeLowerBoundary(0, BoundaryMode.FIXED);
        plot.getGraphWidget().getGridBox().setPadding(30, 10, 30, 0);
        plot.setTicksPerDomainLabel(2);
        updatePlot();
        return  view;
    }

    private void updatePlot() {

        plot.clear();

        series = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Us");
        plot.addSeries(series, formatter);

        BarRenderer renderer = ((BarRenderer)plot.getRenderer(BarRenderer.class));
        renderer.setBarWidthStyle(BarRenderer.BarWidthStyle.VARIABLE_WIDTH);
        plot.setRangeTopMin(0);

        plot.redraw();

    }

}

