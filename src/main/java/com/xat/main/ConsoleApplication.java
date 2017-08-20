package com.xat.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.xat.service.CalculationService;

@SpringBootApplication
@ComponentScan({"com.xat.service"})
public class ConsoleApplication implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CalculationService calculationService;
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//    	calculationService.displayMessage();
    	
    	calculationService.countWord();
//        Dataset<String> logData = spark.read().textFile().javaRDD().readTextFile(logFile).cache();
//
//        long numAs = logData.filter(s -> s.contains("a")).count();
//        long numBs = logData.filter(s -> s.contains("b")).count();

//        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);


    }
}
