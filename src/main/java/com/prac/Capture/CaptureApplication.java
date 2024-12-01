package com.prac.Capture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
public class CaptureApplication {
	public static void main(String[] args) {
		SpringApplication.run(CaptureApplication.class, args);
	}
}

@RestController
class CaptureDataController {

	private static final String ORG_URL = "https://cpdldxutilitydev.carelonrx.com/dxuapi/api/deImplManualOverride/getDashboardDetails/268a"; // Organization endpoint

	// Proxy endpoint to fetch data from the organization's server
	@GetMapping("/proxy")
	public String fetchOrgData(@RequestHeader Map<String, String> headers) {
		try {
			// Use RestTemplate to fetch data from the organization server
			RestTemplate restTemplate = new RestTemplate();

			// Add headers (optional, if needed by the org server)
			org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
			headers.forEach(httpHeaders::add);

			org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(httpHeaders);

			// Send GET request to the organization server
			org.springframework.http.ResponseEntity<String> response =
					restTemplate.exchange(ORG_URL, org.springframework.http.HttpMethod.GET, entity, String.class);

			String responseData = response.getBody();

			return "Data fetched successfully!";
		} catch (Exception e) {
			return "Failed to fetch or capture data: " + e.getMessage();
		}
	}
}