package com.ethfin.artrepo.repository;



import com.ethfin.artrepo.domain.ArtURL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtURLRepository extends MongoRepository<ArtURL, String> {
}
