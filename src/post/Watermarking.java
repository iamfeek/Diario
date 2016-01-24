package post;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Jy on 18-Jan-16.
 */
public class Watermarking {
    public static void watermarkEntire(BufferedImage original, String watermarkText, int fontSize, float opacity) {
        Graphics2D g2d = original.createGraphics();
        g2d.scale(1, 1);
        g2d.addRenderingHints(
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON));
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, fontSize);
        GlyphVector fontGV = font.createGlyphVector(g2d.getFontRenderContext(), watermarkText);
        Rectangle size = fontGV.getPixelBounds(g2d.getFontRenderContext(), 0, 0);
        Shape textShape = fontGV.getOutline();
        double textWidth = size.getWidth();
        double textHeight = size.getHeight();
        AffineTransform rotate45 = AffineTransform.getRotateInstance(Math.PI / 4d);
        Shape rotatedText = rotate45.createTransformedShape(textShape);

        // use a gradient that repeats 4 times
        g2d.setPaint(new GradientPaint(0, 0,
                new Color(0f, 0f, 0f, 0.1f),
                original.getWidth() / 2, original.getHeight() / 2,
                new Color(1f, 1f, 1f, opacity)));
        g2d.setStroke(new BasicStroke(0.5f));

        // step in y direction is calc'ed using pythagoras + 5 pixel padding
        double yStep = Math.sqrt(textWidth * textWidth / 2) + 5;

        // step over image rendering watermark text
        for (double x = -textHeight * 3; x < original.getWidth(); x += (textHeight * 3)) {
            double y = -yStep;
            for (; y < original.getHeight(); y += yStep) {
                g2d.draw(rotatedText);
                g2d.fill(rotatedText);
                g2d.translate(0, yStep);
            }
            g2d.translate(textHeight * 3, -(y + yStep));
        }
    }

    public static byte[] encodeJPEG(BufferedImage image, int quality) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream((int) ((float) image.getWidth() * image.getHeight() / 4));
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
        quality = Math.max(0, Math.min(quality, 100));
        param.setQuality((float) quality / 100.0f, false);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(image);
        byte[] result = baos.toByteArray();
        baos.close();
        return result;
    }
}
