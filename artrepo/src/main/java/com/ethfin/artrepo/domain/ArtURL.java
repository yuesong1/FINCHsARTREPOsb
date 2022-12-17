package com.ethfin.artrepo.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ArtURL {
    @Id
    private ObjectId id;
    private String url;
    private String name;
    private String md5;

    public ArtURL(String url, String name, String md5) {
        this.url = url;
        this.name = name;
        this.md5 = md5;
    }
}
