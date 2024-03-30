package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	  
	@Query("SELECT r FROM Restaurant r WHERE r.address.addressLocality = :addressLocality")
	List<Restaurant> findRestaurantesByAddressLocality(@Param("addressLocality")String addressLocality);
	  
	@Query("SELECT r FROM Restaurant r WHERE r.address.addressCountry = :addressCountry")
	List<Restaurant> findRestaurantesByAddressCountry(@Param("addressCountry")String addressCountry);
}
