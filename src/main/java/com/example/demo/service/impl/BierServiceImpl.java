package com.example.demo.service.impl;

import com.example.demo.entity.Bier;
import com.example.demo.service.BierService;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class BierServiceImpl implements BierService {

    Logger logger = LoggerFactory.getLogger(BierServiceImpl.class);
    private final static String API_URL = "https://api.punkapi.com/v2/";
    private final RestTemplate restTemplate;

    public BierServiceImpl() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        this.restTemplate = new RestTemplate(requestFactory);
    }

    public Bier getRandom() {
        String resourceUrl = API_URL + "/beers/random";
        logger.info("getting data from {} ", resourceUrl);
        return Objects.requireNonNull(restTemplate.getForObject(resourceUrl, Bier[].class))[0];
    }

    @Override
    public Bier[] findBier(String text) {
        String resourceUrl = API_URL + "beers/";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(resourceUrl)
                .queryParam("beer_name", text);
        logger.info("getting data from {} ", builder.toUriString());
        return restTemplate.getForObject(builder.toUriString(), Bier[].class);
    }
}
