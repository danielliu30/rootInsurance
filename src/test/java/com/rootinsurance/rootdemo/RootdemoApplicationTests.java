package com.rootinsurance.rootdemo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import com.rootinsurance.rootdemo.ServiceUtility.ParseFile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RootdemoApplicationTests {

	@Test
	void readNonExistingFile() {
		assertThrows(FileNotFoundException.class, ()-> ParseFile.readFile("src\\main\\resources\\FakeFile.txt"));	
	}

	@Test
	void readBrokenFileFormat_missingData() {
		assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ParseFile.readFile("src\\main\\resources\\badFormat.txt"));
	}

	@Test
	void readBrokenFileFormat_missingTestOrDrive() {
		assertThrows(Exception.class, ()-> ParseFile.readFile("src\\main\\resources\\noDriverorTrip.txt"));
	}
	
}
