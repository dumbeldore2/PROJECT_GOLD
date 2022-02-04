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
import com.github.mikephil.charting.components.LimitLine;
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

        yvalues.add(new Entry(0,-100));
        yvalues.add(new Entry(1,-100));
        yvalues.add(new Entry(2,-100));
        yvalues.add(new Entry(3,-100));
        yvalues.add(new Entry(4,-100));
        yvalues.add(new Entry(5,100));
        yvalues.add(new Entry(6,100));
        yvalues.add(new Entry(7,100));
        yvalues.add(new Entry(8,100));
        yvalues.add(new Entry(9,100));

        LineDataSet lineDataSet = new LineDataSet(yvalues, "Data set 1");

        //pos of negatieve background van de chart
        float minYwaarde = 0;
        float maxYwaarde = 0;
        float averageYwaarde = 0;

        for (int i = 0 ; i < yvalues.size() ; i++){
            //System.out.println(yvalues.get(i).getY());

            //dit zoekt naar de laagste
            if (yvalues.get(i).getY() <= minYwaarde){
                minYwaarde = yvalues.get(i).getY();
            }

            //deze zoekt naar de hoogste
            if (yvalues.get(i).getY() >= maxYwaarde){
                maxYwaarde = yvalues.get(i).getY();
            }

            averageYwaarde += yvalues.get(i).getY();
        }
        averageYwaarde = averageYwaarde / yvalues.size();

        //test bovenstaande functies
        //System.out.println(minYwaarde);
        //System.out.println(maxYwaarde);
        System.out.println(averageYwaarde);

        //functie die beslist welke drawable best gebruikt word in deze situatie
        // de eerste is de default ²
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.gradiant_background_chart_pos_neg_v1);
        if (minYwaarde < 0 && maxYwaarde > 0){
            drawable = ContextCompat.getDrawable(this, R.drawable.gradiant_background_chart_pos_neg_v1);
        } else {
            if (minYwaarde < 0 && maxYwaarde == 0){
               drawable = ContextCompat.getDrawable(this, R.drawable.gradiant_background_chart_neg_v1);
            } else {
                if (minYwaarde == 0 && maxYwaarde > 0){
                   drawable = ContextCompat.getDrawable(this, R.drawable.gradiant_background_chart_pos_v1);
                }
            }
        }

        LimitLine limitLine = new LimitLine(averageYwaarde,"gemidelde");
        limitLine.setLineWidth(2);
        limitLine.enableDashedLine(30,15,0);
        limitLine.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        limitLine.setTextSize(12);
        //functie voor kleur van de lijn voor de avererage
        if (averageYwaarde > 0){
            limitLine.setLineColor(ContextCompat.getColor(this,R.color.c_6));
        } else {
            if (averageYwaarde < 0){
                limitLine.setLineColor(ContextCompat.getColor(this,R.color.c_7));
            } else {
                if (averageYwaarde == 0){
                    limitLine.setLineColor(ContextCompat.getColor(this,R.color.c_1));
                }
            }
        }

        lineChart.setHighlightPerDragEnabled(false);
        lineChart.setHighlightPerTapEnabled(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setColor(ContextCompat.getColor(this,R.color.c_3));
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(drawable);
        XAxis xas = lineChart.getXAxis();
        xas.setPosition(XAxis.XAxisPosition.BOTTOM);
        xas.setDrawGridLines(false);
        xas.setEnabled(true);

        //dit is alles van de rechtste yas
        YAxis yas = lineChart.getAxisRight();
        yas.setDrawGridLines(false);
        yas.setEnabled(false);

        //dit is de linkse yas
        YAxis yas2 = lineChart.getAxisLeft();
        yas2.removeAllLimitLines();
        yas2.addLimitLine(limitLine);
        yas2.setDrawGridLines(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);

        lineChart.setData(data);
    }
}