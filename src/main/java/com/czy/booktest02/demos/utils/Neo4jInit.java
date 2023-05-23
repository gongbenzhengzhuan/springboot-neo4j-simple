package com.czy.booktest02.demos.utils;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.exceptions.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
class Neo4jInit {

	@Value("bolt://123.60.190.167")
	private String uri;

	@Value("admin")
	private String username;

	@Value("admin")
	private String password;

//	@Autowired
//	private Driver nDriver;

	@Bean
	@Scope(value = "singleton")
	public Driver getDriver() {

		Driver driver;

		try {

			driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
			return driver;

		} catch (AuthenticationException e) {

		}

		return null;
	}

}