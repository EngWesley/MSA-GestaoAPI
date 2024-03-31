package com.example.demo.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.domain.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.RestaurantService;

@RestController
@RequestMapping("/restaurantes")
public class RestaurantController {

	@Autowired
	RestaurantService restService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurant> findAll(){
		return restService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EntityModel<Restaurant>> findById(@PathVariable(value="id") Long id) {
		EntityModel<Restaurant> restaurantEntityModel = restService.findById(id);
		return ResponseEntity.ok().body(restaurantEntityModel);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Restaurant>> create(@RequestBody Restaurant obj) {
		EntityModel<Restaurant> restaurantEntityModel = restService.create(obj);
		
		return ResponseEntity.created(null).body(restaurantEntityModel);		
	}
	
	@PutMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<Restaurant>> update(@PathVariable(value="id") Long id, @RequestBody Restaurant obj){
		EntityModel<Restaurant> restaurantEntityModel = restService.update(id, obj);
		
		
		return ResponseEntity.ok(restaurantEntityModel);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		restService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/pais/cidade", produces = MediaType.APPLICATION_JSON_VALUE )
	public Map<String, List<Restaurant>> findRestaurantsByAddressLocality(){
		List<Restaurant> todosRestaurants = restService.findAll();
		
		Map<String, List<Restaurant>> restaurantsByCity = todosRestaurants.stream()
				.collect(Collectors.groupingBy(restaurant -> restaurant.getAddress().getAddressLocality()));
		
		return restaurantsByCity;
	}
	
	@GetMapping(value="/pais", produces = MediaType.APPLICATION_JSON_VALUE )
	public Map<String, List<Restaurant>> findRestaurantsByAddressCountry(){
		List<Restaurant> todosRestaurants = restService.findAll();
		
		Map<String, List<Restaurant>> restaurantsByCountry = todosRestaurants.stream()
				.collect(Collectors.groupingBy(restaurant -> restaurant.getAddress().getAddressCountry())); 
		
		return restaurantsByCountry;
	}
	
	 @GetMapping(params = "cidade", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurant>> findRestaurantsByCity(@RequestParam(value = "cidade") String cidade) {
        List<Restaurant> restaurants = restService.findRestaurantsByAddressLocality(cidade);
        return ResponseEntity.ok(restaurants);
    } 
		
	
}
