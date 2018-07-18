package img;

import img.service.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchTest {

    MockMvc mockMvc;

    @Autowired
    protected SearchService search;

    @Before
    public void setup() throws Exception {

        //this.mockMvc = standaloneSetup(this.mangaController).build();// Standalone context
    }

    @Test

    public void testSearch() throws Exception {
        search.get();
    }


}
