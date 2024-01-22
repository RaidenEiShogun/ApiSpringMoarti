package com.example.springboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
	private static final ObjectMapper mapper = new ObjectMapper();
	private Utils() {
		throw new IllegalStateException();
	}
	public static String serialize(Object object, String fileName) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	public static Object deserialize(String json, Class<?> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	public static String read(String filename) throws IOException {
		File file = new File(filename);
		if (file.exists()) {
			return Files.readString(file.toPath());
		} else {
			return "";
		}
	}
	public static void write(String filename, String content) throws IOException {
		Files.write(Path.of(content), content.getBytes());
	}
	public static Object readJson(String filename, Class<?> clazz) throws IOException {
		return mapper.readValue(new File(filename), clazz);
	}
	public static void delete(String filename) throws IOException {
		Files.delete(Path.of(filename));
	}
	public static void writeJson(String filename, Object object) throws IOException {
		mapper.writer().writeValue(new File(filename), object);
	}
}
