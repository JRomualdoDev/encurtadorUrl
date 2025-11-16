package com.encurtadorurl.encurtadorurl.controller;

import com.encurtadorurl.encurtadorurl.DTO.UrlDTO;
import com.encurtadorurl.encurtadorurl.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {

    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<String> shortenerUrl(@RequestBody UrlDTO url) {

        String shortenerUrl = urlService.shortenerUrl(url.url());

        return new ResponseEntity<>(shortenerUrl, HttpStatus.OK);
    }

    @GetMapping("{shortenerUrl}")
    public ResponseEntity<String> getUrl(@PathVariable String shortenerUrl) {

        String urlFull = urlService.getUrl(shortenerUrl);

        if (urlFull == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (urlFull.equals("Url expired.")) {
            return ResponseEntity.badRequest().body("Url expired.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, urlFull);

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
