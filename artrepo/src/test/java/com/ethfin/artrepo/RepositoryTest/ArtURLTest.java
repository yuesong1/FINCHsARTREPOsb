package com.ethfin.artrepo.RepositoryTest;

import com.ethfin.artrepo.domain.ArtURL;
import com.ethfin.artrepo.repository.ArtURLRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ArtURLTest {
    @Autowired
    private  ArtURLRepository artURLRepository;

    @Test
    void insertNewURL(){
        ArtURL artURL =new ArtURL("http://121.40.136.162/images/1.png","1.png","testMD5");
        artURLRepository.insert(artURL);
    }
    @Test
    void getAllURL(){
        List<ArtURL> artURLS = new ArrayList<ArtURL>();

        artURLS=artURLRepository.findAll();
        System.out.println(artURLS);
    }
}
