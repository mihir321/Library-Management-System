package business;

import java.io.IOException;

import application.Main;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginMapper 
{
	@FXML public TextField userName;
	@FXML public PasswordField password;
	@FXML public Text textArea;



	@FXML public void handleLoginSubmitButtonAction(ActionEvent actionEvent) throws LoginException  //for Login
	{
		System.out.println("inside login mapper");
		
		SystemController controller =  new SystemController();

		System.out.println("inside" +userName.getText() +" "+password.getText());
		controller.login(userName.getText(),password.getText());

		System.out.println(SystemController.currentAuth);

		if(SystemController.currentAuth != null)
		{
			textArea.setText("");
			
			Parent root1;
			try {
				root1 = FXMLLoader.load(getClass().getResource("/ui/MainScreenAdminLibrarian.fxml"));
				Stage stage = new Stage();
				
				
				
				if(SystemController.currentAuth.equals(Auth.LIBRARIAN))
				{
					stage.setTitle("Librarian Window");
				}
				else if(SystemController.currentAuth.equals(Auth.ADMIN))
				{
					stage.setTitle("Admin Window");
				}
				else if(SystemController.currentAuth.equals(Auth.BOTH))
				{
					stage.setTitle("Librarian And Admin Window");
				}
				stage.setScene(new Scene(root1));
				stage.show();
				
				Main.mainStage.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

			
		}
		else
		{
			
			textArea.setText("Invalid UserName or Password");
		}


	}
}
