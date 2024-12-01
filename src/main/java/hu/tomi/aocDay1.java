package hu.tomi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// ADVENT OF CODE 2024 DAY 1
// A megoldáshoz szándékosan kerültem külső könyvtárak, és beépített függvények használatát,
// ezek helyett ahol lehet saját metódus megvalósításokat használtam gyakorlási célból
public class aocDay1 {

	// megoldás
	public static void main(String[] args) {
		final List<List<Long>> inputData = readFile("day1.csv");
		final List<List<Long>> sortedData = List.of(sortList(inputData.get(0)), sortList(inputData.get(1)));
		final long distance = calculateDistance(sortedData.get(0), sortedData.get(1));
		final long similarity = calculateSimilarity(sortedData.get(0), sortedData.get(1));
		System.out.println("Távolság: " + distance);
		System.out.println("Hasonlóság: " + similarity);
	}

	// fájlbeolvasás
	private static List<List<Long>> readFile(final String fileName) {
		try (InputStream inputStream = aocDay1.class.getClassLoader().getResourceAsStream(fileName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			List<Long> line1 = new ArrayList<>();
			List<Long> line2 = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				final String[] pair = line.split(",");
				line1.add(Long.parseLong(pair[0]));
				line2.add(Long.parseLong(pair[1]));
			}
			return List.of(line1, line2);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// minimum kiválasztásos rendezés
	private static List<Long> sortList(final List<Long> values) {
		final int n = values.size();
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (values.get(j) < values.get(minIndex)) {
					minIndex = j;
				}
			}

			final long temp = values.get(minIndex);
			values.set(minIndex, values.get(i));
			values.set(i, temp);
		}
		return values;
	}

	// a megoldás távolság kiszámítása
	private static long calculateDistance(final List<Long> a, final List<Long> b) {
		long distance = 0L;
		for (int i = 0; i < a.size(); i++) {
			distance += diff(a.get(i), b.get(i));
		}
		return distance;
	}

	private static long diff(final long a, final long b) {
		return (a - b) >= 0 ? a - b : b - a;
	}

	public static long calculateSimilarity(final List<Long> a, final List<Long> b) {
		long similarity = 0;
		for (long elementInA : a) {
			long numOfFound = 0;
			for (long elementInB : b) {
				if (Objects.equals(elementInA, elementInB)) {
					numOfFound++;
				}
			}
			similarity += elementInA * numOfFound;
		}
		return similarity;
	}

}