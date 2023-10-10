package test.screenshotcompare;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ScreenshotComparisonTest {

    @Test
    public void testImageComparison() {
        String img1 = "/home/kirill/Pictures/Screenshots/11.png";
        String img2 = "/home/kirill/Pictures/Screenshots/22.png";

        try {
            Method2(img1, img2);
            Method1(img1, img2);
            assertSameDimensions(img1, img2);
        } catch (ImageComparisonException e) {
            fail("Screenshot comparison FAILED: " + e.getMessage());
        } catch (IOException e) {
            fail("Error reading screenshots: " + e.getMessage());
        }
    }

    public static void Method1(String image1, String image2) throws IOException, ImageComparisonException {
        BufferedImage img1 = ImageIO.read(new File(image1));
        BufferedImage img2 = ImageIO.read(new File(image2));
        int w1 = img1.getWidth();
        int w2 = img2.getWidth();
        int h1 = img1.getHeight();
        int h2 = img2.getHeight();

        if ((w1 != w2) || (h1 != h2)) {
            throw new ImageComparisonException("Screenshots should have the same dimensions");
        }

        long diff = 0;
        for (int j = 0; j < h1; j++) {
            for (int i = 0; i < w1; i++) {
                int pixel1 = img1.getRGB(i, j);
                int pixel2 = img2.getRGB(i, j);

                if (pixel1 != pixel2) {
                    throw new ImageComparisonException("Screenshots are different by method 1.");
                }
            }
        }

        System.out.println("Screenshots are the same by method 1.");
    }

    public static void Method2(String image1, String image2) throws IOException, ImageComparisonException {
        BufferedImage expectedImage = ImageIO.read(new File(image1));
        BufferedImage actualImage = ImageIO.read(new File(image2));

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);

        if (diff.hasDiff()) {
            throw new ImageComparisonException("Screenshots are different by method 2.");
        }

        System.out.println("Screenshots are the same by method 2.");
    }

    public static void assertSameDimensions(String image1, String image2) throws IOException, ImageComparisonException {
        BufferedImage img1 = ImageIO.read(new File(image1));
        BufferedImage img2 = ImageIO.read(new File(image2));
        int w1 = img1.getWidth();
        int w2 = img2.getWidth();
        int h1 = img1.getHeight();
        int h2 = img2.getHeight();

        if ((w1 != w2) || (h1 != h2)) {
            throw new ImageComparisonException("Screenshots should have the same dimensions");
        }
    }
}

class ImageComparisonException extends Exception {
    public ImageComparisonException(String message) {
        super(message);
    }
}
