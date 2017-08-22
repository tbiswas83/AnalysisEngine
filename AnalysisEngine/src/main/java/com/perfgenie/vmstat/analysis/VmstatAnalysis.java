package com.perfgenie.vmstat.analysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import com.perfgenie.util.math.MathStats;

public class VmstatAnalysis {
	
	private static final int RUN_Q_COL=1;
	private static final int B_COL=2;
	private static final int MEM_SWAP_COL=3;
	private static final int MEM_FREE_COL=4;
	private static final int MEM_BUFF_COL=5;
	private static final int MEM_CACHE_COL=6;
	private static final int SWAP_SI_COL=7;
	private static final int SWAP_SO_COL=8;
	private static final int IO_BI_COL=9;
	private static final int IO_BO_COL=10;
	private static final int SYS_INT_COL=11;
	private static final int SYS_CS_COL=12;
	private static final int CPU_USR_COL=13;
	private static final int CPU_SYS_COL=14;
	private static final int CPU_IDL_COL=15;
	private static final int CPU_STL_COL=16;
	private static final int TS_DATE_COL=17;
	private static final int TS_TIME_COL=18;
	private static final int TS_ZONE_COL=19;
	private static final String COL_SEP=" ";

	
	public VmstatAnalysis() {

	}

	public double processVmstat() {
		String fileName="src/main/resources/vmstat.out";
		List<String[]> list = new ArrayList<>();
		List<Integer> cpuUsr=new ArrayList<Integer>();
		List<Integer> memSwap=new ArrayList<Integer>();
		List<Integer> swapSI=new ArrayList<Integer>();
		List<Integer> swapSO=new ArrayList<Integer>();
		
		  System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			
			list = stream.filter(line -> !line.contains("procs") && !line.contains("swpd"))
					.map(line -> splitVmstat(line))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		list.forEach(line -> {
			cpuUsr.add(Integer.parseInt(line[CPU_USR_COL-1]));
			memSwap.add(Integer.parseInt(line[MEM_SWAP_COL-1]));
			swapSI.add(Integer.parseInt(line[SWAP_SI_COL-1]));
			swapSO.add(Integer.parseInt(line[SWAP_SO_COL-1]));
			});
		System.out.println("cpuUsr" + cpuUsr);
		System.out.println("memSwap" + memSwap);
		System.out.println("swapSI" + swapSI);
		System.out.println("swapSO" + swapSO);
		
		double sdMemSwap=MathStats.calcStdDev(memSwap);
		System.out.println("std dev val:" + sdMemSwap );
		
		//sd.evaluate((double[])memSwap.toArray(new double[memSwap.size()]));
		return sdMemSwap;
	}
	
	private static String[] splitVmstat(String line) {
		List<String> mappedColumns= new ArrayList<String>();
		String[] columns=line.split(COL_SEP);
		for(int i=0; i<columns.length; i++) {
			if(columns[i].compareTo("")!=0) {
				mappedColumns.add(columns[i]);
			}
		}
		return (String[]) mappedColumns.toArray(new String[mappedColumns.size()]);
	}

}

