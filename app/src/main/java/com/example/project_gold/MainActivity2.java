package com.example.project_gold;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    //iniiten van de variablelen
    LineChart lineChart;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getWindow().setNavigationBarColor(getResources().getColor(R.color.c_5));
        getWindow().setStatusBarColor(getResources().getColor(R.color.c_1));

        //variablelen conecten
        lineChart = findViewById(R.id.line_chart);

        //linechart stuff
        ArrayList<Entry> yvalues = new ArrayList<>();

        yvalues.add(new Entry(0,10));
        yvalues.add(new Entry(1,50));
        yvalues.add(new Entry(2,10));
        yvalues.add(new Entry(3,10));
        yvalues.add(new Entry(4,90));
        yvalues.add(new Entry(5,10));
        yvalues.add(new Entry(6,10));
        yvalues.add(new Entry(7,-100));
        yvalues.add(new Entry(8,-300));
        yvalues.add(new Entry(9,0));

        LineDataSet lineDataSet = new LineDataSet(yvalues, "Data set 1");


        //Drawable drawable = ContextCompat.getDrawable(this, R.drawable.background_2);

        lineChart.setHighlightPerDragEnabled(false);
        lineChart.setHighlightPerTapEnabled(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setColor(Color.rgb(0,250,154));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        lineDataSet.setDrawFilled(true);
        //lineDataSet.setFillDrawable(drawable);
        XAxis xas = lineChart.getXAxis();
        xas.setPosition(XAxis.XAxisPosition.BOTTOM);
        xas.setDrawGridLines(false);
        xas.setEnabled(true);

        YAxis yas = lineChart.getAxisRight();
        yas.setDrawGridLines(false);
        yas.setEnabled(false);

        YAxis yas2 = lineChart.getAxisLeft();
        yas2.setDrawGridLines(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);
    }
}