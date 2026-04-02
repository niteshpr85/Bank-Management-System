package bank.management.system;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class ImageUtil {

    public static ImageIcon loadIcon(String resourcePath, int width, int height) {
        URL url = ClassLoader.getSystemResource(resourcePath);
        if (url == null) {
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        if (width > 0 && height > 0) {
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            return new ImageIcon(image);
        }
        return icon;
    }
}
