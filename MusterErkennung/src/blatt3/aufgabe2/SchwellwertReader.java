package blatt3.aufgabe2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchwellwertReader {
	
	static Map<Integer, Double> table = new HashMap<Integer, Double>();
	static File schwellwertFile = new File("data" + File.separator
			+ "schwellwert.txt");
	
	static int schwellwert = 4;

	public static void main(String[] args) {
		populateTable(schwellwertFile);
		
		List<Double> leftList = getLeftList(table, schwellwert);
		List<Double> rightList = getRightList(table, schwellwert);
		
		System.out.println(getAlpha(leftList, rightList));
		
	}
	
	static double getAlpha(List<Double> left, List<Double> right) {
		double result = getListSum(right)/ getListSum(left);
		return result;
	}
	
	static double getListSum(List<Double> list) {
		double sum = 0;
		for(double value : list) {
			sum = sum + value;
		}
		return sum;
	}
	
	static List<Double> getLeftList(Map<Integer, Double> map, int schwellwert) {
		List<Double> result = new ArrayList<Double>();
		for(int i = 0; i < schwellwert; i++) {
			result.add(map.get(i));
		}
		return result;
	}
	
	static List<Double> getRightList(Map<Integer, Double> map, int schwellwert) {
		List<Double> result = new ArrayList<Double>();
		for(int i = map.size()-1; i > schwellwert; i--) {
			result.add(map.get(i));
		}
		return result;
	}
	
	static double getStandardDeviation(List<Double> list) {
		double average = getAverage(list);
		double sum = 0;
		for(double value : list) {
			sum = sum + Math.pow((value - average), 2);
		}
		return Math.sqrt(sum/list.size());
	}
	
	static double getAverage(List<Double> list) {
		double result = 0;
		for(double value : list) {
			result = result + value;
		}
		return result/list.size();
	}
	
	static double getExpectation(List<Double> list) {
		double result = 0;
		for(double value : list) {
			result = result + value;
		}
		return result;
	}

	static void populateTable(File fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();

			while (line != null) {
				String[] lineSplit = line.split(" ");
				table.put(Integer.parseInt(lineSplit[0]), Double.parseDouble(lineSplit[1]));
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static List<Double> convertToList(String line) {
		ArrayList<Double> result = new ArrayList<Double>();
		String[] lineSplit = line.split(" ");
		for(String value : lineSplit) {
			result.add(Double.parseDouble(value));
		}
		return result;
	}
	
	static void printList(List<Double> list) {
		double total = 0;
		for(double value : list){
			total = total + value;
			System.out.println(value);
		}
		System.out.println("Total: " + total);
		System.out.println("List length: " + list.size());
	}

}
