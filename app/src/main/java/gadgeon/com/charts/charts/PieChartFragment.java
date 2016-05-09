package gadgeon.com.charts.charts;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
import gadgeon.com.charts.R;

/**
 * Created by febin on 6/5/16.
 */
public class PieChartFragment extends Fragment {

    public PieChartFragment() {
        //Empty Contructor
    }

    private PieChart pie;

    private Segment s1;
    private Segment s2;
    private Segment s3;
    private Segment s4;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        s1 = new Segment("s1", 10);
        s2 = new Segment("s2", 1);
        s3 = new Segment("s3", 10);
        s4 = new Segment("s4", 10);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pie_chart, container, false);
        pie = (PieChart) view.findViewById(R.id.pieplot);

        SegmentFormatter sf1 = new SegmentFormatter(Color.parseColor("#FF0000"));
        sf1.getLabelPaint().setTextSize(50);

        SegmentFormatter sf2 = new SegmentFormatter(Color.parseColor("#0000FF"));
        sf2.getLabelPaint().setTextSize(50);

        SegmentFormatter sf3 = new SegmentFormatter(Color.parseColor("#00FF00"));
        sf3.getLabelPaint().setTextSize(50);

        SegmentFormatter sf4 = new SegmentFormatter(Color.parseColor("#FF00FF"));
        sf4.getLabelPaint().setTextSize(50);

        pie.addSeries(s1, sf1);
        pie.addSeries(s2, sf2);
        pie.addSeries(s3, sf3);
        pie.addSeries(s4, sf4);

        pie.getBorderPaint().setColor(Color.TRANSPARENT);
        pie.getBackgroundPaint().setColor(Color.parseColor("#FFFFFF"));
        pie.getTitleWidget().getLabelPaint().setColor(Color.parseColor("#000000"));

        pie.redraw();
        return  view;
    }
}
