import java.io.*;
import java.util.Scanner;

public class ImageFileUtilities {
	
	public static Image read (String filename) throws IOException{

			// Scanner to reader input file
			Scanner reader = new Scanner(new File(filename));
			// Format: P2 or P3
			String codeFormat = reader.nextLine();
			// Comments
			String metadata = "";
			
			// Collect every line that starts with "#"
			if (reader.hasNext("#")) {
				metadata += metadata + reader.nextLine();
			}
			
			// Columns or Rows
			int cols = reader.nextInt();
			int rows = reader.nextInt();
			
			int maxRange = reader.nextInt();
			Pixel[][] pixelArray = new Pixel[rows][cols];
			
			// If file type is P3
			// Retrieve all 3 color values and create a pixel
			if (codeFormat.equals("P3")) {
				// Pixel colors
				int red = 0;
				int green = 0;
				int blue = 0;
				
				// Get each pixel from file
				for (int i = 0; i < rows; i++) {
					for(int j = 0; j < cols; j++) {
						// Each pixel color
						red = reader.nextInt();
						green = reader.nextInt();
						blue = reader.nextInt();
						// Create new pixels with values just read from file
						pixelArray[i][j] = new Pixel(red,green,blue);
						
	
					}
				}
					
			}
			
			// If file type is P2
			// Retrieve intensity
			if (codeFormat.equals("P2")) {
				
				int[][] newPixels = new int[rows][cols];
				
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						// Create a pixel based on the intensity
						newPixels[i][j] = reader.nextInt();
						pixelArray[i][j] = new Pixel(newPixels[i][j]);
						
					}
				}
				
			}
			
			reader.close();
			
			// Returning a newly created image
			return new Image(metadata, maxRange, pixelArray);
		
	}
	
	
	// Method that Creates a PNM file from an image object
	public static void writePnm (Image img, String filename) throws IOException {
		
		// Instantiate FileWriter and BufferedWriter classes  
		FileWriter fWriter = new FileWriter(filename);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		
		// Write to file
		bWriter.write("P3");
		bWriter.newLine();
		bWriter.write(img.getMetadata());
		bWriter.newLine();
		bWriter.write(img.getWidth() + " " + img.getHeight());
		bWriter.newLine();
		bWriter.write(img.getMaxRange() + " ");
		bWriter.newLine();
		
		// Write each pixel's red, green and blue values to the file
		for (int i  = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				bWriter.write(img.getPixel(i, j).getRed() + " " ); 
				bWriter.write(img.getPixel(i, j).getGreen() + " " ); 
				bWriter.write(img.getPixel(i, j).getBlue() + " " );
			}
			bWriter.write("\n");
		}
		bWriter.close();
		fWriter.close();
	}
	
	// Method that Creates a PGM file from an image object
	public static void writePgm (Image img, String filename) throws IOException {
		
		// Instantiate FileWriter and BufferedWriter classes
		FileWriter fWriter = new FileWriter(filename);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		
		// Write to file
		bWriter.write("P2");
		bWriter.newLine();
		bWriter.write(img.getMetadata());
		bWriter.newLine();
		bWriter.write(img.getWidth() + " " + img.getHeight());
		bWriter.newLine();
		bWriter.write(img.getMaxRange() + " ");
		bWriter.newLine();
		
		// Write each Pixels grey
		for (int i  = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				bWriter.write(img.getPixel(i, j).getRed() + " ");
			}
		}
		
		bWriter.close();
		fWriter.close();
		
	}
	
	
	
}
