package com.example.demo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Address;
import com.example.demo.domain.Restaurant;
import com.example.demo.repository.RestaurantRepository;

@Configuration
public class RestaurantClient implements CommandLineRunner{

/*	@Autowired
	private RestaurantRepository restrepocliente; */
	
	@Override
	public void run(String... args) throws Exception {
		
	/*	restrepocliente.deleteAll();


		
		Address adress1 = new Address();
		adress1.setPostalCode("123456-050");
		adress1.setStreetAddress("Rua ABC, 123");
		adress1.setAddressLocality("São Paulo");
		adress1.setAddressRegion("SP");
		adress1.setAddressCountry("Brasil");
		
		Restaurant restaurante01 = new Restaurant();
		
		restaurante01.setName("Restaurante Paulistano");
		restaurante01.setMenu("cardapio XPTO");
		restaurante01.setTelephone("2323-2323");
		restaurante01.setPriceRange("R$10,00...");
		restaurante01.setUrl("www.restaurantepaulistano.com.br");
		restaurante01.setAddress(adress1);
		
		
		restrepocliente.save(restaurante01);
	} 

	*/
}
}

