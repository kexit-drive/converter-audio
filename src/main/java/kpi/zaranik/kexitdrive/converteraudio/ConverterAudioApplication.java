package kpi.zaranik.kexitdrive.converteraudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConverterAudioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConverterAudioApplication.class, args);
	}

}
