package logico;
import javax.swing.*;
import java.awt.*;

public class FullscreenJFrameSizeRatio {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowFullscreenJFrame());
    }

    private static void createAndShowFullscreenJFrame() {
        JFrame frame = new JFrame("Fullscreen JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the JFrame to fullscreen
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (graphicsDevice.isFullScreenSupported()) {
            graphicsDevice.setFullScreenWindow(frame);
        }

        // Get the size of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Get the size of the JFrame
        Dimension frameSize = frame.getSize();
        int frameWidth = frameSize.width;
        int frameHeight = frameSize.height;
        System.out.println(frameHeight);
        System.out.println(frameWidth);

        // Calculate the ratio of JFrame size to screen size
        double widthRatio = (double) frameWidth / screenWidth;
        double heightRatio = (double) frameHeight / screenHeight;

        // Output the ratio
        System.out.println("JFrame width-to-screen ratio: " + widthRatio);
        System.out.println("JFrame height-to-screen ratio: " + heightRatio);

        // Display the JFrame
        frame.setVisible(true);
    }
}
