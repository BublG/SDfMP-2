package com.example.productlist;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.productlist.db.Result;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        loadResults();
    }

    public void loadResults() {
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        List<Result> results = MainActivity.db.resultDao().getAll();
        for (Result result : results) {
            TableRow tableRow = new TableRow(this);
            tableRow.addView(createSearchTextView(result.getSearch()));
            tableRow.addView(createQuantityTextView(result.getQuantity()));
            tableRow.addView(createDateTextView(result.getDate()));
            tableLayout.addView(tableRow);
        }
    }

    public TextView createSearchTextView(String search) {
        TableRow.LayoutParams searchLayoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                4.0f
        );
        TextView dateTextView = new TextView(this);
        dateTextView.setLayoutParams(searchLayoutParams);
        dateTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        dateTextView.setText(search);
        dateTextView.setTextSize(20);
        return dateTextView;
    }


    public TextView createDateTextView(String date) {
        TableRow.LayoutParams dateLayoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                5.0f
        );
        TextView dateTextView = new TextView(this);
        dateTextView.setLayoutParams(dateLayoutParams);
        dateTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        dateTextView.setText(date);
        dateTextView.setTextSize(20);
        return dateTextView;
    }

    public TextView createQuantityTextView(int quantity) {
        TableRow.LayoutParams resLayoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        TextView resTextView = new TextView(this);
        resTextView.setLayoutParams(resLayoutParams);
        resTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        resTextView.setTextSize(20);
        resTextView.setText(String.valueOf(quantity));
        return resTextView;
    }
}
