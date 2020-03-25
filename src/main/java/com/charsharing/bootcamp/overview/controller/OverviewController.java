package com.charsharing.bootcamp.overview.controller;

import com.charsharing.bootcamp.overview.domain.Document;
import com.charsharing.bootcamp.overview.domain.OverviewPageModel;
import com.charsharing.bootcamp.overview.service.DocumentListService;
import com.charsharing.bootcamp.overview.service.OverviewService;
import com.charsharing.bootcamp.overview.service.ValidationService;
import com.charsharing.bootcamp.overview.service.captcha.ICaptchaService;
import com.charsharing.bootcamp.overview.service.captcha.ReCaptchaInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class OverviewController {

    @Value("${charSharing.page.service.redirect.url}")
    private String pageServiceURL;

    @Value("${charSharing.document.service.url}")
    private String documentServiceURL;

    @Value("${logo.png}")
    private String fullLogo;

    @Value("${favicon.ico}")
    private String favIcon;

    @Value("${styles.css}")
    private String css;

    private RestTemplateBuilder builder;

    @Autowired
    private ICaptchaService captchaService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private OverviewService overviewService;

    @Autowired
    private DocumentListService documentListService;

    @GetMapping("")
    public String indexPage(Model model) {
        model.addAttribute("overviewPageModel", new OverviewPageModel());
        final List<Document> documentList = documentListService.getDocumentList();
        model.addAttribute("documentList", documentList);
        model.addAttribute("pageServiceURL", pageServiceURL);
        model.addAttribute("fullLogo", fullLogo);
        model.addAttribute("favicon", favIcon);
        model.addAttribute("stylesCSS", css);
        return "index";
    }

    @PostMapping("")
    public String checkInput(Model model, @ModelAttribute OverviewPageModel overviewPageModel, HttpServletRequest request) {
        String title = overviewPageModel.getTitle();
        String creator = overviewPageModel.getCreator();
        model.addAttribute("overviewPageModel", overviewPageModel);
        model.addAttribute("titleEmpty", validationService.isTitleEmpty(title));
        model.addAttribute("creatorEmpty", validationService.isCreatorEmpty(creator));
        final List<Document> documentList = documentListService.getDocumentList();
        model.addAttribute("documentList", documentList);
        model.addAttribute("pageServiceURL", pageServiceURL);
        try {
            String response = request.getParameter("g-recaptcha-response");
            captchaService.processResponse(response);

            if (!validationService.isTitleEmpty(title) && !validationService.isCreatorEmpty(creator)) {

                if (overviewService.createNewDocument(title, creator)) {
                    log.info("redirect:" + pageServiceURL + title);
                    return "redirect:" + pageServiceURL + title;
                }

                model.addAttribute("documentAlreadyExists", true);
            }
        } catch (ReCaptchaInvalidException e) {
            log.info(String.valueOf(e));
            return "index";
        }
        return "index";
    }


}
