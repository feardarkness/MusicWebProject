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
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aalvarado
 */
public class BasicSearchTest {

    // create small index to test some basics
    @Before
    public void setUp() {
        // i could recreate the index, but, when more data comes this will take too long
        // so, i will use the already created index
    }

    @Test
    public void testResponseIsOK() {
        expect().
                statusCode(200).
                when().
                get("/MusicWebProject/msod/search/fear");
    }

    @Test
    public void testResponseIs405WithNoData() {
        expect().
                statusCode(405). // 405 Method not allowed
                when().
                get("/MusicWebProject/msod/search/");
    }

    @Test
    public void testFoundItems() {
        get("/MusicWebProject/msod/search/fear").
                then().
                body("totalFound", not(equalTo(0)));
    }

    // this could change when the index grows, and maybe gonna be difficult to test
    @Test
    public void testFoundOneItem() {
        get("/MusicWebProject/msod/search/appeal").
                then().
                body("totalFound", equalTo(1));
    }

    // g 0o g 1e has configured 2048 max length in his input, maybe i should test if accepts more without UI >_<
    @Test
    public void testNegativeWithMoreThan100Characters() {
        get("/MusicWebProject/msod/search/appealeqwrewqrwerwrerweqwreqrwerewreqrwerwerwe appealeqwrewqrwerwrerweqwreqrwerewreqrwerwerwe appealeqwrewqrwerwrerweqwreqrwerewreqrwerwerwe appealeqwrewqrwerwrerweqwreqrwerewreqrwerwerwe appealeqwrewqrwerwrerweqwreqrwerewreqrwerwerwe").
                then().
                body("totalFound", equalTo(-1));
    }
}
