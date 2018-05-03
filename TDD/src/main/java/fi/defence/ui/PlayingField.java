package fi.defence.ui;

import fi.defence.engine.Map;
import fi.defence.io.IO;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final Map Map;
    private final TopBar topBar;
    private final Entities ent;
    private final IO io;
    private boolean saved;

    public PlayingField(Map map, IO io) {
        this.io = io;
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
        this.saved = false;
        this.setPath();
        this.bPane.setTop(this.topBar.init());
        this.mouseInit();
        drawEnemy();
    }

    public void animate() {
        this.pane.getChildren().removeAll(this.ent.returnRemovableEnemyShapes());
        this.pane.getChildren().removeAll(this.ent.returnRemovableTowers());
        this.pane.getChildren().addAll(this.ent.returnProjectiles());
        this.pane.getChildren().removeAll(this.ent.returnRemovableProjectiles());
        this.topBar.getText().textProperty().setValue("" + this.Map.getMoney() + " - " + this.Map.getHealth() + (Map.getHealth() >= 0 ? "" : "DEAD"));
        if (topBar.getSave() && !this.saved) {
            try {
                this.io.savePath(this.Map.getPath(), this.topBar.getTextFieldText());
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

    private void drawEnemy() {
        this.pane.getChildren().addAll(this.ent.addEnemy());
    }

    private void mouseInit() {
        this.pane.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.topBar.deleteIsSelected()) {
                for (Shape s : this.ent.getFriendly()) {
                    if (s.intersects(event.getSceneX(), event.getY(), 10, 10)) {
                        this.pane.getChildren().remove(s);
                        if (!this.Map.removeTower((int) event.getSceneX(), (int) event.getY())) {
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
