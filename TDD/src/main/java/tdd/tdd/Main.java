package tdd.tdd;

import engine.Map;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.PlayingField;

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
        Map map = new Map(800, 600);
        //map.generateNewRandomPath(200,650,400,400);
        map.getPath().setDefaultPath();
        PlayingField pf = new PlayingField(map);
        pf.init();
        primaryStage.setScene(pf.getScene());
        primaryStage.show();
    }

}
