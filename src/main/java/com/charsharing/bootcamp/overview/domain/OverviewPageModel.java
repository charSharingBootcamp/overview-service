package com.charsharing.bootcamp.overview.domain;

import lombok.Data;
import java.util.List;

@Data
public class OverviewPageModel {
    private String title;
    private String creator;
    private String pageServiceURL;
    private List<Document> documentList;
    private String fullLogo;

}
