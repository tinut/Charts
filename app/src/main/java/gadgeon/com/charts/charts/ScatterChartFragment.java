package gadgeon.com.charts.charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.RectRegion;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import gadgeon.com.charts.R;

public class ScatterChartFragment extends Fragment{

    public void ScatterChartFragment(){
        //Empty
    }

    private XYPlot plot;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_scatter_chart, container, false);
        plot = (XYPlot) view.findViewById(R.id.scatterplot);

        XYSeries series1 = generateScatter("series1", 80, new RectRegion(10, 50, 10, 50));
        plot.setDomainBoundaries(0, 80, BoundaryMode.FIXED);
        plot.setRangeBoundaries(0, 80, BoundaryMode.FIXED);

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.parseColor("#00000000"),
                Color.RED,Color.parseColor("#00000000"),null);
        series1Format.getVertexPaint().setStrokeWidth(20);

        plot.addSeries(series1, series1Format);
        plot.setTicksPerRangeLabel(1);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setAlpha(100);
        plot.getGraphWidget().setRangeOriginLinePaint(borderPaint);
        plot.getGraphWidget().setDomainOriginLinePaint(borderPaint);

        return view;
    }

    private XYSeries generateScatter(String title, int numPoints, RectRegion region) {
        SimpleXYSeries series = new SimpleXYSeries(title);
        for(int i = 0; i < numPoints; i++) {
            series.addLast(
                    region.getMinX().doubleValue() + (Math.random() * region.getWidth().doubleValue()),
                    region.getMinY().doubleValue() + (Math.random() * region.getHeight().doubleValue())
            );
        }
        return series;
    }

}