package img.model.api;

import img.model.search.SearchDocument;
import img.model.search.SearchResponse;

import java.util.ArrayList;
import java.util.List;

public class DocumentFactory {

    public List<Document> fromResponse(SearchResponse response) {

        List<Document> results = new ArrayList<Document>();

        for(SearchDocument doc: response.getResult().getResults()) {

            results.add(new Document(doc.getCode(),
                                     doc.getUrl_tornada().getOrDefault("ca",""),
                                     doc.getOrganization().getDescription_translated().getOrDefault("ca","")
            ));
        }

        return results;
    }
}
