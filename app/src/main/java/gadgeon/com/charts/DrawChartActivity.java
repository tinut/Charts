package gadgeon.com.charts;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import gadgeon.com.charts.charts.BarChartFragment;
import gadgeon.com.charts.charts.CandlestickChartFragment;
import gadgeon.com.charts.charts.LineChartFragment;
import gadgeon.com.charts.charts.PieChartFragment;
import gadgeon.com.charts.charts.StepChartFragment;
import gadgeon.com.charts.charts.ScatterChartFragment;

public class DrawChartActivity extends AppCompatActivity {

    public static final String chartTypeArg = "chartType";
    ChartType chartType;
    int chartItemPos;
    //new menu added for next button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.draw_activity, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        if (chartItemPos == 5) {
            menu.getItem(0).setEnabled(false);
            menu.getItem(0).setVisible(false);
            this.invalidateOptionsMenu();
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.navigate) {
            chartItemPos = chartItemPos +1;
            chartType = ChartType.values()[chartItemPos];
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(chartType.toString());
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            Fragment chartFragment;
            chartFragment = getChartFragment(chartType);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.draw_chart_fragment, chartFragment);


            fragmentTransaction.commit();

        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_draw_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            chartItemPos = bundle.getInt(chartTypeArg);
            chartType = ChartType.values()[chartItemPos];
            toolbar.setTitle(chartType.toString());
            toolbar.setTitleTextColor(Color.WHITE);
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                return new LineChartFragment();
            case CHART_TYPE_BAR:
                return new BarChartFragment();
            case CHART_TYPE_PIE:
                return new PieChartFragment();
           case CHART_TYPE_SCATTER:
                return new ScatterChartFragment();
            case CHART_TYPE_STEP:
                return new StepChartFragment();
           case CHART_TYPE_CANDLESTICK:
                return new CandlestickChartFragment();
        }
        return null;
    }

}
