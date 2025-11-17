package com.encurtadorurl.encurtadorurl.repository;

import com.encurtadorurl.encurtadorurl.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    /**
     * Execute :
     * CREATE SEQUENCE url_short_seq
     *     START WITH 1
     *     INCREMENT BY 1;
     * This is necessary for postgres
     */
    @Query(value = "SELECT nextval('url_short_seq')", nativeQuery = true)
    Long getNextUrlShortenerId();

    Optional<Url> findByurlOriginal(String urlFull);
    Optional<Url> findByurlShort(String urlShort);
}
