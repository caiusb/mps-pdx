package blender.solutions;

import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.stream.IntStream;

import blender.Blender;

public class BlenderParallel extends Blender {

	public BlenderParallel(BufferedImage img1, BufferedImage img2,
			int[] imageBuffer, MemoryImageSource imageSource) {
		super(img1, img2, imageBuffer, imageSource);
	}

	@Override
	public void process() {
		IntStream.range(0, height).parallel().forEach(row -> combineRows(row));
	}

	private void combineRows(int row) {
		int[] rgbim1 = new int[width];
		int[] rgbim2 = new int[width];

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