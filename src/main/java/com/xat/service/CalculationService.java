package com.xat.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import scala.Tuple2;

@Service
public class CalculationService {
	private static final Pattern SPACE = Pattern.compile(" ");
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${message:unknown}")
    private String message;
	
	public void displayMessage() {
		System.out.println(message);
	}
	
	public void countWord() {
		String logFile = "file:///D:/projects/spring-boot-console-app/big.txt"; // Should be some file on your system
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        JavaRDD<String> lines = spark.read().textFile(logFile).javaRDD();
        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());
        JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

        List<Tuple2<String, Integer>> output = counts.collect();
        logger.info("spark executing...................." + output.size());
        for (Tuple2<?,?> tuple : output) {
          logger.info(tuple._1() + ": " + tuple._2());
        }
        spark.stop();
	}
}
