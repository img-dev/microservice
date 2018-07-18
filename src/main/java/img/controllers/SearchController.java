package img.controllers;

import img.model.api.Document;
import img.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService search;


    @RequestMapping(value = "/{lang}", method = RequestMethod.GET)
    public List<Document> getDocument(@PathVariable String lang ) {

        List<Document> results = search.getByLang(lang);
        return results;
    }
}
