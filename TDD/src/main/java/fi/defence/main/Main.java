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
            long rCount = 0;

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - previousMillis1 > 10) {
                    if (rCount > 200) {
                        System.out.println("reset");
                        map.resetTowers();
                        pf.addEnemy();
                        rCount = 0;
                    }
                    pf.animate();
                    map.resolve();
                    rCount ++;
                    previousMillis1 = System.currentTimeMillis();

                }
            }
        }.start();
    }
}
