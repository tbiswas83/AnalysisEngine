package com.perfgenie.util.math;

import java.util.List;

public class MathStats {
	
	public MathStats() {

	}

	public static double calcStdDev(List<Integer> dataSet){
	    double sum = 0;
	    double sum2 = 0;
	    double sd = 0;

	    for (int i = 0; i < dataSet.size(); i++) {
	        sum = sum + dataSet.get(i);
	    }

	    double mean = sum / dataSet.size();

	    for (int i = 0; i < dataSet.size(); i++) {
	    	sum2=sum2 + ((dataSet.get(i) - mean)*(dataSet.get(i) - mean));
	    }

	    double mean2 = sum2 / (dataSet.size()-1);

	    sd = Math.sqrt(mean2);
	    
		return sd;
		
	}

}
