package com.example.rental.service;

import org.springframework.web.multipart.MultipartFile;

public interface IOssService {
    String upload(MultipartFile file);
    Boolean delete(String Url);
}
