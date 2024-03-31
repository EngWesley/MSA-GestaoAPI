package com.example.demo.cliente;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.Address;
import com.example.demo.domain.Restaurant;

public class RestaurantServiceClient implements Serializable {

    private static final long serialVersionUID = 1L;

    // Definição das constantes para o host, porta e caminho do serviço
    private static final String SERVICE_HOST = "localhost";
    private static final String SERVICE_HOST_PORT = "8085";
    private static final String SERVICE_RESTAURANT_PATH = "/restaurantes";

    public static void main(String[] args) {
        // Obtenção do endpoint completo para o serviço
        String endpoint = getEndpoint();

        // Chamadas para testar as operações REST
          System.out.println("==>GET------------------------------------");
          getRestaurants(endpoint);
          System.out.println("==>POST-----------------------------------");
          postRestaurant(endpoint, "Nome do RestauranteXPTO", "https://www.restauranteexemploXPTO.com", "https://www.restauranteexemplo.com/menu", "123-4", "$", "12345-678", "Rua Exemplo, 123", "Rio de Janeiro", "RJ", "Brasil");
          System.out.println("==>PUT------------------------------------");
          putRestaurant(endpoint, 1, "Novo Nome do Restaurante", null, null, null, null, null, null, null, null, null);;
          System.out.println("==>DELETE---------------------------------");
          deleteRestaurant(endpoint, 18);  
          System.out.println("==>GET BY CITY------------------------------------");
          getRestaurantsByCity(endpoint, "São Paulo");
    }

    // Método para obter o endpoint completo do serviço
    private static String getEndpoint() {
        return "http://" + SERVICE_HOST + ":" + SERVICE_HOST_PORT + SERVICE_RESTAURANT_PATH;
    }

    // Método para realizar a operação GET para obter todos os restaurantes
    private static void getRestaurants(String endpoint) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);
        System.out.println(response.getBody());
    }

    // Método para realizar a operação POST para adicionar um restaurante
    private static void postRestaurant(String endpoint, String name, String url, String menu, String telephone, String priceRange, String postalCode, String streetAddress, String addressLocality, String addressRegion, String addressCountry) {
        RestTemplate restTemplate = new RestTemplate();
        Address address = new Address(postalCode, streetAddress, addressLocality, addressRegion, addressCountry);
        Restaurant restaurant = new Restaurant(null, name, url, menu, telephone, priceRange, address);
        ResponseEntity<Restaurant> response = restTemplate.postForEntity(endpoint, restaurant, Restaurant.class);
        System.out.println("Restaurante criado: " + response.getBody().getId());
    }

    // Método para realizar a operação PUT para atualizar o nome de um restaurante
    private static void putRestaurant(String endpoint, long id, String name, String url, String menu, String telephone, String priceRange, String postalCode, String streetAddress, String addressLocality, String addressRegion, String addressCountry) {
        RestTemplate restTemplate = new RestTemplate();
        Address address = new Address(postalCode, streetAddress, addressLocality, addressRegion, addressCountry);
        Restaurant restaurant = new Restaurant(id, name, url, menu, telephone, priceRange, address);
        restTemplate.put(endpoint + "/" + id, restaurant);
    }

    // Método para realizar a operação DELETE para excluir um restaurante pelo ID
    private static void deleteRestaurant(String endpoint, long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(endpoint + "/" + id);
    }
    
    private static void getRestaurantsByCity(String endpoint, String city) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(endpoint + "?cidade=" + city, String.class);
        System.out.println(response.getBody());
    }
}
