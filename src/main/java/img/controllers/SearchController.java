package img.controllers;

import img.model.api.Document;
import img.service.SearchService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private static Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService search;

    @RequestMapping(value = {""} , method = RequestMethod.GET)
    public List<Document> getDocument(@RequestParam(value = "lang") Optional<String> lang) {

        log.info("{ method: getDocument, params: [ {} ] }",lang.orElse("default"));

        if(lang.isPresent())
            return search.get(lang.get());
        else
            return search.get();

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Document> getDocumentById(@PathVariable("id") String id, @RequestParam(value = "lang") Optional<String> lang ) {

        log.info("{ method: getDocumentById, params: [{} , {}] }",id,lang.orElse("default"));
        if(lang.isPresent())
            return search.getById(id, lang.get());
        else
            return search.getById(id);

    }
}
