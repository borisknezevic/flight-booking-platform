package com.boris.flightticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableMongoRepositories
public class FlightTicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightTicketBookingApplication.class, args);

	}
}
