package com.rest.backend.controller;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c")
public class CController {
  private static Logger log = LoggerFactory.getLogger(CController.class);

  @GetMapping("/message")
  public String message(@RequestHeader MultiValueMap<String, String> headers) {
    headers.forEach((key, value) -> {
      log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
    });
    return "Greetings from Service-C!" + String.format("Listed %d headers", headers.size());
  }
}
