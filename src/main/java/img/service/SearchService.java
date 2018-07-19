package img.service;

import img.model.api.Document;
import img.model.search.SearchResponse;
import img.util.DocumentMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;

import java.util.List;

@Service
public class SearchService {

    Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Value("${search.host}")
    private String searchHost;

    @Value("${search.default.lang}")
    private String defaultLang;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DocumentMapper factory;


    @Cacheable(cacheNames = "searches",  key="'defaultLang'")
    public List<Document> get() {
        return get(defaultLang);
    }

    @Cacheable(cacheNames = "searches",  key="#lang")
    public List<Document> get(String lang) {

        SearchResponse response = restTemplate.getForObject(searchHost, SearchResponse.class);
        List<Document> results = factory.fromResponse(response, lang);
        return results;
    }

    @Cacheable(cacheNames = "ids", key="#id.concat('-').concat('default')")
    public List<Document> getById(String id) {
        return getById(id, defaultLang);
    }

    @Cacheable(cacheNames = "ids", key="#id.concat('-').concat(#lang)")
    public List<Document> getById(String id, String lang) {

        SearchResponse response = restTemplate.getForObject(searchHost+"?q=id:"+id, SearchResponse.class);
        List<Document> results = factory.fromResponse(response, lang);
        return results;
    }

}
