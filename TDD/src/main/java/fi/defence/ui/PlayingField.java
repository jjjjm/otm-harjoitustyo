package fi.defence.ui;

import fi.defence.engine.Map;
import fi.defence.engine.Tower;
import java.util.List;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Pair;

public class PlayingField {

    private final BorderPane bPane;
    private final Pane pane;
    private final Scene scene;
    private final Map Map;
    private final TopBar topBar;
    private final Entities ent;

    public PlayingField(Map map) {
        this.bPane = new BorderPane();
        this.pane = new Pane();
        this.Map = map;
        pane.setPrefSize(this.Map.getWidth(), this.Map.getLength());
        this.topBar = new TopBar();
        bPane.setCenter(pane);
        this.ent = new Entities(map);
        this.scene = new Scene(bPane);
    }

    public void init() {
        this.setPath();
        this.bPane.setTop(this.topBar.init());
        this.mouseInit();
    }

    public Scene getScene() {
        return scene;
    }

    private void setPath() {
        List<Pair<Integer, Integer>> al = Map.getPath().getCoords();
        Shape path = new Polygon((double) al.get(0).getKey() - this.Map.getPath().getWidth(), (double) al.get(0).getValue() - this.Map.getPath().getWidth());
        for (int i = 1; i < Map.getPath().getCoords().size(); i++) {
            Polygon pathPart = null;
            if (Objects.equals(al.get(i).getKey(), al.get(i - 1).getKey())) {
                pathPart = new Polygon(
                        (double) al.get(i - 1).getKey() - this.Map.getPath().getWidth(), (double) al.get(i - 1).getValue() - this.Map.getPath().getWidth(),
                        (double) al.get(i - 1).getKey() + this.Map.getPath().getWidth(), (double) al.get(i - 1).getValue() - this.Map.getPath().getWidth(),
                        (double) al.get(i).getKey() + this.Map.getPath().getWidth(), (double) al.get(i).getValue() + this.Map.getPath().getWidth(),
                        (double) al.get(i).getKey() - this.Map.getPath().getWidth(), (double) al.get(i).getValue() + this.Map.getPath().getWidth()
                );
            } else {
                pathPart = new Polygon(
                        (double) al.get(i - 1).getKey(), (double) al.get(i - 1).getValue() - this.Map.getPath().getWidth(),
                        (double) al.get(i - 1).getKey(), (double) al.get(i - 1).getValue() + this.Map.getPath().getWidth(),
                        (double) al.get(i).getKey(), (double) al.get(i).getValue() + this.Map.getPath().getWidth(),
                        (double) al.get(i).getKey(), (double) al.get(i).getValue() - this.Map.getPath().getWidth()
                );
            }
            path = Polygon.union(pathPart, path);
            path.setFill(Color.BURLYWOOD);
        }
        this.pane.getChildren().add(path);
    }

    private boolean drawTower(double xd, double yd) {
        this.pane.getChildren().addAll(ent.addTower(xd, yd));
        return true;
    }

//    private List<Shape> mockUpTower(double xd, double yd) {
//        List<Shape> mockUp = ent.mockUp(xd, yd);
//        this.pane.getChildren().addAll(ent.addTower(xd, yd));
//        return mockUp;
//    }
    private void mouseInit() {

        this.pane.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            if (this.topBar.towerIsSelected()) {

            }
        });

        this.pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.topBar.deleteIsSelected()) {
                for (Shape s : this.ent.getFriendly()) {
                    if (s.intersects(event.getSceneX(), event.getY(), 3, 3)) {

                    }
                }
            }
            if (this.topBar.towerIsSelected()) {
                this.drawTower(event.getX(), event.getY());
            }
        });
    }

}
