package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		final List<String> input = readFile("input.txt");

		String patternString = "mul\\(\\d+,\\d+\\)";;
		Pattern pattern = Pattern.compile(patternString);

		long result = 0;
		final StringBuilder file = new StringBuilder();
		for (String line : input) {
			file.append(line);
		}
		Matcher matcher = pattern.matcher(file);
		while (matcher.find()) {
			String[] numbers = matcher.group().split(",");
			String num1 = numbers[0].replaceAll("[^0-9]", "");
			String num2 = numbers[1].replaceAll("[^0-9]", "");
			result = result + Long.parseLong(num1) * Long.parseLong(num2);
		}
		System.out.println(result);



		long result2 = 0;
		boolean enabled = true;
		Set<String> foundStrings = new HashSet<>();
		for (int i = 0; i < file.length() - 11; i++) {
			final String curremt = file.substring(i, i + 11);
			if (curremt.contains("don't()")) {
				enabled = false;
			} else if (curremt.contains("do()")) {
				enabled = true;
			}
			matcher = pattern.matcher(file.substring(i, i + 11));
			if (matcher.find() && enabled) {
				foundStrings.add(matcher.group());
				String[] numbers = matcher.group().split(",");
				String num1 = numbers[0].replaceAll("[^0-9]", "");
				String num2 = numbers[1].replaceAll("[^0-9]", "");
				result2 = result2 + Long.parseLong(num1) * Long.parseLong(num2);
			}
		}
		System.out.println(result2);
	}

	private static List<String> readFile(final String fileName) {
		try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			List<String> input = new ArrayList<>();
			while ((line = reader.readLine()) != null) {
				input.add(line);
			}
			return input;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}