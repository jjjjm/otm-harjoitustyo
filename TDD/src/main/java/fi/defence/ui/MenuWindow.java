package fi.defence.ui;

import javafx.event.EventType;
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

    public MenuWindow() {
        this.gameStart = false;
        this.bpane = new BorderPane();
        HBox hbox = new HBox();
        this.bpane.setCenter(hbox);
        this.bpane.setPrefSize(100, 100);
        this.button1 = new Button("Start");
        this.button2 = new Button("Load something");
        hbox.getChildren().addAll(button1,button2);
        this.scene = new Scene(bpane);
    }

    public void init() {
        bpane.setTop(new Text("Very nice tower defence game"));
            this.button1.setOnMouseClicked(e ->{
                this.gameStart = true;
            });
    }
    
    public boolean startGame(){
        return this.gameStart;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getStartButton() {
        return button1;
    }
    
    
    

}
