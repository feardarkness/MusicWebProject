/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aalvarado
 */
@XmlRootElement(name="searchResponse")
public class SearchResponse {        

    static SearchResponse createErrorResponse() {
        return new SearchResponse(-1);
    }
    private List<Music> musicFound = null;
    private Integer totalFound = null;

    public SearchResponse() {
    }
    
    private SearchResponse(int num){
        totalFound = num;
    }

    public List<Music> getMusicFound() {
        return musicFound;
    }

    public Integer getTotalFound() {
        return totalFound;
    }

    public void setMusicFound(List<Music> musicFound) {
        this.musicFound = musicFound;
    }

    public void setTotalFound(Integer totalFound) {
        this.totalFound = totalFound;
    }
    
    
    
}
