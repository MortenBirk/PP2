package com.pp2.starlords.pp2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jjoe64.graphview.*;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CdfActivity extends Activity {


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cdf_activity);

        context = getApplicationContext();

        renderOldGraph("readingsOldAPI.log");
        renderNewGraph("readingsNewAPI.log");
        
    }

    private void renderNewGraph(String fileName) {

        /**
            NEW API
        **/
        LinearLayout graphLayout = (LinearLayout) findViewById(R.id.cdfnewview_graph);

        //TimeEventData[] values = TimeEventsDataStore.getListAsTimeEventData();

        // TODO: replace empty array with ground truths
        PP2ReadingData[] values = CDFHelper.graphData(FileLogger.parseFile(fileName, getApplicationContext()), new ArrayList<Position>() );

        System.out.println(FileLogger.parseFile(fileName, getApplicationContext()));

        GraphViewSeriesStyle style = new GraphViewSeriesStyle(
                Color.parseColor("#FF8800"), 1);

        GraphViewSeries series = new GraphViewSeries("CDF", style, values);

        BarGraphView graphView = new BarGraphView(context, "");

        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.GRAY);
        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.GRAY);
        graphView.getGraphViewStyle().setGridColor(Color.TRANSPARENT);

    //  graphView.setDrawDataPoints(true);
    //  graphView.setDataPointsRadius(15f);
        
        graphView.getGraphViewStyle().setNumHorizontalLabels(values.length);
        graphView.setManualYAxis(true);
        
        
        double max = 0;
        
        for (int i = 0; i < values.length; i++) {
            if( values[i].getY() > max) 
                max = values[i].getY();
        }
        
        graphView.setManualYAxisBounds(max, 0);

        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    //return "" + ((int) value);
                    if (value == Math.floor(value))
                        return ((int)value) + "";
                } else {
                    double s = value/1000;
                    return "" + ( (double) Math.round((s * 100)))/ 100; // convert to s, leave two decimal points
                }
                
                return null;
            }
        });

        graphView.addSeries(series);
        graphLayout.addView(graphView);
        
    }


    private void renderOldGraph(String fileName) {
        /**
            OLD API
        **/
        LinearLayout graphLayout = (LinearLayout) findViewById(R.id.cdfoldview_graph);

        //TimeEventData[] values = TimeEventsDataStore.getListAsTimeEventData();

        // TODO: replace empty array with ground truths
        PP2ReadingData[] values = CDFHelper.graphData(FileLogger.parseFile(fileName, getApplicationContext()), new ArrayList<Position>() );

        GraphViewSeriesStyle style = new GraphViewSeriesStyle(
                Color.parseColor("#FF8800"), 1);

        GraphViewSeries series = new GraphViewSeries("CDF", style, values);

        BarGraphView graphView = new BarGraphView(context, "");

        graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.GRAY);
        graphView.getGraphViewStyle().setVerticalLabelsColor(Color.GRAY);
        graphView.getGraphViewStyle().setGridColor(Color.TRANSPARENT);

    //  graphView.setDrawDataPoints(true);
    //  graphView.setDataPointsRadius(15f);
        
        graphView.getGraphViewStyle().setNumHorizontalLabels(values.length);
        graphView.setManualYAxis(true);
        
        
        double max = 0;
        
        for (int i = 0; i < values.length; i++) {
            if( values[i].getY() > max) 
                max = values[i].getY();
        }
        
        graphView.setManualYAxisBounds(max, 0);

        graphView.setCustomLabelFormatter(new CustomLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    //return "" + ((int) value);
                    if (value == Math.floor(value))
                        return ((int)value) + "";
                } else {
                    double s = value/1000;
                    return "" + ( (double) Math.round((s * 100)))/ 100; // convert to s, leave two decimal points
                }
                
                return null;
            }
        });

        graphView.addSeries(series);
        graphLayout.addView(graphView);
    }
}
