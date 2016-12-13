public class Pixel {

	// Private Properties
	private int red;
	private int green;
	private int blue;
	
	// Constructor
	// @param: red, green, blue colors [0-255] inclusively
	public Pixel (int red, int green, int blue) {
		if ((red > 255 || red < 0) || (green > 255 || green < 0) || (blue > 255 || blue < 0)) {
			throw new IllegalArgumentException("Color values are outside of the expected range [0-255]");
		}
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	// Constructor
	// @param: intensity same for each color
	public Pixel (int intensity) {
		if ((intensity > 255 || intensity < 0)) {
			throw new IllegalArgumentException("Intensities are outside of the expected range [0-255]");
		}
		this.red = intensity;
		this.green = intensity;
		this.blue = intensity;
	}
	
	// Getter method to retrieve red color
	public int getRed() {
		return this.red;
	}
	
	// Getter method to retrieve green color
	public int getGreen() {
		return this.green;
	}
	// Getter method to retrieve blue color
	public int getBlue() {
		return this.blue;
	}
	
	// Method that turns Pixel to grey
	public int grey() {
		double grey =  ((this.red) + (this.green) + (this.blue))/3;
		return (int) grey;
	}
	
	
	
}
