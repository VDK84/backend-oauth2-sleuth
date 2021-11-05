package com.rest.backend.controller;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/a")
public class AController {

  private static Logger log = LoggerFactory.getLogger(AController.class);

  @Autowired
  private RestTemplate restTemplate;
  
  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/message")
  public String message(@RequestHeader MultiValueMap<String, String> headers) {
    String msg = "";
    headers.forEach((key, value) -> {
      log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
    });
    msg = restTemplate.getForObject("http://localhost:"+serverPort+"/c/message", String.class);
    return "Message from RestCall: " + msg;
  }

}
