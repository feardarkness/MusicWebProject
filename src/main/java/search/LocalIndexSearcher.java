/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author aalvarado
 */
@Stateless
public class LocalIndexSearcher {

    public LocalIndexSearcher() {
    }

    public List<Music> search(String palabra) throws IOException, ParseException {
        List<Music> musicList = new ArrayList();
        TopDocs hits = null;
        QueryParser parser = null;
        IndexSearcher isearcher = null;
        Query query = null;
        try (Analyzer analyzer = new StandardAnalyzer();
                Directory directory = FSDirectory.open(Paths.get("E:\\Index"));
                DirectoryReader ireader = DirectoryReader.open(directory)) {
            isearcher = new IndexSearcher(ireader);
            parser = new QueryParser("letra", analyzer);
            query = parser.parse(palabra);
            hits = isearcher.search(query, 10);
            System.out.println(hits.totalHits);
            for (int i = 0; i < hits.scoreDocs.length; i++) {
                System.out.println(hits.scoreDocs[i]);
                Document doc = isearcher.doc(hits.scoreDocs[i].doc);
                System.out.println(doc.getField("titulo"));
                System.out.println(doc.getField("letra"));
                System.out.println(doc.getField("id"));
                Music music = new Music();
                music.setArtist(doc.getField("artista").stringValue());
                music.setLyric(doc.getField("letra").stringValue());
                music.setTitle(doc.getField("titulo").stringValue());
                musicList.add(music);
            }
        }
        return musicList;
    }
}
