/*
 * Class: CMSC214 
 * Instructor:Mark Estep
 * Description: 32.5 (Display a running fan). Rewrite programming exercise 15.28 
 * using a thread to control animation rather than using Timeline animation object. 
 * * Due: 04/23/2023
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Will Tiwari
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Fan extends Application {
	private int sleepTime = 50;
	
	FanPane fan = new FanPane(100);
	
	private Thread thread = new Thread(() -> {
	    try {
	      while (true) {
	        fan.move();
	        Thread.sleep(sleepTime);
	      }
	    }
	    catch (InterruptedException ex) {
	    }
	  });
	
	@SuppressWarnings("removal")
	public void pause() {
	    thread.suspend();
	  }
	 
	  @SuppressWarnings("removal")
	public void play() {
	    thread.resume();
	  }

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		 thread.start();
		
        
        Button pause = new Button("Pause");
        pause.setOnAction(e -> pause());

        Button resume = new Button("Resume");
        resume.setOnAction(e -> play());

      

        Button reverse = new Button("Reverse");
        reverse.setOnAction(e -> fan.reverse());
        
        
        HBox hButtons = new HBox(pause,resume, reverse);
        hButtons.setSpacing(10);
        hButtons.setAlignment(Pos.CENTER);
        hButtons.setPadding(new Insets(10, 10, 10, 10));
        BorderPane borderPane = new BorderPane(fan, null, null, hButtons, null);


        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setTitle("Spinning fan");
        primaryStage.show();
    }
	
	

}
