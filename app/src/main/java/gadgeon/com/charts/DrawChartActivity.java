package gadgeon.com.charts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import gadgeon.com.charts.charts.BarChart;
import gadgeon.com.charts.charts.LineChart;
import gadgeon.com.charts.charts.PieChartFragment;
import gadgeon.com.charts.charts.StepChart;
import gadgeon.com.charts.charts.ScatterChartFragment;

public class DrawChartActivity extends AppCompatActivity {

    public static final String chartTypeArg = "chartType";
    ChartType chartType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int chartItemPos = bundle.getInt(chartTypeArg);
            chartType = ChartType.values()[chartItemPos];
            Fragment chartFragment;

            chartFragment = getChartFragment(chartType);
            if(chartFragment == null)
                return;

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction  = fm.beginTransaction();
            fragmentTransaction.replace(R.id.draw_chart_fragment, chartFragment);
            fragmentTransaction.commit();
        }

    }

    Fragment getChartFragment(ChartType chartType)
    {
        switch (chartType) {
            case CHART_TYPE_LINE:
                return new LineChart();
            case CHART_TYPE_BAR:
                return new BarChart();
            case CHART_TYPE_PIE:
                return new PieChartFragment();
           case CHART_TYPE_SCATTER:
                return new ScatterChartFragment();
            case CHART_TYPE_STEP:
                return new StepChart();
/*            case CHART_TYPE_CANDLESTICK:
                return "Candle stick Chart";*/
        }
        return null;
    }

}
