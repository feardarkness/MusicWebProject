/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.search;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import org.junit.Test;

/**
 *
 * @author aalvarado
 */
public class SearchTest {
    @Test
    public void SearchIsRespondingOk(){        
        expect().
                statusCode(200).
                when().
                get("/MusicWebProject/msod/search/fear");
    }
}
