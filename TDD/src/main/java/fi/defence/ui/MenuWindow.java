
package fi.defence.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class MenuWindow {
    private  BorderPane bpane;
    
    public MenuWindow() {
         this.bpane = new BorderPane();
         HBox hbox = new HBox();
         this.bpane.setCenter(hbox);
         Button button = new Button("Start");
         hbox.getChildren().add(button);
    }
     
}
