package pl.lublin.wsei.java.cwiczenia.lab5;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Pong extends Application {

    private static final double HEIGHT = 600;
    private static final double WIDTH = 800;
    private static final double MARGIN = 50;
    private static final double ARENAWIDTH = WIDTH - 2*MARGIN;
    private static final double ARENAHEIGHT = HEIGHT - 2*MARGIN;
    private static final double ARENAX1 = MARGIN;
    private static final double ARENAY1 = MARGIN;
    private static final double ARENAX2 = ARENAX1+ ARENAWIDTH;
    private static final double ARENAY2 = ARENAY1 + ARENAHEIGHT;
    private static final double R = 10;


    private static final int LICZBAKULEK = 10;
    private double[] x = new double[LICZBAKULEK];
    private double[] y = new double[LICZBAKULEK];
    private double[] vx = new double[LICZBAKULEK];
    private double[] vy = new double[LICZBAKULEK];



    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        initKula();

        Timeline t = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        t.setCycleCount(Timeline.INDEFINITE);

        stage.setTitle("Kulki");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();

        t.play();

    }


    public static void main(String[] args) {
        launch(args);
    }

    private void initKula(){
        Random lott = new Random();
        for (int i = 0; i < LICZBAKULEK; i++){
            x[i] = lott.nextDouble() * ARENAWIDTH + ARENAX1;
            y[i] = lott.nextDouble() * ARENAHEIGHT + ARENAY1;
            vx[i] = 5+lott.nextDouble()*20;
            vy[i] = 5+lott.nextDouble()*20;
        }
    }

    private void run(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(ARENAX1, ARENAY1, ARENAWIDTH, ARENAHEIGHT);


        for (int i = 0; i < LICZBAKULEK; i++){
            if ((x[i] <= ARENAX1) || (x[i] >= ARENAX2)) vx[i] = -vx[i];
            if ((y[i] <= ARENAY1) || (y[i] >= ARENAY2)) vy[i] = -vy[i];
        }

        for (int i = 0; i < LICZBAKULEK; i++){
            x[i] += vx[i];
            y[i] += vy[i];
        }

        for (int i = 0; i < LICZBAKULEK; i++){
            gc.setFill(Color.WHITESMOKE);
            gc.fillOval(x[i] - R, y[i] - R, 2*R, 2*R);
        }
    }
}
