package ui;

import java.io.File;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class TopBar {

    private HBox layout;
    private ToggleButton towerButton, deleteButton;
    private ToggleGroup toggleGroup;

    public TopBar() {
        this.toggleGroup = new ToggleGroup();
        this.layout = new HBox();
        this.towerButton = new ToggleButton("Tower");
        this.deleteButton = new ToggleButton("Delete");
        towerButton.setToggleGroup(toggleGroup);
        deleteButton.setToggleGroup(toggleGroup);
    }

    public HBox init() {
        this.layout.getChildren().addAll(this.towerButton, this.deleteButton);
        this.layout.setTranslateX(0);
        this.layout.setTranslateY(0);
        this.layout.getStylesheets().add("file:" + new File("style.css").getAbsolutePath());
        this.layout.getStyleClass().add("upperbar");
        return this.layout;
    }

    public HBox getLayout() {
        return layout;
    }

    public boolean deleteIsSelected() {
        return this.deleteButton.isSelected();
    }

    public boolean towerIsSelected() {
        return this.towerButton.isSelected();
    }

}
