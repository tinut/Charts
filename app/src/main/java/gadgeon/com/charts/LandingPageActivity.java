package gadgeon.com.charts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LandingPageActivity extends AppCompatActivity {

    private class ChartListAdapter extends BaseAdapter {
        LayoutInflater inflater;

        ChartListAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return ChartType.values().length;
        }

        @Override
        public Object getItem(int position) {
            return ChartType.values()[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.list_view_charts, parent, false);
            }

            TextView  txtView = (TextView)convertView.findViewById(R.id.list_item_text);

            //newfont
            Typeface myface= Typeface.createFromAsset(getAssets(),"ForgetMeKnot-Roman.otf");
            txtView.setTypeface(myface);
            txtView.setText(ChartType.values()[position].toString());
            return convertView;
        }
    }



    ChartListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing_page);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     if(toolbar!=null) {
            toolbar.setTitle(R.string.title_charts);
            toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        ListView listView = (ListView)findViewById(R.id.chart_list_view);
        adapter = new ChartListAdapter(this);
        if(listView != null) {
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(LandingPageActivity.this, DrawChartActivity.class);
                    intent.putExtra("chartType", position);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                }
            });
        }
    }

}
