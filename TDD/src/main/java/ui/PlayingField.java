package ui;

import engine.Map;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.util.Pair;

public class PlayingField {
    
    private Pane pane;
    private Scene scene;
    private Map Map;
    private TopBar topBar;

    public PlayingField(Map map) {
        this.pane = new Pane();
        this.Map = map;
        pane.setPrefSize(this.Map.getWidth(), this.Map.getLength());
        this.scene = new Scene(pane);
        this.topBar = new TopBar();
    }

    public void init() {
        this.setPath();
        this.pane.getChildren().add(this.topBar.init());
        this.mouseInit();
    }

    public Scene getScene() {
        return scene;
    }

    private void setPath() {
        List<Pair<Integer, Integer>> al = Map.getPath().getCoords();
        for (int i = 1; i < Map.getPath().getCoords().size(); i++) {
            Polygon pathPart = new Polygon(al.get(i - 1).getKey() - 10, al.get(i - 1).getValue() - 10, al.get(i - 1).getKey() + 10, al.get(i - 1).getValue() + 10,
                    al.get(i).getKey() + 10, al.get(i).getValue() + 10, al.get(i).getKey() - 10, al.get(i).getValue() - 10
            );
            pathPart.setFill(Color.BURLYWOOD);
            this.pane.getChildren().add(pathPart);
            this.pane.getChildren().add(new Line(al.get(i - 1).getKey(), al.get(i - 1).getValue(), al.get(i).getKey(), al.get(i).getValue()));
        }
    }

    private boolean drawTower(double xd, double yd) {
        int x =(int) Math.floor(xd);
        int y =(int) Math.floor(yd);
        this.pane.getChildren().add(
                new Polygon(
                        x - 5, y - 10,
                        x + 5, y - 10,
                        x + 10, y,
                        x + 5, y + 10,
                        x- 5, y + 10,
                        x - 5, y));
        this.Map.addTower(x, y);
        return true; // todo, if placing was possible return true else false
    }

    private void mouseInit() {

        this.pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.topBar.deleteIsSelected()) {
                //todo
            }
            if (this.topBar.towerIsSelected()) {
                this.drawTower(event.getSceneX(), event.getSceneY());
            }
        });
    }

}
