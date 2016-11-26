package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	public static Stage mainStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			mainStage = primaryStage;
			
			 Parent root = FXMLLoader.load(getClass().getResource("/ui/loginScreen.fxml"));
			 primaryStage.setTitle("Login Library Management System");
			 Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
