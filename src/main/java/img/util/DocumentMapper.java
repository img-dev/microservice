package img.util;

import img.model.api.Document;
import img.model.search.SearchDocument;
import img.model.search.SearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentMapper {

    @Value("${search.default.lang}")
    private String defaultLang;

    public List<Document> fromResponse(SearchResponse response) {
        return fromResponse(response,defaultLang);
    }

    public List<Document> fromResponse(SearchResponse response, String lang) {

        List<Document> results = new ArrayList<Document>();

        for(SearchDocument doc: response.getResult().getResults()) {

            results.add(new Document(doc.getCode(),
                                     doc.getUrl_tornada().getOrDefault(lang,""),
                                     doc.getOrganization().getDescription_translated().getOrDefault(lang,""),
                                     doc.getNotes_translated().getOrDefault(lang,"")
            ));
        }

        return results;
    }
}
