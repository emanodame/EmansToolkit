package Notepad;

import IntroScreen.MainScreen;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sun.applet.Main;
import java.io.IOException;

public class Writer{
    private final static String fileName = "Notes.txt";
    private FlowPane notepadLayout = new FlowPane(Orientation.VERTICAL);
    private Scene notepadScene = new Scene(notepadLayout, 600, 300);
    private TextArea inputArea = new TextArea();

    public static String getFileName() {
        return fileName;
    }

    public Scene getNotepadScene() {
        notepadLayoutSetup();
        notepadLoadText();
        return notepadScene;
    }

    private void notepadLayoutSetup() {
        Text titleText = new Text("Notepad");
        titleText.setFont(new Font(36));
        titleText.setTextAlignment(TextAlignment.CENTER);

        notepadLayout.getChildren().add(titleText);
        notepadLayout.getChildren().add(inputArea);
        notepadLayout.setAlignment(Pos.TOP_CENTER);
    }

    private void notepadLoadText() {
      notepadSaveText();
        notepadScene.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                MainScreen.getCurrentStage().setScene(MainScreen.getCurrentScene());
            }
        });

        try {
            inputArea.setText(Load.loadTextFromFile());
        } catch (IOException e) {
            inputArea.setText("Error. Could not load text.");
        }
    }
    private void notepadSaveText(){

        inputArea.setOnKeyPressed((event) -> {
            if (event.isControlDown() && event.getCode() == KeyCode.S) {
                new Save(inputArea).saveToFile();
                System.out.println("Saved!");
            }
        });
    }

}
