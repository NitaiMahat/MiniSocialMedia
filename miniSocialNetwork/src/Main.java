package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import src.model.Post;
import src.model.SocialNetwork;
import src.model.User;

public class Main extends Application {
    private SocialNetwork network = new SocialNetwork();
    private User currentUser;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mini Social Network");
        showLoginScreen(primaryStage);
    }

    private void showLoginScreen(Stage stage) {
        VBox layout = new VBox(10);
        TextField usernameField = new TextField();
        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");
        Label statusLabel = new Label();

        layout.getChildren().addAll(new Label("Username:"), usernameField, loginBtn, registerBtn, statusLabel);

        loginBtn.setOnAction(e -> {
            String name = usernameField.getText();
            if (network.userExists(name)) {
                currentUser = network.getUser(name);
                showMainUI(stage);
            } else {
                statusLabel.setText("User not found.");
            }
        });

        registerBtn.setOnAction(e -> {
            String name = usernameField.getText();
            if (network.registerUser(name)) {
                statusLabel.setText("Registered! You can now login.");
            } else {
                statusLabel.setText("Username already taken.");
            }
        });

        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void showMainUI(Stage stage) {
        VBox layout = new VBox(10);
        Label welcome = new Label("Welcome, " + currentUser.getUsername());
        TextArea postArea = new TextArea();
        Button postBtn = new Button("Post Status");
        ListView<String> feedList = new ListView<>();
        Button refreshFeed = new Button("Refresh Feed");

        postBtn.setOnAction(e -> {
            String content = postArea.getText();
            if (!content.isEmpty()) {
                currentUser.addPost(content);
                postArea.clear();
            }
        });

        refreshFeed.setOnAction(e -> {
            feedList.getItems().clear();
            for (Post post : currentUser.getFeed()) {
                feedList.getItems().add(post.toString());
            }
        });

        layout.getChildren().addAll(welcome, new Label("Post something:"), postArea, postBtn, new Label("Feed:"), feedList, refreshFeed);
        stage.setScene(new Scene(layout, 400, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}