package com.charsharing.bootcamp.overview.service;


import com.charsharing.bootcamp.overview.domain.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class DocumentListService {
    @Value("${charSharing.document.service.url}")
    String documentServiceURL;
    private RestTemplateBuilder builder;

    public DocumentListService(RestTemplateBuilder builder) {

        this.builder = builder;
    }

    public List<Document> getDocumentList() {
        final Document[] documentArray = builder.build().getForEntity(documentServiceURL + "documents", Document[].class).getBody();
        return isNull(documentArray) ? Collections.emptyList() : Arrays.asList(documentArray);
    }
}
