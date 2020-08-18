package dibujo.cli.output;

import dibujo.core.Canvas;
import dibujo.core.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CanvasAsImage {

    private final List<String> SUPPORTED_FORMATS = List.of("jpg", "jpeg", "png", "bmp", "gif");

    public void draw(String filename, String extension, Canvas canvas) {

        if(SUPPORTED_FORMATS.stream().noneMatch(f -> f.equalsIgnoreCase(extension))) {
            throw new IllegalArgumentException("Unsupported image extension: " + extension);
        }

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        //3 bytes pra cada pixel (R, G, B) de 0 a 255
        byte[] imageByteArray = new byte[3 * width * height];

        int i = 0;
        for (Position position : canvas) {
            if (position.getColor() != null) {
                // tem cor
                imageByteArray[i] = (byte) 255;
                imageByteArray[i+1] = (byte) 0;
                imageByteArray[i+2] = (byte) 0;
            } else if (position.isFilled()) {
                // tem barreira
                imageByteArray[i] = (byte) 0;
                imageByteArray[i+1] = (byte) 0;
                imageByteArray[i+2] = (byte) 0;
            } else {
                // em branco
                imageByteArray[i] = (byte) 255;
                imageByteArray[i+1] = (byte) 255;
                imageByteArray[i+2] = (byte) 255;
            }
            i += 3;
        }

        createImage(filename, extension, width, height, imageByteArray);
    }

    private void createImage(String filename, String extension, int width, int height, byte[] imageByteArray) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            DataBuffer buffer = new DataBufferByte(imageByteArray, imageByteArray.length);
            WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, 3 * width, 3, new int[]{0, 1, 2}, null);
            ColorSpace colorSpace = ColorModel.getRGBdefault().getColorSpace();
            ColorModel cm = new ComponentColorModel(colorSpace, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
            BufferedImage image = new BufferedImage(cm, raster, true, null);
            ImageIO.write(image, extension, fos);
        } catch (IOException e) {
            throw new RuntimeException("Error dump to image: " + filename, e);
        }
    }
}
