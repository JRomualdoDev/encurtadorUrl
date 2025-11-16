package com.encurtadorurl.encurtadorurl.repository;

import com.encurtadorurl.encurtadorurl.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByurlOriginal(String urlFull);
}
