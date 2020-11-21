package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.DbSetting;

@RestController
@RefreshScope
public class Greeting {

	@Autowired
	private Environment env;

	@Autowired
	private DbSetting dbSetting;

	@Value("${my.greeting: default value}")
	private String greetingMsg; // If above key not found the 'default value' will be taken

	@Value("static message")
	private String staticMsg; // assigning value to the string variable

	@Value("${list.values}")
	private List listValue; // assigning list values

	@Value("#{${db.values}}")
	private Map<String, String> dbValues;

	@GetMapping("/greeting")
	public String greeting() {
		return greetingMsg + staticMsg + listValue + dbValues + dbSetting.getConnection() + dbSetting.getHost()
				+ dbSetting.getPort();
	}

	@GetMapping("/envDeatils")
	public String getEnvDetails() {
		return env.toString();
	}

}
