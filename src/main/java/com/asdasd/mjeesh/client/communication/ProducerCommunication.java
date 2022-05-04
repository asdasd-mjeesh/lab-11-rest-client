package com.asdasd.mjeesh.client.communication;

import com.asdasd.mjeesh.client.model.Producer;
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
public class ProducerCommunication {
    private final RestTemplate restTemplate;
    private final String PRODUCERS_GENERAL_URL = "http://localhost:1337/api/producers/";
    private final String GET_BY_ID_URL = PRODUCERS_GENERAL_URL + "id/";

    @Autowired
    public ProducerCommunication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void saveOrUpdateProducer(Producer producer) {
        if (Objects.isNull(producer.getId())) {
            restTemplate.postForEntity(PRODUCERS_GENERAL_URL, producer, Producer.class);
        } else {
            restTemplate.put(PRODUCERS_GENERAL_URL, producer);
        }
    }

    public List<Producer> getAllProducers(Integer pageNo) {
        ResponseEntity<List<Producer>> responseEntity = restTemplate.exchange(
                PRODUCERS_GENERAL_URL + pageNo,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Producer>>() {  } );

        if (responseEntity.hasBody()) {
            return responseEntity.getBody();
        }
        return new ArrayList<>();
    }

    public Producer getById(Long id) {
        return restTemplate.getForObject(GET_BY_ID_URL + id, Producer.class);
    }

    public void deleteProducer(Long id) {
        restTemplate.delete(PRODUCERS_GENERAL_URL + id);
    }
}
