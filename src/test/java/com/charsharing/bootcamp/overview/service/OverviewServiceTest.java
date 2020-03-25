package com.charsharing.bootcamp.overview.service;

import com.charsharing.bootcamp.overview.domain.Document;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.*;


public class OverviewServiceTest {

    @Mock
    private RestTemplateBuilder templateBuilder;
    @Mock
    private RestTemplate template;
    @Mock
    private ResponseEntity<Void> response;
    @Captor
    private ArgumentCaptor<HttpEntity<Document>> documentEntityCaptor;


    private OverviewService overviewService;


    @BeforeMethod
    public void setUp() {
        initMocks(this);
        this.overviewService = new OverviewService(templateBuilder);
        when(templateBuilder.build()).thenReturn(template);
    }

    @Test
    public void shouldCreateNewDocument() {
        //given
        when(response.getStatusCode()).thenReturn(HttpStatus.OK);
        when(template.exchange(anyString(), eq(HttpMethod.POST), documentEntityCaptor.capture(), eq(Void.class)))
                .thenReturn(response);
        //when
        boolean created = overviewService.createNewDocument("foo", "myuser");

        //then
        assertEquals(created, true);
        Document document = documentEntityCaptor.getValue().getBody();
        assertEquals(document.getTitle(), "foo");
        assertEquals(document.getCreator(), "myuser");
        assertNotNull(document.getCreatedAt());
    }

    @Test
    public void shouldNotCreateNewDocument() {
        //given
        when(response.getStatusCode()).thenThrow(new HttpClientErrorException(HttpStatus.CONFLICT));
        when(template.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Void.class)))
                .thenReturn(response);
        //when
        boolean created = overviewService.createNewDocument("foo", "myuser");

        //then
        assertEquals(created, false);

    }
}