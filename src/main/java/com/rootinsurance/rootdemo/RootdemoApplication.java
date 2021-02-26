package com.rootinsurance.rootdemo;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import com.rootinsurance.rootdemo.ServiceUtility.ParseFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RootdemoApplication {
	public static void main(String[] args) throws ParseException, IOException {
		SpringApplication.run(RootdemoApplication.class, args);
		Scanner input = new Scanner(System.in);
        System.out.println("Enter a fileName: ");
        String fileName = input.nextLine();
		ParseFile.readFile(fileName);
		ParseFile.printAverageTrips();
		input.close();
	}

}
