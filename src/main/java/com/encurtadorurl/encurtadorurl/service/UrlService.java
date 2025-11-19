package com.encurtadorurl.encurtadorurl.service;

import com.encurtadorurl.encurtadorurl.entity.Url;
import com.encurtadorurl.encurtadorurl.repository.UrlRepository;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final Hashids hashIds;

    private final String urlLocalhost = "http://localhost:8080/url/";


    public UrlService(UrlRepository urlRepository, Hashids hashIds) {
        this.urlRepository = urlRepository;
        this.hashIds = hashIds;
    }

    public String shortenerUrl(String urlFull) {

        Url url = new Url();
        url.setUrlOriginal(urlFull);
        url.setCreatedAt(LocalDate.now());
        url.setExpirationDate(LocalDate.now().plusDays(10));

        try {
            Optional<Url> newUrl = urlRepository.findByurlOriginal(urlFull);

            if (newUrl.isPresent()) {
               return urlLocalhost + newUrl.get().getUrlShort();
            }

            Long nextId = urlRepository.getNextUrlShortenerId();
            String encodeUrl = hashIds.encode(nextId);
            url.setUrlShort(encodeUrl);
            Url savedUrl = urlRepository.save(url);

            return urlLocalhost + encodeUrl;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "Error to shortener url";
    }

    public String getUrl(String shortenerUrl) {

        // Clear string
        String strClear = shortenerUrl.replace("http://localhost:8080/", "");

        try {

            Url url = urlRepository.findByurlShort(strClear).orElseThrow();

            //TODO: trocar o localdate por localdatetime por causa do minutos e seugundos
            if (url.getExpirationDate().isBefore(LocalDate.now())) {
                return "Url expired.";
            }

            return url.getUrlOriginal();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
