package graphics;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import msrcpsp.scheduling.Schedule;
import msrcpsp.scheduling.Task;

import org.jfree.chart.ChartUtilities;

public class Graphic{
   public static void drowGraphic(XYSeries dataset1, XYSeries dataset2,XYSeries dataset3)throws Exception
   {
      
	   final XYSeriesCollection dataset = new XYSeriesCollection( );
	      dataset.addSeries( dataset1 );
	      dataset.addSeries( dataset2 );
	      dataset.addSeries( dataset3 );

	   JFreeChart xylineChart = ChartFactory.createXYLineChart(
		         "AG-MSRCPSP", 
		         "Generation",
		         "Duration", 
		         dataset,
		         PlotOrientation.VERTICAL, 
		         true, true, false);
		      
		      int width = 640; /* Width of the image */
		      int height = 480; /* Height of the image */ 
		      File XYChart = new File( "assets/XYLineChart.jpeg" ); 
		      ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
         }
      
}

