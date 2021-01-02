package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.util.Duration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author AhmedG
 */
public class Video {

    private JFrame frmPlayerJava;
    private MediaPlayer player;
    private JFXPanel videoPanel;
    private JLabel title;
    private static Video video = null;
    String winPath = "src\\icons\\win.mp4";
    String losePath = "src\\icons\\lose.mp4";

    private Video() {
        initialize();
    }

    public static Video getInstance() {
        if (video == null) {
            video = new Video();
        }
        return video;
    }

    public void start(String userName, boolean win) {

        if (win) {
            getVideo(winPath);
            title.setText(userName + " Won");
        } else {
            getVideo(losePath);
            title.setText(userName + " Lost");
        }
        frmPlayerJava.setVisible(true);
        player.seek(Duration.ZERO);
        player.play();
    }

    private void initialize() {

        frmPlayerJava = new JFrame();
        frmPlayerJava.setTitle("Player Java");
        frmPlayerJava.setBounds(100, 100, 1260, 782);

        frmPlayerJava.getContentPane().setLayout(null);
        videoPanel = new JFXPanel();
        videoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        videoPanel.setBounds(100, 100, 1000, 500);
        frmPlayerJava.getContentPane().add(videoPanel);

        title = new JLabel("Player");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        title.setBounds(502, 11, 289, 42);
        frmPlayerJava.getContentPane().add(title);

        JButton btnExit = new JButton("Exit Game");
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 23));
        btnExit.setBounds(870, 637, 228, 79);
        frmPlayerJava.add(btnExit);

        JButton btnNewGame = new JButton("New Game");
        btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 23));
        btnNewGame.setBounds(200, 637, 228, 79);
        frmPlayerJava.add(btnNewGame);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                frmPlayerJava.setVisible(false);
            }
        });
    }

    private void getVideo(String path) {
        File video_source = new File(path);
        Media m = new Media(video_source.toURI().toString());
        player = new MediaPlayer(m);
        MediaView viewer = new MediaView(player);
        player.setAutoPlay(true);

//        player.setOnEndOfMedia(new Runnable() {
//            @Override
//            public void run() {
//                player.seek(Duration.ZERO);
//                player.play();
//            }
//        });
        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        // center video position
        javafx.geometry.Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        viewer.setX((screen.getWidth() - videoPanel.getWidth()) / 2);
        viewer.setY((screen.getHeight() - videoPanel.getHeight()) / 2);

        // resize video based on screen size
//        DoubleProperty width = viewer.fitWidthProperty();
//        DoubleProperty height = viewer.fitHeightProperty();
////        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
////        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
        viewer.setPreserveRatio(true);

        // add video to stackpane
        root.getChildren().add(viewer);

        videoPanel.setScene(scene);
//        player.play();
        videoPanel.setLayout(new BorderLayout());
    }
}
