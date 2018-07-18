package img.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse implements Serializable {
    private boolean success;
    private SearchResult result;
}