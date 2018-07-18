package img.service;

import img.model.api.Document;
import img.model.api.DocumentFactory;
import img.model.search.SearchResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;

import java.util.List;

@Service
public class SearchService {

    Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Value("${search.host}")
    private String searchHost;

    private RestTemplate restTemplate = new RestTemplate();
    private DocumentFactory factory = new DocumentFactory();


    public List<Document> get() {

        SearchResponse response = restTemplate.getForObject(searchHost, SearchResponse.class);
        logger.info("Response: succces {} count {} result {} ",response.isSuccess(),response.getResult().getCount(), response.getResult().getResults().get(0));
        List<Document> results = factory.fromResponse(response);
        logger.info("Results {} ", results);
        return results;
    }

    public List<Document> getByLang(String lang) {

        SearchResponse response = restTemplate.getForObject(searchHost, SearchResponse.class);
        logger.info("Response: succces {} count {} result {} ",response.isSuccess(),response.getResult().getCount(), response.getResult().getResults().get(0));
        List<Document> results = factory.fromResponse(response);
        logger.info("Results {} ", results);
        return results;
    }

}
