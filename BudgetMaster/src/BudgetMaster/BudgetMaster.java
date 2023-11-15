package BudgetMaster;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class BudgetMaster extends Application {
    public void start(Stage primaryStage) {
        //Creating the scene and panes
        BorderPane border = new BorderPane();
        ScrollPane scroll = new ScrollPane();
        Scene scene = new Scene(border, 200, 200); // Integer values are width and height, can be changed

        //Set title
        primaryStage.setTitle("Scroll Page Test");

        /*
       	 * Organize the visual aspects
       	 */
        //Puts our scroll pane in the center of the border pane
        border.setCenter(scroll);

        //Puts the selected image into the scroll pane, this will be changed to Transactions later
        //Calls the imageView method from below to get the image
        scroll.setContent(imageView("file:\\C:\\Users\\alexp\\Documents\\Pictures\\CleetusWallpaper.jpg"));

        //Allows the user to pan the view using the mouse
        scroll.setPannable(true);

        //Sets the horizontal and vertical hotbar to be ALWAYS, AS_NEEDED, or NEVER
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        //Sets the scene and displays it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
     * Returns an image from a specified file address
     */
    private ImageView imageView(String fileAddress) {
    	//Create image object from our file
        Image image = new Image("file:\\C:\\Users\\alexp\\Documents\\Pictures\\CleetusWallpaper.jpg");

        //Creating the image view
        ImageView imageView = new ImageView();

        //Setting image to image view
        imageView.setImage(image);

        return imageView;
    }

    public static void main(String[] args) {
    	launch(args);
    }
}
