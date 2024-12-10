package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// ADVENT OF CODE 2024 DAY 2
// A megoldáshoz szándékosan kerültem külső könyvtárak, és beépített függvények használatát,
// ezek helyett ahol lehet saját metódus megvalósításokat használtam gyakorlási célból

public class Main {
	public static void main(String[] args) {
		List<List<Long>> input = readFile("input.csv");

		long safeCount = 0L;
		int i = 0;
		for (List<Long> row : input) {
			if (isIncreasing(row) || isDecreasing(row)) {
				safeCount += 1;
			}
			i++;
		}
		System.out.println("A biztonságos szintek száma: " + safeCount);

		long almostSafeCount = 0L;
		for (List<Long> row : input) {
			if (isAlmostIncreasing(row) || isAlmostDecreasing(row)) {
				almostSafeCount += 1;
			}
		}
		System.out.println("A majdnem biztonságos szintek száma: " + almostSafeCount);

	}

	// fájlbeolvasás
	private static List<List<Long>> readFile(final String fileName) {
		try (InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			List<List<Long>> data = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				List<Long> row = new ArrayList<>();
				final String[] elements = line.split(",");
				for (final String element : elements) {
					row.add(Long.parseLong(element));
				}
				data.add(row);
			}
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean isIncreasing(final List<Long> data) {
		boolean increasing = true;
		for (int i = 1; i < data.size(); i++) {
			final Long current = data.get(i);
			final Long last = data.get(i - 1);
			if (current > last + 3 || current < last + 1) {
				increasing = false;
				break;
			}
		}
		return increasing;
	}

	private static boolean isDecreasing(final List<Long> data) {
		boolean decreasing = true;
		for (int i = 1; i < data.size(); i++) {
			final Long current = data.get(i);
			final Long last = data.get(i - 1);
			if (current + 3 < last || current + 1 > last) {
				decreasing = false;
				break;
			}
		}
		return decreasing;
	}

	private static boolean isAlmostIncreasing(final List<Long> data) {
		boolean increasing = false;
		for (int i = 0; i < data.size(); i++) {
			final List<Long> dataSmaller = new ArrayList<>(data);
			dataSmaller.remove(i);
			if (isIncreasing(dataSmaller)) {
				return true;
			};
		}
		return increasing;
	}

	private static boolean isAlmostDecreasing(final List<Long> data) {
		boolean decreasing = false;
		for (int i = 0; i < data.size(); i++) {
			final List<Long> dataSmaller = new ArrayList<>(data);
			dataSmaller.remove(i);
			if (isDecreasing(dataSmaller)) {
				return true;
			};
		}
		return decreasing;
	}

}