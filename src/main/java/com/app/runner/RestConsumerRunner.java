package com.app.runner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.app.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class RestConsumerRunner implements CommandLineRunner{
	

	@Override
	public void run(String... args) throws Exception {
		String url="http://localhost:8081/getAll";
		RestTemplate template=new RestTemplate();
		ResponseEntity<String> response=template.getForEntity(url, String.class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getStatusCode().name());
		if(response.getStatusCodeValue()==200){
			boolean b=0.7==0.1*7;
			// On Success
			ObjectMapper om=new ObjectMapper();
			Student[] readValue = om.readValue(response.getBody(), Student[].class);
			System.out.println(Arrays.asList(readValue));
			System.out.println("boolean b=0.7==0.1*7;"+b);
		}
		else if(response.getStatusCodeValue()==500)
		{
			System.out.println("unable to fetch data");
		}
		
		
	}

}
