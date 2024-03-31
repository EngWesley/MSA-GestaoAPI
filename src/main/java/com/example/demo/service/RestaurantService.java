package com.example.demo.service;

import java.io.Serializable;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.example.demo.controller.RestaurantController;
import com.example.demo.domain.Restaurant;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.RestaurantRepository;

@Service
public class RestaurantService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	RestaurantRepository restRepository;
	
	
	public List<Restaurant> findAll(){	
		var teste = restRepository.findAll();
		teste.stream().forEach(p->p.add(linkTo(methodOn(RestaurantController.class).findById(p.getId())).withSelfRel()));
		return teste;
	}
	
	public EntityModel<Restaurant> findById(Long id) {
		Restaurant restaurant = restRepository.findById(id).orElse(null);
		if(restaurant == null) {
			throw new  ResourceNotFoundException("Objeto não encontrado para id: "+id);
		}	
		 Link selfLink = linkTo(methodOn(RestaurantController.class).findById(id)).withSelfRel();
	     EntityModel<Restaurant> entityModel = EntityModel.of(restaurant, selfLink);

	        return entityModel;
	}
	
	public EntityModel<Restaurant> create(Restaurant obj) {
		Restaurant createdRestaurant = restRepository.save(obj);
		Long id = createdRestaurant.getId();
		
		Link selfLink = linkTo(methodOn(RestaurantController.class).findById(id)).withSelfRel();
        EntityModel<Restaurant> entityModel = EntityModel.of(createdRestaurant, selfLink);

        return entityModel;
	}
	
	public EntityModel<Restaurant> update(Long id, Restaurant obj) {
		
		var entity = restRepository.findById(obj.getId()).orElseThrow(
				()-> new ResourceNotFoundException("Objeto não encontrado com o id: "+ id));
		
		entity.setName(obj.getName());
		entity.setUrl(obj.getUrl());
		entity.setPriceRange(obj.getPriceRange());
		entity.setTelephone(obj.getTelephone());
		entity.setMenu(obj.getMenu());
		entity.setAddress(obj.getAddress());
		
		Restaurant updateRestaurant = restRepository.save(entity);
		
		Link selfLink = linkTo(methodOn(RestaurantController.class).findById(updateRestaurant.getId())).withSelfRel();
        EntityModel<Restaurant> entityModel = EntityModel.of(updateRestaurant, selfLink);

        return entityModel;
	}
	
	public void delete(Long id) {
		findById(id);
		if(id == null) {
			 new ResourceNotFoundException("Objeto não encontrado com o id: "+ id);
		}
		restRepository.deleteById(id);
	}
	
	public List<Restaurant> findRestaurantsByAddressLocality(String city){	
		return restRepository.findRestaurantesByAddressLocality(city);
	}
	
	public List<Restaurant> findRestaurantsByAddressCountry(String Country){
		return restRepository.findRestaurantesByAddressCountry(Country);
	}

}
