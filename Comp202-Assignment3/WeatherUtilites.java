import java.util.Scanner;

public class WeatherUtilites {

	public static void main(String[] args) {

		// Input from comamand line
		int n = Integer.parseInt(args[0]);

		// An array of Weather Entry objects
		WeatherEntry[] weatherEntries = new WeatherEntry[n];

		// Initialization of scanner to take input from user
		Scanner input = new Scanner(System.in);

		// Populate the Weather Entry array with properties from the user
		for (int i = 0; i < weatherEntries.length; i++) {

			double temperatureInCelsius = 0;
			boolean isSunny;

			System.out.print("What was the temperature in Celsius for day " + (i + 1) + " : ");
			temperatureInCelsius = input.nextDouble();

			System.out.print("Was it is sunny that day (true/false): ");
			isSunny = input.nextBoolean();

			weatherEntries[i] = new WeatherEntry(temperatureInCelsius, isSunny);

		}

		System.out.println("There were " + displayNumberOfGoodDays(weatherEntries) + " nice days");
		System.out.println("The highest temperature was " + gethighestTemperature(weatherEntries) + " degrees Celsius and the lowest was " + getlowestTemperature(weatherEntries) + ".");

	}


	// Private getter method to display the number of good days
	private static int displayNumberOfGoodDays (WeatherEntry[] weatherEntries) {
		return countGoodDays(weatherEntries);
	}

	// Private getter method to determine and return the highest temperature
	private static double gethighestTemperature (WeatherEntry[] weatherEntries) {
		double max = weatherEntries[0].getTemperatureCelsius();

		for (int i = 0; i < weatherEntries.length; i++) {
			if (max < weatherEntries[i].getTemperatureCelsius()) {
				max = weatherEntries[i].getTemperatureCelsius();
			}
		}

		return max;
	}

	// Private getter method to determine and return the lowest temperature
	private static double getlowestTemperature (WeatherEntry[] weatherEntries) {
		double min = weatherEntries[0].getTemperatureCelsius();

		for (int i = 0; i < weatherEntries.length; i++) {
			if (min > weatherEntries[i].getTemperatureCelsius()) {
				min = weatherEntries[i].getTemperatureCelsius();
			}
		}

		return min;
	}

	// This method returns the number of good days
	// Given an array of temperatures and sunniness status
	public static int countGoodDays (double[] temperatures, boolean[] sunninessStatus) {
		int totalGoodDays = 0;

		// Input Arrays not the same size
		if (temperatures.length != sunninessStatus.length) {
			throw new IllegalArgumentException ("The lengths of the input arrays are not the same!");
		}

		for (int i = 0; i < temperatures.length; i++) {
			if (temperatures[i] > -30 && sunninessStatus[i]) {
				totalGoodDays++;
			}
		}
		return totalGoodDays;
	}

	// This method returns the number of good days
	// Given an array of Weather Entry
	public static int countGoodDays (WeatherEntry[] weatherEntries) {
		int totalGoodDays = 0;

		for (int i = 0; i < weatherEntries.length; i++) {
			if (weatherEntries[i].isGoodWeather()) {
				totalGoodDays++;
			}
		}

		return totalGoodDays;

	}






}