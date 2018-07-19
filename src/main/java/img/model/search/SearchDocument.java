package img.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDocument {
    private String code;
    private Map<String,String> url_tornada;
    private Map<String,String> notes_translated;
    private SearchOrganization organization;

}
