package com.charsharing.bootcamp.overview.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class Document {
    private String title;
    private String creator;
    private Date createdAt;
    private Date updatedAt;
    private boolean isArchived;

}
