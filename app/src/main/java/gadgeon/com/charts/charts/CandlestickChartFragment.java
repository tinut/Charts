package gadgeon.com.charts.charts;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.candlestick.CandlestickFormatter;
import com.androidplot.candlestick.CandlestickMaker;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.List;

import gadgeon.com.charts.R;

public class CandlestickChartFragment extends Fragment{

    private XYPlot plot;

    public CandlestickChartFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_candlestick_chart, container, false);

        plot = (XYPlot) view.findViewById(R.id.plot);

        List<Number> xVals = Arrays.asList(new Number[]{1, 2, 3, 4, 5});
        XYSeries highVals = new SimpleXYSeries(xVals,
                Arrays.asList(12, 10, 15, 8, 7), "");
        XYSeries lowVals = new SimpleXYSeries(xVals,
                Arrays.asList(3, 1, 5, 0, 2), "");
        XYSeries openVals = new SimpleXYSeries(xVals,
                Arrays.asList(5, 2, 7, 5, 3), "");
        XYSeries closeVals = new SimpleXYSeries(xVals,
                Arrays.asList(7, 9, 6, 0, 4), "");


        CandlestickFormatter formatter = new CandlestickFormatter();

        formatter.setBodyStyle(CandlestickFormatter.BodyStyle.Square);

        CandlestickMaker.make(plot, formatter,
                openVals, closeVals, highVals, lowVals);

        plot.setRangeLabel("Amount");
        plot.setTicksPerRangeLabel(3);

        plot.setRangeValueFormat(new DecimalFormat("$0.00"));

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

        plot.setDomainBoundaries(0, 6, BoundaryMode.FIXED);
        plot.setDomainLabel("Day");
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
        plot.setDomainValueFormat(new Format() {

            public StringBuffer format(Object object, StringBuffer buffer, FieldPosition field) {
                switch(((Number) object).intValue()) {
                    case 1:
                        buffer.append("Mon");
                        break;
                    case 2:
                        buffer.append("Tues");
                        break;
                    case 3:
                        buffer.append("Wed");
                        break;
                    case 4:
                        buffer.append("Thurs");
                        break;
                    case 5:
                        buffer.append("Fri");
                        break;
                    default:


                }
                return buffer;
            }

            public Object parseObject(String string, ParsePosition position) {
                return null;
            }
        });

        plot.getGraphWidget().setDomainLabelOrientation(-45);
        return view;
    }
}
