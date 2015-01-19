package com.bmasmoudi.tutos.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class ReadFile {

	@Test
	public void testReadFile() {
		try {
			String s = readFile("D:\\tmp\\json.txt", StandardCharsets.UTF_8);
			s.trim();
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static String readFile(final String path, final Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
