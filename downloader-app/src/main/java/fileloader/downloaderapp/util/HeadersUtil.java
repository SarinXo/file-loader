package fileloader.downloaderapp.util;

import lombok.val;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

public class HeadersUtil {

    public static HttpHeaders forDownload(String fileName, int contentLength){
        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        val contentDisposition = ContentDisposition.builder("attachment")
                .filename(fileName, StandardCharsets.UTF_8)
                .build();
        headers.setContentDisposition(contentDisposition);
        headers.setContentLength(contentLength);
        return headers;
    }
}
