/**

 * The Controller handles user input and coordinates the updating of the model and the view with the help of a timer.
 */

package finalPacman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent>{
    final private static double FRAMES_PER_SECOND = 5.0;

    @FXML private Label scoreLabel;
    @FXML private Label levelLabel;
    @FXML private Label gameOverLabel;
    @FXML private PacManView pacManView;
    private PacManModel pacManModel;
    public static int sp =50;
   
   public int level =1 ;
    
    
    private static final String[] levelFiles = {"src/levels/level1.txt", "src/levels/level2.txt", "src/levels/level3.txt"};

    private Timer timer;
    private static int ghostEatingModeCounter;
    private boolean paused;

    public Controller() {
        this.paused = false;
    }

    /**
     * Initialize and update the model and view from the first txt file and starts the timer.
     */
    public void initialize() {
        String file = Controller.getLevelFile(UsrData.getlevel()-1);
        this.pacManModel = new PacManModel(UsrData.getlevel());
        
   
        this.update(PacManModel.Direction.NONE);
        ghostEatingModeCounter = 25;
        this.startTimer();
    }

    /**
     * Schedules the model to update based on the timer.
     */
    private void startTimer() {
        this.timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        update(PacManModel.getCurrentDirection());
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)((1000.0 *50)/(PacManModel.sps * FRAMES_PER_SECOND));
        
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
        
    }

    /**
     * Steps the PacManModel, updates the view, updates score and level, displays Game Over/You Won, and instructions of how to play
        direction the most recently inputted direction for PacMan to move in
     */
    private void update(PacManModel.Direction direction) {
        this.pacManModel.step(direction);
        this.pacManView.update(pacManModel);
        this.scoreLabel.setText(String.format("Score: %d", this.pacManModel.getScore()));
        this.levelLabel.setText(String.format("Level: %d", this.pacManModel.getLevel()));
        if (pacManModel.isGameOver()) {
        	
        	
            this.gameOverLabel.setText(String.format("GAME OVER"));
            
            UsrData.setusrhighscore(this.pacManModel.getLevel(), this.pacManModel.getScore());
            try {
				UsrData.updateTB(UsrData.usid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            pause();
        }
        if (pacManModel.isYouWon()) {
            this.gameOverLabel.setText(String.format("YOU WON!"));
        }
        //when PacMan is in ghostEatingMode, count down the ghostEatingModeCounter to reset ghostEatingMode to false when the counter is 0
        if (pacManModel.isGhostEatingMode()) {
            ghostEatingModeCounter--;
        }
        if (ghostEatingModeCounter == 0 && pacManModel.isGhostEatingMode()) {
            pacManModel.setGhostEatingMode(false);
        }
    }

    /**
     * Takes in user keyboard input to control the movement of PacMan and start new games
        keyEvent user's key click
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        boolean keyRecognized = true;
        KeyCode code = keyEvent.getCode();
        PacManModel.Direction direction = PacManModel.Direction.NONE;
        if (code == KeyCode.LEFT) {
            direction = PacManModel.Direction.LEFT;
        } else if (code == KeyCode.RIGHT) {
            direction = PacManModel.Direction.RIGHT;
        } else if (code == KeyCode.UP) {
            direction = PacManModel.Direction.UP;
        } else if (code == KeyCode.DOWN) {
            direction = PacManModel.Direction.DOWN;
        } else if (code == KeyCode.G) {
            pause();
            this.pacManModel.startNewGame();
            this.gameOverLabel.setText(String.format(""));
            paused = false;
            this.startTimer();
        } else if (code ==KeyCode.S  ) {
        	try {
				this.clickMe(keyEvent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else {
            keyRecognized = false;
        }
        if (keyRecognized) {
            keyEvent.consume();
            pacManModel.setCurrentDirection(direction);
        }
    }

    /**
     * Pause the timer
     */
    public void pause() {
            this.timer.cancel();
            this.paused = true;
    }

    public double getBoardWidth() {
        return PacManView.CELL_WIDTH * this.pacManView.getColumnCount();
    }

    public double getBoardHeight() {
        return PacManView.CELL_WIDTH * this.pacManView.getRowCount();
    }

    public static void setGhostEatingModeCounter() {
        ghostEatingModeCounter = 25;
    }

    public static int getGhostEatingModeCounter() {
        return ghostEatingModeCounter;
    }

    public static String getLevelFile(int x)
    { 
        return levelFiles[x];
    }

    public boolean getPaused() {
        return paused;
    }
	
public void clickMe(KeyEvent e) throws Exception {
	

 
	
	Parent root = FXMLLoader.load(getClass().getResource("sss.fxml"));
	
	Stage stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
	double sceneWidth = this.getBoardWidth() + 900.0;
    double sceneHeight = this.getBoardHeight() + 300.0;
	Scene scene = new Scene(root,sceneWidth,sceneHeight);
	stage.setScene(scene);
	stage.show();
	
	
	
}

public void sl(int l)
{
	this.level= l;
	
}



}
