package blender;

import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;

import util.UnimplementedExercise;

/**
 * Java 8 Streams implementation.
 */

/*
 * In this exercise the aim is to replace the outer "for" loop with java 8
 * Stream operations.
 * 
 * Steps:
 * 
 * 1. Move rgbim1 and rgbim2 at the beginning of the outer loop.
 * 
 * 2.Extract the body of the outer loop into a separate method called computeRow(int
 * row). You can use the extract method refactoring to automate this process.
 * 
 * 3. Replace the outer loop with an IntStream with integers from 0 to the
 * height of the images. The integers represent the image rows that were
 * previously iterated through by the outer loop.
 * 
 * 4. For each integer in the stream (row in the image), apply the previously
 * extracted .computeRow method. Use the Stream's .forEach method to achieve this.
 * 
 * 5.The code should now work correctly in a sequential manner. To parallelize
 * it, apply the method .parallelize to the int stream before calling .forEach.
 * 
 * 6. Remove the UnimplementedExercise annotation and run the application and
 * see how it performs. Again, it will be slightly slower than the fork-join and
 * thread pool versions.
 */

public class BlenderParallel extends Blender implements UnimplementedExercise {

	public BlenderParallel(BufferedImage img1, BufferedImage img2,
			int[] imageBuffer, MemoryImageSource imageSource) {
		super(img1, img2, imageBuffer, imageSource);
	}

	@Override
	public void process() {
		int[] rgbim1 = new int[width];
		int[] rgbim2 = new int[width];

		for (int row = 0; row < height; row++) {
			img1.getRGB(0, row, width, 1, rgbim1, 0, width);
			img2.getRGB(0, row, width, 1, rgbim2, 0, width);

			for (int col = 0; col < width; col++) {
				int rgb1 = rgbim1[col];
				int r1 = (rgb1 >> 16) & 255;
				int g1 = (rgb1 >> 8) & 255;
				int b1 = rgb1 & 255;

				int rgb2 = rgbim2[col];
				int r2 = (rgb2 >> 16) & 255;
				int g2 = (rgb2 >> 8) & 255;
				int b2 = rgb2 & 255;

				int r3 = (int) (r1 * weight + r2 * (1.0 - weight));
				int g3 = (int) (g1 * weight + g2 * (1.0 - weight));
				int b3 = (int) (b1 * weight + b2 * (1.0 - weight));

				imageBuffer[row * width + col] = new java.awt.Color(r3, g3, b3)
						.getRGB();
			}

			imageSource.newPixels(0, row, width, 1, true);
		}
	}
}
