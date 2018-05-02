package fi.defence.main;

import fi.defence.engine.Map;
import fi.defence.ui.MenuWindow;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import fi.defence.ui.PlayingField;
import io.IO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

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
        IO io = new IO();
        MenuWindow menu = new MenuWindow();
        menu.init();
        primaryStage.setScene(menu.getScene());
        primaryStage.show();

        menu.getLoadButton().setOnMouseClicked(e -> {
            try {
                for (String name : io.loadPath().keySet()) {
                    menu.addMapButton(name);
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });

        menu.getStartButton().setOnMouseClicked(e -> {
            Map map = new Map(1000, 900);
            map.generateNewRandomPath(25);
            if (!"".equals(menu.getSelectedMapName())) {
                try {
                    map.getPath().setCoords(io.loadPath().get(menu.getSelectedMapName()));
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
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
                            map.resetTowers();
                            pf.addEnemy();
                            rCount = 0;
                        }
                        pf.animate();
                        map.resolve();
                        rCount++;
                        previousMillis1 = System.currentTimeMillis();
                    }
                }
            }.start();

        });
    }
}
