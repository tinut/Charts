package gadgeon.com.charts.charts;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.StepFormatter;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

import gadgeon.com.charts.R;


public class StepChartFragment extends android.support.v4.app.Fragment {

    private XYPlot plot;
    Number[] series1Numbers = {1, 2, 3, 4, 2, 3, 4,2,3,4,2};
    static final int DomainMin = 0, DomainMax = 10, DomainInc = 1;

    public StepChartFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_step_chart,container,false);

        plot = (XYPlot) view.findViewById(R.id.plot);

        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "POINTS");

        StepFormatter stepFormatter  = new StepFormatter(Color.parseColor("#FF0000"),Color.WHITE);
        stepFormatter.getLinePaint().setStrokeWidth(3);

        plot.addSeries(series2, stepFormatter);

        plot.setDomainBoundaries(DomainMin, DomainMax, BoundaryMode.FIXED);

        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 1);
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, DomainInc);

        plot.setTicksPerRangeLabel(1);
        plot.setTicksPerDomainLabel(1);

        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setAlpha(100);
        plot.getGraphWidget().setRangeOriginLinePaint(borderPaint);
        plot.getGraphWidget().setDomainOriginLinePaint(borderPaint);

        plot.setDomainValueFormat(new DecimalFormat("0"));

        plot.setRangeValueFormat(new Format(){

            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                Number num = (Number) obj;
                switch(num.intValue()) {
                    case 1:
                        toAppendTo.append("Init");
                        break;
                    case 2:
                        toAppendTo.append("Idle");
                        break;
                    case 3:
                        toAppendTo.append("Recv");
                        break;
                    case 4:
                        toAppendTo.append("Send");
                        break;
                    default:
                        toAppendTo.append("Unknown");
                        break;
                }
                return toAppendTo;
            }


            public Object parseObject(String source, ParsePosition pos) {return null;}
    });

        return view;
    }
}
