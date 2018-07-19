package img.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private String code;
    private String url;
    private String organization;
    private String description;
}
