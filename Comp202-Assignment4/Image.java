public class Image {
	
	// Properties
	private String metadata;
	private int maxRange;
	private Pixel[][] data;
	
	// Constructor
	public Image(String metadata, int maxRange, Pixel[][] image) {
		if (maxRange < 0) {
			throw new IllegalArgumentException("Negative range");
		}
		this.metadata = metadata;
		this.maxRange = maxRange;
		this.data = image;
	}
	
	// Getter method to retrieve metadata
	public String getMetadata() {
		return this.metadata;
	}
	
	// Getter method to retrieve max range
	public int getMaxRange() {
		return this.maxRange;
	}
	
	// Getter method to retrieve width
	public int getWidth() {
		return this.data[0].length;
	}
	
	// Getter method to retrieve height
	public int getHeight() {
		return this.data.length;
	}
	
	// Getter method to retrieve Pixel object
	public Pixel getPixel(int i,int j) {
		return this.data[i][j];
	}
	
	// Flip method
	public void flip(boolean horizontal) {
		
		// Create new Pixel array to holded flipped array
		Pixel[][] flippedData = new Pixel[getHeight()][getWidth()];
		
		// Flip image along y-axis
		if (horizontal) {
			
			for (int i = 0; i < getHeight(); i++) {
				for (int j = 0; j < getWidth(); j++) {
					flippedData[i][j] = this.data[i][getWidth()-1-j];	
				}
			}	
		
			// Flip image along x-axis
		} else {
			
			for (int i = 0 ; i < getHeight(); i++) {
				for (int j = 0; j < getWidth(); j++) {
					flippedData[i][j] = this.data[getHeight()-1-i][j];
				}
			}
		}
		
		this.data = flippedData;
	}
	
	// Method that turns image into a gray scale image
	public void toGrey() {
		Pixel[][] greyData = new Pixel[this.data.length][this.data[0].length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				greyData[i][j] = new Pixel(this.getPixel(i, j).grey());
			}
		}
		this.data = greyData;
	}
	
	// Method that crops an image 
	// @params: startX (inclusive), endX (exclusive), startY (inclusive), endY (exclusive)
	public void crop(int startX, int startY, int endX, int endY) {
		// Invalid coordinates
		if (startY > data.length || startX > data[0].length || endY > data.length || endY < startY || endX > data[0].length || endX < startX || startY < 0 || startX < 0 || endY < 0 || endX < 0 ) {
			throw new IllegalArgumentException("Invalid Coordinates");
		}
		// New Pixel array to hold cropped pixels
		Pixel croppedData[][] = new Pixel[endY - startY][endX - startX];
		for (int i = 0; i < endY - startY; i++) {
			for (int j = 0; j < endX - startX; j++) {
				// Populate crop array from coordinates
				croppedData[i][j] = this.data[startY + i][startX + j];
			}
		}
		this.data = croppedData;
		
	}
	
	
	

}
