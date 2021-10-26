
package finalPacman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login_form.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("PacMan");
        LoginController controller = loader.getController();
     
  
        primaryStage.setScene(new Scene(root, 1280.0, 720.0));
        primaryStage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}