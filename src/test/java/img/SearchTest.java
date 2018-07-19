package img;

import img.controllers.SearchController;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;



@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class SearchTest {

    MockMvc mockMvc;

    @Autowired
    protected SearchController search;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() throws Exception {

        this.mockMvc = standaloneSetup(this.search).build();// Standalone context
    }

    @Test
    public void testA_SearchDefault() throws Exception {
        mockMvc.perform(get("/api/search").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].url", containsString("catala")) );
    }

    @Test
    public void testB_SearchLang() throws Exception {
        mockMvc.perform(get("/api/search?lang=en").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].url", containsString("angles")) );
    }

    @Test
    public void testC_SearchIdDefault() throws Exception {
        mockMvc.perform(get("/api/search/56568d9d-651a-49ff-bbc8-52d3fcee4421").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code", is("Est_Padró_Ocupació_Mitjana")))
                .andExpect(jsonPath("$[0].url", containsString("catala")) );
    }

    @Test
    public void testD_SearchIdLang() throws Exception {
        mockMvc.perform(get("/api/search/56568d9d-651a-49ff-bbc8-52d3fcee4421?lang=es").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code", is("Est_Padró_Ocupació_Mitjana")))
                .andExpect(jsonPath("$[0].url", containsString("castella")) );

    }

    @Test
    public void testE_SearchesCacheHit() throws Exception {
        mockMvc.perform(get("/api/search?lang=en").contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        assertThat( cacheManager.getCache("searches").get("en"), notNullValue());

    }

    @Test
    public void testF_IdsCacheHit() throws Exception {
        mockMvc.perform(get("/api/search/56568d9d-651a-49ff-bbc8-52d3fcee4421?lang=es").contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        assertThat( cacheManager.getCache("ids").get("56568d9d-651a-49ff-bbc8-52d3fcee4421-es"), notNullValue());

    }

}
