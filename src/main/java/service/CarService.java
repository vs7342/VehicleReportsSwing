package service;

import model.Car;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

@Service
public class CarService {
	
	private Hashtable <Integer, Car> cars = new Hashtable<Integer, Car>();
	
	public CarService()
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader("vehicles.csv"));
			Integer id = 1;
			String line="";
			while((line=br.readLine())!=null)
			{
				String[] vehicles = line.split(",");
				Car c = new Car(
							vehicles[0].trim(),
							Integer.parseInt(vehicles[1].trim()),
							vehicles[2].trim(),
							vehicles[3].trim(),
							vehicles[4].trim(),
							Double.parseDouble(vehicles[5].trim()),
							Integer.parseInt(vehicles[6].trim())
						);
				cars.put(id, c);
				id++;
			}
			br.close();
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Hashtable<String,Double> getAllPrices()
	{
		Hashtable<String,Double> details = new Hashtable<String,Double>();
		Set<Integer> keys = cars.keySet();
		for(Integer key: keys)
		{
			details.put("VIN="+cars.get(key).getVin()+"&Type="+cars.get(key).getType(),cars.get(key).getPrice());
		}
		return details;
		
	}
	
	
	public Map<String, Double> getAverage(String byClause)
	{
		LinkedHashMap<String, Double> solution = new LinkedHashMap<String, Double>();
		Set<Integer> keys = cars.keySet();
		
		//This loop will store all vehicle types in the solution HM
		for(Integer key: keys)
		{
			if(byClause.equalsIgnoreCase("type"))
				solution.put(cars.get(key).getType(), 0.0);
			if(byClause.equalsIgnoreCase("brand"))
				solution.put(cars.get(key).getBrand(), 0.0);
			if(byClause.equalsIgnoreCase("engine"))
				solution.put(cars.get(key).getEngineType(), 0.0);
			if(byClause.equalsIgnoreCase("color"))
				solution.put(cars.get(key).getColor(), 0.0);
		}
		
		//calculates average for each type
		Set<String> carTypes = solution.keySet();
		for(String carType: carTypes)
		{
			Double sum = 0.0;
			int count = 0;
			String type = "";
			for(Integer key: keys)
			{
				if(byClause.equalsIgnoreCase("type"))
					type = cars.get(key).getType();
				if(byClause.equalsIgnoreCase("brand"))
					type = cars.get(key).getBrand();
				if(byClause.equalsIgnoreCase("engine"))
					type = cars.get(key).getEngineType();
				if(byClause.equalsIgnoreCase("color"))
					type = cars.get(key).getColor();
				
				if(type.equalsIgnoreCase(carType))
				{
					sum = sum+cars.get(key).getPrice();
					count++;
				}
			}
			solution.replace(carType, sum/count);
		}

		Map<String,Double> m= sortByValue(solution);
		return m;
	}
	
	public static <key, value extends Comparable<? super value>> Map<key, value> sortByValue( Map<key,value> map )
	{
	     Map<key,value> sortedMap = new LinkedHashMap<>();
	     
	     Stream <Entry<key,value>> stream = map.entrySet().stream();
	
	     stream.sorted(Comparator.comparing(entry -> entry.getValue())).forEachOrdered(entry ->sortedMap.put(entry.getKey(),entry.getValue()));
	
	     return sortedMap;
	}
}
