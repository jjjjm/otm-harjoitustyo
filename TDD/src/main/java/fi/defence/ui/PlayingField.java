package fi.defence.ui;

import fi.defence.engine.Map;
import fi.defence.io.IO;
import java.io.IOException;
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

/**
 * Kuvaa pelinäkymää ja vastaa sen päivityksesä
 */
public class PlayingField {

    private final BorderPane bPane;
    private final Pane pane;
    private final Scene scene;
    private final Map map;
    private final TopBar topBar;
    private final Entities entitities;
    private final IO io;
    private boolean saved;

    public PlayingField(Map map, IO io) {
        this.io = io;
        this.bPane = new BorderPane();
        this.pane = new Pane();
        this.map = map;
        pane.setPrefSize(this.map.getWidth(), this.map.getLength());
        this.topBar = new TopBar();
        bPane.setCenter(pane);
        this.entitities = new Entities(map);
        this.scene = new Scene(bPane);
    }

    public void init() {
        this.saved = false;
        this.setPath();
        this.bPane.setTop(this.topBar.init());
        this.mouseInit();
        drawEnemy();
    }

    public void animate() {
        this.pane.getChildren().removeAll(this.entitities.returnRemovableEnemyShapes());
        this.pane.getChildren().removeAll(this.entitities.returnRemovableTowers());
        this.pane.getChildren().addAll(this.entitities.returnProjectiles());
        this.pane.getChildren().removeAll(this.entitities.returnRemovableProjectiles());
        this.topBar.getText().textProperty().setValue("" + this.map.getMoney() + " - " + this.map.getHealth() + (map.getHealth() >= 0 ? "" : "DEAD"));
        if (topBar.getSave() && !this.saved) {
            try {
                this.io.savePath(this.map.getPath(), this.topBar.getTextFieldText());
            } catch (IOException ex) {
                System.out.println("Vittu");
            }
            this.saved = true;
        }
    }

    public void addEnemy() {
        this.drawEnemy();
    }

    public Scene getScene() {
        return scene;
    }

    private void setPath() {
        List<Pair<Integer, Integer>> coordinateList = map.getPath().getCoords();
        Shape path = new Polygon((double) coordinateList.get(0).getKey() - this.map.getPath().getWidth(), (double) coordinateList.get(0).getValue() - this.map.getPath().getWidth());
        for (int i = 1; i < coordinateList.size(); i++) {
            Polygon pathPart = null;
            double x1 = coordinateList.get(i - 1).getKey();
            double x2 = coordinateList.get(i).getKey();
            double y1 = coordinateList.get(i - 1).getValue();
            double y2 = coordinateList.get(i).getValue();
            double w = this.map.getPath().getWidth();
            if (x1 == x2) {
                pathPart = new Polygon(
                        x1 - w, y1 - w,
                        x1 + w, y1 - w,
                        x2 + w, y2 + w,
                        x2 - w, y2 + w
                );
            } else {
                pathPart = new Polygon(
                        x1, y1 - w,
                        x1, y1 + w,
                        x2, y2 + w,
                        x2, y2 - w
                );
            }
            path = Polygon.union(pathPart, path);
            path.setFill(Color.BURLYWOOD);
        }
        this.pane.getChildren().add(path);
    }

    private boolean drawTower(double xd, double yd) {
        this.pane.getChildren().addAll(this.entitities.addTower(xd, yd));
        return true;
    }

    private void drawEnemy() {
        this.pane.getChildren().addAll(this.entitities.addEnemy());
    }

    private void mouseInit() {
        this.pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.topBar.deleteIsSelected()) {
                for (Shape s : this.entitities.getFriendly()) {
                    if (s.intersects(event.getSceneX(), event.getY(), 10, 10)) {
                        this.pane.getChildren().remove(s);
                        if (!this.map.removeTower((int) event.getSceneX(), (int) event.getY())) {
                        }
                    }
                }
            }
            if (this.topBar.towerIsSelected()) {
                this.drawTower(event.getX(), event.getY());
            }
        });
    }

}
