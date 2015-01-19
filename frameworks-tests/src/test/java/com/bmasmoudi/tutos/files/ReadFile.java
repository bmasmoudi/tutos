package com.bmasmoudi.tutos.files;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class ReadFile {

	@Test
	public void use_java_nio_to_read_lines() throws Exception {
		Path path = Paths.get("D:/test.txt");

		// Attention bien précise l'encoding
		List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

		System.out.println(lines.get(0));
	}

}
