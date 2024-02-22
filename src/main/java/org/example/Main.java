package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class Main extends Application {

    private TextArea outputTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Reader");

        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);

        Label label = new Label("Choose a file to process:");

        Button submitButton = new Button("Choose File and Submit");
        submitButton.setOnAction(e -> submitButtonClicked());

        VBox vbox = new VBox(label, outputTextArea, submitButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void submitButtonClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            processFile(file);
        }
    }

    private void processFile(File file) {
        Instant inst1 = Instant.now();

        Solve solve = Solve.getInstance();
        solve.readTxt(file.getAbsolutePath());

        StringBuilder sb = new StringBuilder();
        sb.append("Max number = ").append(String.format("%.1f", solve.maxNum())).append("\n");
        sb.append("Min number = ").append(String.format("%.1f", solve.minNum())).append("\n");
        sb.append("Median = ").append(String.format("%.1f", solve.median())).append("\n");
        sb.append("Avg = ").append(String.format("%.1f", solve.avg())).append("\n");
        sb.append("Highest upward streak = ").append(solve.upwardCombo()).append("\n");
        sb.append("Highest downward streak = ").append(solve.downwardCombo()).append("\n");

        Instant inst2 = Instant.now();
        long timeElapsed = Duration.between(inst1, inst2).getSeconds();

        sb.append("Time spent = ").append(timeElapsed).append(" sec").append("\n");

        outputTextArea.setText(sb.toString());
    }

}
