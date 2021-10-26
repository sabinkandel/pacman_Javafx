package finalPacman;



import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller2 implements Initializable  {

     private String[] levels = {"level 1", "level 2", "level 3"};
     int mysp = 50;
     @FXML private ChoiceBox<String> cb;  
     @FXML private Slider speed;
     @FXML private Label ul1;
     @FXML private Label ul2;
     @FXML private Label ul3;
     @FXML private Label l1;
     @FXML private Label l2;
     @FXML private Label l3;
     @FXML private Label username;
     
private int ll = 1;

public void abc (ActionEvent ev)  throws IOException{


	 FXMLLoader loader = new FXMLLoader(getClass().getResource("pacman.fxml"));
     Parent root = loader.load();
 
     
 Stage tage =(Stage)((Node)ev.getSource()).getScene().getWindow() ;
    
     
     Controller controller = loader.getController();
     
     PacManModel pm = new PacManModel(ll);
     
     
  
     root.setOnKeyPressed(controller);
     double sceneWidth = controller.getBoardWidth() + 900.0;
     double sceneHeight = controller.getBoardHeight() + 300.0;
     tage.setScene(new Scene(root, sceneWidth, sceneHeight));
     tage.show();
     root.requestFocus();



}


@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
	try {
		UsrData.updateTB(UsrData.usid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 ul1.setText(Integer.toString(UsrData.getusrhighscore(1)));
	 ul2.setText(Integer.toString(UsrData.getusrhighscore(2)));
	 ul3.setText(Integer.toString(UsrData.getusrhighscore(3)));

	 l1.setText("N/A");
	 l2.setText("N/A");
	 l3.setText("N/A");
	 username.setText(UsrData.getsername());
	cb.getItems().setAll(levels);	
    cb.setOnAction(this::getLevel );
    
    speed.valueProperty().addListener(new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
			// TODO Auto-generated method stub
			PacManModel.sps=(int) speed.getValue();
			
		}
    }
    );
    }
    

public void getLevel(ActionEvent e) {

	String myLevel = cb.getValue();
	int i = 999;
	for(i =0; i<3 ;i++)
	{
		if(myLevel == levels[i]) UsrData.setlevel(i+1);
	}
	
}
	
}

	


