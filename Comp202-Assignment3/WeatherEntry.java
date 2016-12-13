public class WeatherEntry {

	// Private properties
	private double temperatureInCelsius;
	private boolean isSunny;

	// Constructor
	public WeatherEntry (double temperatureInCelsius, boolean isSunny) {
		this.temperatureInCelsius = temperatureInCelsius;
		this.isSunny = isSunny;
	}

	// Getter method for temperature in celsius
	public double getTemperatureCelsius () {
		return this.temperatureInCelsius;
	}

	// This method determines if its a good day based on temperature and isSunny properties
	public boolean isGoodWeather () {
		if (this.temperatureInCelsius > -30 && this.isSunny) {
			return true;
		}
		return false;

	}

	// This method prints the temperature in celsius or fahrenheit, if its sunny and if its a good day
	public void display (boolean isCelsius) {

		double tempInFahrenheit = this.temperatureInCelsius * (9.0/5.0) + 32.0;

		if (isCelsius) {
			if (isGoodWeather()) {
				System.out.println("It is " + this.temperatureInCelsius + " degrees Celsius and is sunny. It is a good day.");
			} else {
				System.out.println("It is " + this.temperatureInCelsius + " degrees Celsius and is sunny. It is not a good day.");
			}
		}

		if (!isCelsius) {
			if (isGoodWeather()) {
				System.out.println("It is " + tempInFahrenheit + " degrees Fahrenheit and is sunny. It is a good day.");
			} else {
				System.out.println("It is " + tempInFahrenheit + " degrees Fahrenheit and is sunny. It is not a good day.");
			}
		}
	}
	

	
}