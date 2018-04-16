package fi.defence.main;

import fi.defence.engine.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import fi.defence.ui.PlayingField;

/**
 *
 * @author .
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Map map = new Map(1000, 900);
        map.generateNewRandomPath(15);
        //map.getPath().setDefaultPath();
        PlayingField pf = new PlayingField(map);
        pf.init();
        primaryStage.setScene(pf.getScene());
        primaryStage.show();

        new AnimationTimer() {
            long previous = 0;

            @Override
            public void handle(long now) {
                if (now - previous < 150000000) {

                }
                
                previous = now;
            }
        }.start();
    }

}
