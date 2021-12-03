package com.rest.backend.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import com.rest.backend.JenkinsScraper;

import org.apache.http.client.ClientProtocolException;
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

  @Value("${jenkins-server}")
  private String jenkinsServer;

  @GetMapping("/message")
  public String message(@RequestHeader MultiValueMap<String, String> headers) {
    String msg = "";
    headers.forEach((key, value) -> {
      log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
    });
    msg = restTemplate.getForObject("http://localhost:" + serverPort + "/c/message", String.class);
    return "Message from RestCall: " + msg;
  }

  @GetMapping("/jenkins")
  public String jenkins(@RequestHeader MultiValueMap<String, String> headers) {
    log.info("Incoming REST consumed: /jenkins");
    String msg = "";
    headers.forEach((key, value) -> {
      log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
    });
    // msg = restTemplate.getForObject("http://localhost:"+serverPort+"/c/message",
    // String.class);
    return "Message from RestCall: " + msg;
  }

// http://192.168.1.138:8088
// /a/callJenkins
  final String jenkinsUser = "vdk84";
  final String jenkinsToken = "119bf1bff861a209e23d95804d895d28a1";  
  private static final String SUB_URL = "/buildWithParameters?REST_URL=http://192.168.1.138:8088&REST_SERVICE=/a/callJenkins&REST_EXTRA=test";

  @GetMapping("/callJenkins")
  public String callJenkins() {
    log.info("Incoming REST consumed: /callJenkins");
    try {
      return JenkinsScraper.scrape(jenkinsServer + "/job/test_rest2" + SUB_URL, jenkinsUser, jenkinsToken);
    } catch (ClientProtocolException e1) {
      e1.printStackTrace();
      return "Error\n"+e1.getMessage();

    } catch (IOException e1) {
      return "Error\n"+e1.getMessage();
    }
  }
}
