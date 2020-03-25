package com.charsharing.bootcamp.overview.service;

import com.charsharing.bootcamp.overview.domain.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@Slf4j
@Service
public class OverviewService {

    @Value("${charSharing.document.service.url}")
    private String documentServiceURL;

    private RestTemplateBuilder templateBuilder;

    public OverviewService(RestTemplateBuilder templateBuilder) {
        this.templateBuilder = templateBuilder;
    }

    public boolean createNewDocument(String title, String creator) {
        final Date createdAt = new Date();
        Document document = new Document(title, creator, createdAt, createdAt,  false);
        log.info("create new document={}", document);
        HttpEntity<Document> request = new HttpEntity<>(document);

        try {
            final ResponseEntity<Void> entity = templateBuilder.build().exchange(
                    documentServiceURL + "documents", HttpMethod.POST, request, Void.class);
            return entity.getStatusCode().is2xxSuccessful();

        } catch (HttpClientErrorException err) {
            log.info("Exception catched: "+err);
            return false;
        }

    }

}
