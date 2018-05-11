package fi.defence.ui;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Kuvaa pelin aikana näkyvää ylävalikkoa
 */
public class TopBar {
    
    private HBox layout;
    private ToggleButton towerButton, deleteButton;
    private Button saveButton;
    private ToggleGroup toggleGroup;
    private Text text1;
    private TextField textField;
    private boolean save;

    /**
     * Luo uuden TopBar() olion ja alustaa jotain tarvittavia muuttuja ja niiden
     * välisiä yhteyksiä (esim. valintanapit)
     */
    public TopBar() {
        this.textField = new TextField();
        this.toggleGroup = new ToggleGroup();
        this.layout = new HBox();
        this.towerButton = new ToggleButton("Tower");
        this.deleteButton = new ToggleButton("Delete");
        this.saveButton = new Button("Save this map");
        towerButton.setToggleGroup(toggleGroup);
        deleteButton.setToggleGroup(toggleGroup);
        this.text1 = new Text("-/-");
        this.text1.setFont(Font.font("Verdana", 20));
    }

    /**
     * Alustaa luokan oliot käyttökelpoisiksi
     * Asettaa jokaiselle napille tarvittavat event-handlerit ja alustaa myös teksti kentän
     * 
     * @return palauttaa HBox olion joka voidaan lisätä juuri ikkunan lapseksi
     * jolloin saadaan valikko palkki näkyviin
     */
    public HBox init() {
        this.layout.getChildren().addAll(this.towerButton, this.deleteButton, text1, this.saveButton, this.textField);
        this.textField.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            textField.setText("");
        });
        this.textField.setText("Enter map name here");
        this.saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (!"".equals(TopBar.this.textField.getText())) {
                    TopBar.this.save = true;
                    saveButton.setText("Map saved!");
                    saveButton.setDisable(true);
                    textField.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
                } else {
                    textField.setText("Name cannot be empty");
                }
            }
        });
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
    
    public String getTextFieldText() {
        return this.textField.getText();
    }
    
    public Text getText() {
        return this.text1;
    }
    
    public boolean getSave() {
        return save;
    }
    
}
