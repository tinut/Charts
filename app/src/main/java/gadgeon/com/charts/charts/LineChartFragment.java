package gadgeon.com.charts.charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import java.text.DecimalFormat;
import java.util.Arrays;

import gadgeon.com.charts.R;

public class LineChartFragment extends Fragment {

    private XYPlot plot;
    private XYSeries series;
    Number[] series1Numbers = {10,30,20,50,80,55};
    static final int DomainMin = 0, DomainMax = 10, RangeMin = 0, RangeMax = 100, RangeInc = 10, DomainInc = 1;


    public LineChartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_line_chart,container,false);

        plot = (XYPlot) view.findViewById(R.id.plot);

        plot.setRangeBoundaries(RangeMin, RangeMax, BoundaryMode.FIXED);
        plot.setDomainBoundaries(DomainMin, DomainMax, BoundaryMode.FIXED);

        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, RangeInc);
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, DomainInc);
        plot.setDomainValueFormat(new DecimalFormat("0"));
        plot.setRangeValueFormat(new DecimalFormat("0"));

        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setAlpha(100);
        plot.getGraphWidget().setRangeOriginLinePaint(borderPaint);
        plot.getGraphWidget().setDomainOriginLinePaint(borderPaint);
        updatePlot();
        return view;
    }

    private void updatePlot() {

        XYSeries series1= new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"RED");

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.parseColor("#FF0000"),Color.parseColor("#FF5555"),null,null);
        series1Format.setPointLabelFormatter(new PointLabelFormatter(Color.parseColor("#111111")));
        series1Format.getVertexPaint().setStrokeWidth(20);

        plot.addSeries(series1, series1Format);
        plot.redraw();
    }
}
