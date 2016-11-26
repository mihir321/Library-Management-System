package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertMessageUtil {
	
	
	public static String INFORMATION_TITLE ="ALERT MESSAGE";
	
	public static String SUCCESS_TITLE ="SUCCESS MESSAGE";
	
	public static void showInformationDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(INFORMATION_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	
	public static void showExceptionDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(INFORMATION_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public static void showSucessDialog(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		alert.setTitle(SUCCESS_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
		
		
	}
	
	

}
