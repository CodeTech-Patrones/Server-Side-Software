package com.appnutrix;

import com.appnutrix.Logger.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppnutrixApplication {
    Logger logger = Logger.getInstance();
    public static void main(String[] args) {
        SpringApplication.run(AppnutrixApplication.class, args);
    }
}
