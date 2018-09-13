package com.example.LMSzuulGatwayProxy;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.LMSzuulGatwayProxy.filters.ErrorFilter;
import com.example.LMSzuulGatwayProxy.filters.PostFilter;
import com.example.LMSzuulGatwayProxy.filters.PreFilter;
import com.example.LMSzuulGatwayProxy.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class SpringBootZuulgatwayproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootZuulgatwayproxyApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
