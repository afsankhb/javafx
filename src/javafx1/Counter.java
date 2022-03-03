package javafx1;


	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author afsaneh khabbazi basmenj
 *This program is simply mimics word counter in MicrosoftWord  
 *
 *
 */

/*
 * Inherit from Application class. The main JavaFX class must inherit from Application.
 */
public class Counter extends Application {

/**
 * 
 * @param args twerKJDHFKDH
 */
	public static void main(String[] args) {
		//TODO to start a JavaFX application we must call the static method
		//launch( args) in the main method. This method is from the Application Class.
		launch(args);
	}
	
/**
 *  we get the text from the user
 */
	private TextArea textInput;  
	                              // For the user's input text.
	
	/**
	 * we create instance of label class for displaying the number of text's lines
	 */
	private Label lineCountLabel;  
	/**
	 * we create instance of label for displaying the number of text's words	
	 */
	private Label wordCountLabel;
	/**
	 * we create instance of label for displaying the number of text's chars
	 */
	private Label charCountLabel;   
	/**
	 * we create instance of label for displaying the number of text's digits
	 */
	private Label digitCountLabel;
	/**
	 * we create instance of ToolBar for using to display the toolbar's elements
	 */
	private ToolBar statusBar;

	/**
	 * The constructor creates components and lays out the window.
	 */
	
	
	@Override
	public void start(Stage stage) {
	
		textInput = new TextArea(); //initial textInput        
		textInput.setPrefRowCount(20);// set number of textInput's rows
		textInput.setPrefColumnCount(50);// set number of textInput's columns
		statusBar = new ToolBar();//initial statusBar
	
		/**
		 * call the method createStatusBar
		 * call setOnKeyPressed on this button and pass a lambda to it. In the lambda if the key pressed, invoke processInput method 
		 *
		 */
		
		createStatusBar();
		Button countButton = new Button("process the text");//Create a new button  "process the text" .

		countButton.setOnKeyPressed(
				
				event -> processInput()
				
				);		
		
		countButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");// set  button style   as "-fx-background-color: darkslateblue; -fx-text-fill: white;"
	
	
		/**
		 * when escape key is pressed close the application by using the lambda
		 */
	
		stage.addEventHandler( KeyEvent.KEY_RELEASED, ( KeyEvent event) -> {
	
			if ( !event.isConsumed() && KeyCode.ESCAPE == event.getCode()) {
				stage.hide();
			}
		});
	
		
		
	    //same style string for the labels 
		String 	Q = "-fx-padding: 4px; -fx-font: italic bold 16pt serif; -fx-background-color: white";
	
		/**
		 * Create each of the labels, and set their properties. 
		 */
	
		lineCountLabel = new Label("  Number of lines:");
		lineCountLabel.setStyle("-fx-text-fill: green;"+ Q);
		lineCountLabel.setMaxWidth(1000);

		wordCountLabel = new Label("  Number of words:");
		wordCountLabel.setStyle("-fx-text-fill: red;" +Q);
		wordCountLabel.setMaxWidth(1000);
		
		charCountLabel = new Label("  Number of chars:");
		charCountLabel.setStyle("-fx-text-fill: blue;" +Q);
		charCountLabel.setMaxWidth(1000);
		
		digitCountLabel = new Label("  Number of digits:");
		digitCountLabel.setStyle("-fx-text-fill: black;" +Q);
		digitCountLabel.setMaxWidth(1000);
		
	    
		/*
		 * create and append the elements to VBox(main frame of application) and set style for it
		 */
		VBox root = new VBox( 6,new BorderPane(statusBar),textInput, new BorderPane(countButton),
				lineCountLabel, wordCountLabel, charCountLabel,digitCountLabel);
	
		root.setStyle("-fx-background-color: lightgrey; -fx-border-color: grey; -fx-border-width:5px");
	
	
		/*
		 * create and set properties for the application's window and show it 
		 */
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("MicrosoftWord/Word/Char/digit Counter");
		stage.setResizable(false);
		stage.show();
	
	} 
	
	/**
	 * the method creates ToolBar for the application
	 */
	private void createStatusBar() {
		
		
		
		
		/*
		 * the label to show "Type your text here" and set the style 
		 */
		Label label_Type = new Label("Type your text here ");
		label_Type.setStyle( "-fx-text-fill: black; -fx-font: bold  12pt serif;-fx-padding: 4px;");
		
		/*
		 *  create a space between two labels(the most horizontal spacing)
		 */
		Pane pane = new Pane();
		HBox.setHgrow(pane,  Priority.ALWAYS);
		
		
		/*
		 * the label to show "Press Esc to Exit"
		 */
		Label label_Exit = new Label("Press Esc to Exit");
		
	
		/*
		 * appending element to toolbar
		 */
		statusBar.getItems().addAll(label_Type,pane,label_Exit);
		
		
		
	
	}
	/**
	 * The method computes the text's lines, words, characters and digits
	 * and set results to labels
	 */
	
	public void processInput() {
	
		String text;
	
		int charCt, wordCt, lineCt, digitCt;  // Char, word, line and digits  counts.
	
		text = textInput.getText();
	
		charCt = text.length();
	
		/* Compute the wordCt by counting the number of characters
	             in the text that lie at the beginning of a word.  The
	             beginning of a word is a letter such that the preceding
	             character is not a letter. If the letter is the first character in the
	             text, then it is the beginning of a word.  If the letter
	             is preceded by an apostrophe, and the apostrophe is
	             preceded by a letter, than its not the first character
	             in a word.
	             
		 */
	
		wordCt = 0;
		for (int i = 0; i < charCt; i++) {
			boolean startOfWord;
			if ( Character.isLetter(text.charAt(i)) == false )
				startOfWord = false;
			else if (i == 0)
				startOfWord = true;
			else if ( Character.isLetter(text.charAt(i-1)) )
				startOfWord = false;
			else if ( text.charAt(i-1) == '\'' && i > 1 
					&& Character.isLetter(text.charAt(i-2)) )
				startOfWord = false;
			else
				startOfWord = true;
			if (startOfWord)
				wordCt++;
		}
	

		/*
		 * 	count the number of line occurs in the text by count "\n". but for one line we haven't "\n" so
		 * increase it one unit 
		 */
		lineCt = 0;
		for (int i = 0; i < charCt; i++) {
			
			if (text.charAt(i) == '\n')
				lineCt++;
		}
		if(charCt!=0) {
			lineCt++;
		}
	
	
		/*
		 * 	count the number of digits occurs in the text.
		 */
		digitCt=0;
		for (int i = 0; i < charCt; i++) {
	            if (text.charAt(i) >= 48 && text.charAt(i) <= 57)
	                digitCt++;
	    }
	
	/*
	 * set the computing results in labels
	 */
		lineCountLabel.setText("  Number of lines:  " + lineCt);
		wordCountLabel.setText("  Number of words:  " + wordCt);
		charCountLabel.setText("  Number of chars:  " + charCt);
		digitCountLabel.setText("  Number of digits:  " + digitCt);
	
	}
	
}



