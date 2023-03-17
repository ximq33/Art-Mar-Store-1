package com.ArtMar_Store.Api.api.products;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ArtMar_Store.Api.api.products.AdminPageController.admin_baseURL;


@RestController
@RequestMapping(admin_baseURL)
public class AdminPageController {

    static final String admin_baseURL = "/admin";
}
