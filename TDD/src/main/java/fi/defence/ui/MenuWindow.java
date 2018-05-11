package fi.defence.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Kuvaa alkuvalikkoa
 */
public class MenuWindow {

    private BorderPane bpane;
    private Button button1, button2;
    private boolean gameStart;
    private Scene scene;
    private PlayingField pf;
    private List<ToggleButton> mapButtons;
    private String selectedMap;
    private ToggleGroup toggleGroupMaps;

    /**
     * Luo uuden MenuWindow olion ja alustaa sille tarvittavat oliot
     */
    public MenuWindow() {
        this.gameStart = false;
        this.bpane = new BorderPane();
        bpane.setTop(new Text("Generate new/load old map"));
        HBox hbox = new HBox();
        this.bpane.setCenter(hbox);
        this.bpane.setPrefSize(100, 100);
        this.button1 = new Button("Start");
        this.button2 = new Button("Load map");
        hbox.getChildren().addAll(button1, button2);
        this.scene = new Scene(bpane);
        this.toggleGroupMaps = new ToggleGroup();
        this.button1.setOnMouseClicked(e -> {
            this.gameStart = true;
        });
    }
    /**
     * Palauttaa boolean arvon sen mukaan onko start nappia painettu
     * @return true jos start game nappia on painettu muuten false
     */
    public boolean startGame() {
        return this.gameStart;
    }

    /**
     * Luo uuden kartan lataus napin ja alustaa sille eventHandlerin sekä asettaa sen toglleGroupin
     * @param mapName Kartalle annettava nimi joka on samalla myös napin nimi
     */
    public void addMapButton(String mapName) {
        if (this.mapButtons == null) {
            mapButtons = new ArrayList<>();
        }
        this.mapButtons.add(new ToggleButton(mapName));
        this.mapButtons.forEach(b -> {
            b.setToggleGroup(toggleGroupMaps);
            b.setOnMouseClicked(e -> {
                this.selectedMap = b.getText();
            });
        });
        HBox tmp = new HBox();
        tmp.getChildren().addAll(mapButtons);
        ScrollPane tmp2 = new ScrollPane();
        tmp2.setContent(tmp);
        tmp2.setMinHeight(50);
        this.bpane.setBottom(tmp2);
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
    /**
     * Palauttaa valitun kartan nimen jos sellainen on
     * @return valitun kartan String arvoisen nimen tai tyhjän String olion jos sellaista ei ole
     */
    public String getSelectedMapName() {
        return null != this.toggleGroupMaps.getSelectedToggle() ? this.selectedMap : "";
    }

}
