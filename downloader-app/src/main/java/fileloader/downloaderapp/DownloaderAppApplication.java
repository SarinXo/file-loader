package fileloader.downloaderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DownloaderAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(DownloaderAppApplication.class, args);
    }

}
