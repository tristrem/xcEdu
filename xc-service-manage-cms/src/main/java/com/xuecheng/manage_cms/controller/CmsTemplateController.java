package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.config.cms.CmsTemplateControllerApi;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cms/template")
public class CmsTemplateController implements CmsTemplateControllerApi {

    @Autowired
    PageService pageService;
    @Autowired
    RestTemplate restTemplate;
    @Override
    @GetMapping("/getAll")
    public List<CmsTemplate> getAll() {
        return pageService.findAll();
    }

    @GetMapping("/test")
    public String test(){
        return "123";
    }

}
