package img.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {

    private long count;
    private String sort;
    private List<SearchDocument> results;
}
