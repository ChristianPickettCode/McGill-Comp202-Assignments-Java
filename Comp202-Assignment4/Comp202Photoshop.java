import java.io.IOException;

public class Comp202Photoshop {
	
	public static void main (String [] args) {
		
		// Minimum number of arguments
		if (args.length < 4) {
			System.out.println("Invalid input type \nValid Example. run Comp202Photoshop cat.pnm cat-changed.pgm pgm -gs");
			throw new IllegalArgumentException("Invalid input type");
		}
		
		// Input variables
		String  inputFile = args[0];
		String outputFile = args[1];
		String outputFormat = args[2];
		String operation = args[3];
		
		try {
			
			// Verify file type
			if (!outputFormat.equals("pgm") && !outputFormat.equals("pnm")) {
				System.out.println("Invalid input type \nValid Example. run Comp202Photoshop cat.pnm cat-changed.pgm pgm -gs");
				throw new IllegalArgumentException("Invalid input type");
			}
			
			// Horizontal flip
			if (operation.equals("-fh")){
				// Reference Image File Utilities 
				Image img = ImageFileUtilities.read(inputFile);
				// Flip Image horizontally
				img.flip(true);
				// PNM file
				if (outputFormat.equals("pnm")) {
					ImageFileUtilities.writePnm(img, outputFile);
				}
				// PGM file
				else {
					ImageFileUtilities.writePgm(img, outputFile);
				}
			}
			
			// Vertical flip
			if (operation.equals("-fv")){
				Image img = ImageFileUtilities.read(inputFile);
				// Flip Image vertically
				img.flip(false);
				if (outputFormat.equals("pnm")) {
					ImageFileUtilities.writePnm(img, outputFile);
				}
				else if (outputFormat.equals("pgm")) {
					ImageFileUtilities.writePgm(img, outputFile);
				}
			}
			
			// Grey scale
			if (operation.equals("-gs")){
				Image img = ImageFileUtilities.read(inputFile);
				// Make grey scale
				img.toGrey();
				if (outputFormat.equals("pnm")) {
					ImageFileUtilities.writePnm(img, outputFile);
				}
				else if (outputFormat.equals("pgm")) {
					ImageFileUtilities.writePgm(img, outputFile);
				}
			}
			
			// Crop Image
			if (operation.equals("-cr")) {
				
				// Properties
				int startX = Integer.parseInt(args[4]);
				int startY = Integer.parseInt(args[5]);
				int endX = Integer.parseInt(args[6]);
				int endY = Integer.parseInt(args[7]);

				// Minimum length for arguments
				if (args.length < 8) {
					System.out.println("Invalid input type \nValid Example. run Comp202Photoshop cat.pnm cat-cropped.pnm pnm -cr 10 15 30 40");
					throw new IllegalArgumentException("Invalid input type");
				}
				Image img = ImageFileUtilities.read(inputFile);
				// Crop image
				img.crop(startX, startY, endX, endY);
				
				if (outputFormat.equals("pnm")) {
					ImageFileUtilities.writePnm(img, outputFile);
				}
				else if (outputFormat.equals("pgm")) {
					ImageFileUtilities.writePgm(img, outputFile);
				}
			}
			
			// Make a copy
			if (operation.equals("-id")) {
				Image img = ImageFileUtilities.read(inputFile);
				if (outputFormat.equals("pnm")) {
					ImageFileUtilities.writePnm(img, outputFile);
				}
				else if (outputFormat.equals("pgm")) {
					ImageFileUtilities.writePgm(img, outputFile);
				}
			}

		} catch (IOException e) {
			System.out.println("Can not locate input file");
			e.printStackTrace();
		}
	}
}