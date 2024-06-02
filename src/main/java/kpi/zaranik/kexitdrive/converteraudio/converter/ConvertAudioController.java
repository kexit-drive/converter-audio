package kpi.zaranik.kexitdrive.converteraudio.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
public class ConvertAudioController {

    @Autowired
    WebClient webClient;

    @PostMapping("convert")
    public ResponseEntity<Resource> getFileConverted(@RequestPart MultipartFile file) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        builder
            .part("file", file.getResource());

        MultiValueMap<String, HttpEntity<?>> multipartBody = builder.build();

        Resource converted = webClient.post()
            .uri("mp3")
            .bodyValue(multipartBody)
            .retrieve()
            .bodyToMono(Resource.class)
            .block();

        ContentDisposition contentDisposition = ContentDisposition.inline().build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("audio/mpeg"))
            .headers(headers)
            .body(converted);
    }

}
