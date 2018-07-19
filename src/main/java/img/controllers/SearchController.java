package img.controllers;

import img.model.api.Document;
import img.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService search;

    @RequestMapping(value = {""} , method = RequestMethod.GET)
    public List<Document> getDocument(@RequestParam(value = "lang") Optional<String> lang) {

        if(lang.isPresent())
            return search.get(lang.get());
        else
            return search.get();

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Document> getDocument(@PathVariable("id") String id, @RequestParam(value = "lang") Optional<String> lang ) {

        if(lang.isPresent())
            return search.getById(id, lang.get());
        else
            return search.getById(id);

    }
}
