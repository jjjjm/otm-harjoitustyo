package fi.defence.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MenuWindow {

    private BorderPane bpane;
    private Button button1, button2;
    private boolean gameStart;
    private Scene scene;
    private PlayingField pf;
    private List<Button> mapButtons;
    private String selectedMap;

    public MenuWindow() {
        this.gameStart = false;
        this.bpane = new BorderPane();
        HBox hbox = new HBox();
        this.bpane.setCenter(hbox);
        this.bpane.setPrefSize(200, 100);
        this.button1 = new Button("Start");
        this.button2 = new Button("Load map");
        hbox.getChildren().addAll(button1, button2);
        this.scene = new Scene(bpane);
    }

    public void init() {
        bpane.setTop(new Text("Very nice tower defence game"));
        this.button1.setOnMouseClicked(e -> {
            this.gameStart = true;
        });
    }

    public boolean startGame() {
        return this.gameStart;
    }

    public void addMapButton(String mapName) {
        if (this.mapButtons == null) {
            mapButtons = new ArrayList<>();
        }
        this.mapButtons.add(new Button(mapName));
        this.mapButtons.forEach(b -> {
            b.setOnMouseClicked(e -> {
                this.selectedMap = b.getText();
            });
        });
        HBox tmp = new HBox();
        tmp.getChildren().addAll(mapButtons);
        this.bpane.setBottom(tmp);
    }

    public Scene getScene() {
        return scene;
    }

    public Button getStartButton() {
        return button1;
    }

    public Button getLoadButton() {
        return button2;
    }
    
    public String getSelectedMapName(){
        return this.selectedMap == null ? "" : this.selectedMap;
    }

}
