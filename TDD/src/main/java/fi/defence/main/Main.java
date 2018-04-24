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
            long previousMillis1 = 0;
            long previousMillis2 = 0;

            @Override
            public void handle(long now) {
                if (map.getHealth() >= 0) {
                    if (System.currentTimeMillis() - previousMillis1 > 10) {
                        map.resolve();
                        pf.animate();
                        previousMillis1 = System.currentTimeMillis();
                    }
                    if (System.currentTimeMillis() - previousMillis2 > 2000) {
                        map.resetTowers();
                        pf.addEnemy();
                        previousMillis2 = System.currentTimeMillis();
                    }
                }
            }
        }.start();
    }
}
