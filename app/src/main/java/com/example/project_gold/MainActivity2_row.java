package com.example.project_gold;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class MainActivity2_row extends ArrayAdapter<String> {

    Context context;
    String stringData[];
    String stringType[];
    String stringAmount[];
    int anInt[];


    public MainActivity2_row(@NonNull Context c, String datum[] ,String type[] ,
                             String amount[] ) {
        super(c,R.layout.list_view_row_ma2,datum);
        this.context = c;

        this.stringData = datum;
        this.stringType = type;
        this.stringAmount = amount;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.list_view_row_ma2,parent,false);
        TextView textView1,textView2,textView3,textView4;

        textView1 = row.findViewById(R.id.text_1);
        textView2 = row.findViewById(R.id.text_2);
        textView3 = row.findViewById(R.id.text_3);
        textView4 = row.findViewById(R.id.text_4);

        setBackground(position,parent);

        textView1.setText(position+"");
        textView2.setText(stringData[position]);
        textView3.setText(stringType[position]);
        textView4.setText(stringAmount[position]);

        return row;
    }

    public void setBackground(int i,@NonNull ViewGroup parent){
        int amount = Integer.parseInt(stringAmount[i]);
        System.out.println(amount);

        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.list_view_row_ma2,parent,false);

        if (amount > 0){
            row.setBackground(ContextCompat.getDrawable(context,R.drawable.background_listview_pos));
        } else {
            row.setBackground(ContextCompat.getDrawable(context,
                    R.drawable.background_listview_neg));
        }
    }
}
