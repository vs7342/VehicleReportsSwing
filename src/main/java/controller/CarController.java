package controller;

import service.CarService;
import java.util.Hashtable;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vehicles")
public class CarController {

	@Autowired
	CarService cs;
	
	@RequestMapping("/vehiclePrices")
	public Hashtable<String,Double> getAllVehiclePrices(){
		return cs.getAllPrices();
	}
	
	@RequestMapping("/averageByType")
	public Map<String, Double> getAverageCostPerType(){
		return cs.getAverage("type");
	}
	
	@RequestMapping("/averageByBrand")
	public Map<String, Double> getAverageCostPerBrand(){
		return cs.getAverage("brand");
	}
	@RequestMapping("/averageByColor")
	public Map<String, Double> getAverageCostPerColor(){
		return cs.getAverage("color");
	}
	@RequestMapping("/averageByEngine")
	public Map<String, Double> getAverageCostPerEngine(){
		return cs.getAverage("engine");
	}
}
