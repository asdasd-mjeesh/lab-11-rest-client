package com.asdasd.mjeesh.client.communication;

import com.asdasd.mjeesh.client.dto.ProductFilter;
import com.asdasd.mjeesh.client.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductCommunication {
    private final RestTemplate restTemplate;
    private final String PRODUCTS_GENERAL_URL = "http://localhost:1337/api/products/";
    private final String GET_BY_ID_URL = PRODUCTS_GENERAL_URL + "id/";

    @Autowired
    public ProductCommunication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getById(Long id) {
        return restTemplate.getForObject(GET_BY_ID_URL + id, Product.class);
    }

    public List<Product> getAllProducts(Integer pageNo) {
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                PRODUCTS_GENERAL_URL + pageNo,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {  } );

        if (responseEntity.hasBody()) {
            return responseEntity.getBody();
        }
        return new ArrayList<>();
    }

    public void saveOrUpdateProduct(Product product) {
        if (Objects.isNull(product.getId())) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(PRODUCTS_GENERAL_URL, product, String.class);
        } else {
            restTemplate.put(PRODUCTS_GENERAL_URL, product);
        }
    }

    public void deleteProduct(Long id) {
        restTemplate.delete(PRODUCTS_GENERAL_URL + id);
    }

    public List<Product> getAllProductsByProducerId(Long producerId, Integer pageNo) {
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                PRODUCTS_GENERAL_URL + "/producer-id/" + producerId + "/" + pageNo,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {  } );

        if (responseEntity.hasBody()) {
            return responseEntity.getBody();
        }
        return new ArrayList<>();
    }

    public List<Product> getAllProductsByFilter(ProductFilter filter, Integer pageNo) {
        ResponseEntity<Object> responseEntity =
                restTemplate.postForEntity(PRODUCTS_GENERAL_URL + "filter/" + pageNo, filter, Object.class);

        if (responseEntity.hasBody()) {
            return (List<Product>) responseEntity.getBody();
        }
        return new ArrayList<>();
    }
}
