package com.macaddresslookup;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Controller
public class MacAddressLookUpAPIApplication {

    private RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(MacAddressLookUpAPIApplication.class);
    @Autowired
    ConfigProperties configProp;
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        restTemplate = builder.build();
        return restTemplate;
    }


    @RequestMapping (value = "/getMacAddressVendorDetails", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getMacAdressVendorDetails(@RequestParam(required = true) String ip,
                                            @RequestParam(required = true) String apikey) {

        JSONObject responsedata = new JSONObject();
        try {
            String baselookupmacvendorurl = configProp.getConfigValue("macvendorlookupurl");
            String macvendordetailsAPiurl = baselookupmacvendorurl +"?output=json&search=" + ip + "&apiKey=" + apikey;
            ResponseEntity<String> response = new RestTemplate().getForEntity(macvendordetailsAPiurl, String.class);
            Object obj = JSONValue.parse(response.getBody());
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("response  Body is-->" + response.getBody());

            responsedata.put("companyName", ((JSONObject) ((JSONObject) obj).get("vendorDetails")).get("companyName"));
        }
        catch (Exception e)
        {
            responsedata.put("Failed to retrieve info", e.getMessage());


        }
        return responsedata.toJSONString();

    }

    public static void main(String[] args) {
        SpringApplication.run(MacAddressLookUpAPIApplication.class, args);


    }



}
