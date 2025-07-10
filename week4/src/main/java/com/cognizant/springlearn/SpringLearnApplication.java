package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START main()");
        SpringApplication.run(SpringLearnApplication.class, args);
        displayDate();
        displayCountry();
        displayCountries();
        LOGGER.info("END main()");
    }

    public static void displayDate() {
        LOGGER.info("START displayDate()");
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        String dateStr = "31/12/2018";
        try {
            Date date = format.parse(dateStr);
            LOGGER.debug("Parsed date: {}", date);
        } catch (ParseException e) {
            LOGGER.error("Error parsing date", e);
        }
        LOGGER.info("END displayDate()");
    }

    public static void displayCountry() {
        LOGGER.info("START displayCountry()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Country : {}", country.toString());
        // Singleton/prototype demonstration
        Country anotherCountry = context.getBean("country", Country.class);
        LOGGER.debug("Another Country : {}", anotherCountry.toString());
        LOGGER.info("END displayCountry()");
    }

    public static void displayCountries() {
        LOGGER.info("START displayCountries()");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        @SuppressWarnings("unchecked")
        List<Country> countryList = (List<Country>) context.getBean("countryList");
        for (Country c : countryList) {
            LOGGER.debug("Country from list: {}", c);
        }
        LOGGER.info("END displayCountries()");
    }
} 