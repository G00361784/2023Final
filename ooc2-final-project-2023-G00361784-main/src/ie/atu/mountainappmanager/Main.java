package ie.atu.mountainappmanager;

//required imports for this application
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class Main extends Application {

      public static void main(String[] args) {
            Application.launch(args);// run application
      } // End main method

      Mountainmanager newMM = new Mountainmanager();// createing an instance of mountain manager

      @Override
      public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Mountain List");// Set title for window when it appears

            // Following buttons to be added to VBox i called layout at end of this method
            Button addPopupButton = new Button("Click Me to add mountain to list");
            Button deletePopupButton = new Button("Click Me to remove first mountain from top of list");
            Button searchPopupButton = new Button(
                        "Click me to search for mountain by mountain Id result will appear at top of list");
            Button showTotalButton = new Button("Click me to find number of mountains on list");
            Button quitButton = new Button("Quit");

            // to add image to Top of GUI
            Image mountainImage = new Image("file:images/OIP.jpg");// source of iamge
            ImageView imageView = new ImageView(mountainImage);
            imageView.setFitWidth(400); // Set the width of the image view
            imageView.setPreserveRatio(true); // is used to specify whether to preserve the aspect ratio of the image
                                              // when resizing the ImageView.

            // General Styling for the buttons
            addPopupButton.setStyle(
                        "-fx-background-color: #87ceeb; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");
            deletePopupButton.setStyle(
                        "-fx-background-color: #87ceeb; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");
            searchPopupButton.setStyle(
                        "-fx-background-color: #87ceeb; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");
            showTotalButton.setStyle(
                        "-fx-background-color: #87ceeb; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");
            quitButton.setStyle(
                        "-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");

            // to add the variables i want to see i needed to declare labels to update later
            Label displayMounatinIdLabel = new Label();
            Label displayHeightLabel = new Label();
            Label displayDurationLabel = new Label();
            Label displayListLengthLabel = new Label();
            // displayTitleLabel was used to add title to top of GUI
            Label displayTitleLabel = new Label("Mountain Climbing Aid");

            // grid2 was added to set grid for list of items
            GridPane grid2 = new GridPane();
            grid2.setHgap(10);
            grid2.setVgap(10);
            grid2.setStyle("-fx-border-color: black; -fx-border-width: 1px;");// general styling to add boarder

            displayTitleLabel.setStyle("-fx-font-size: 20px;"); // Adjust the style of title label
            /*
             * The following code for setOnAction for addPopupButton will be explained the
             * comments that follow
             */
            addPopupButton.setOnAction(e -> {

                  // Dialog is a part of the JavaFX library and is used to create pop-up dialog
                  // boxes
                  Dialog<String> dialog = new Dialog<>();
                  dialog.setTitle("Enter Mountain Details");// setting the title of the pop up[]
                  dialog.setHeaderText("Please enter mountain details");

                  /*
                   * The second argument of the following Button, it indicates that
                   * clicking the "Add" button is equivalent to confirming
                   * or finishing an operation.
                   */
                  ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);

                  /*
                   * This line adds the "Add" button and a "Cancel" button to the list of button
                   * types associated with the Dialog. The getButtonTypes() method returns the
                   * list of available button types for the dialog's ButtonBar. The "Add"
                   * button is added first, followed by the "Cancel" button.
                   */
                  dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

                  // new grid for inputs from the user
                  GridPane grid = new GridPane();
                  grid.setHgap(10);
                  grid.setVgap(10);

                  // adding values for textfield which will be handled later when adding to
                  // Mountain Manager
                  TextField mountainIdField = new TextField();
                  TextField heightField = new TextField();
                  TextField durationField = new TextField();

                  // Adding new Labels to the grid in the correct position
                  grid.add(new Label("Mountain ID:"), 0, 0);
                  grid.add(mountainIdField, 1, 0);
                  grid.add(new Label("Height in metres:"), 0, 1);
                  grid.add(heightField, 1, 1);
                  grid.add(new Label("Duration walk in minutes:"), 0, 2);
                  grid.add(durationField, 1, 2);

                  // setting the grid to the pop up
                  dialog.getDialogPane().setContent(grid);

                  dialog.setResultConverter(dialogButton -> {

                        /*
                         * The following if condition checks if the user
                         * clicked the "Add" button.
                         * If the condition is true, it means the user
                         * wants to add a new mountain, and the code
                         * inside the block is executed.
                         */

                        if (dialogButton == addButton) {
                              return String.format("Mountain ID: %s, Height: %s, Duration: %s",
                                          mountainIdField.getText(), heightField.getText(), durationField.getText());
                        }
                        /*
                         * If the condition in the if statement is not met
                         * the method returns null to indicate that no valid
                         * result is available.
                         */
                        return null;

                  });

                  /*
                   * showAndWait() method displays the dialog and waits
                   * for the user to close it by clicking Add or Cancel.
                   *
                   * The result of showAndWait() is stored in the result variable which is
                   * optional
                   */
                  Optional<String> result = dialog.showAndWait();
                  result.ifPresent(mountainDetails -> {
                        // basic error handling follows

                        try {
                              // assigning the correct variable types to each value entered to the pop up
                              String mountainIdValue = mountainIdField.getText();
                              double heightValue = Double.parseDouble(heightField.getText());
                              int durationValue = Integer.parseInt(durationField.getText());

                              // Check if values entered are greater than zero
                              if (heightValue <= 0 || durationValue <= 0) {
                                    throw new IllegalArgumentException(
                                                "Height and duration must be greater than zero.");
                              }

                              // Add mountain to the list in the mountain manager
                              newMM.addMountainToList(mountainIdValue, heightValue, durationValue);

                              // Update UI labels
                              displayMounatinIdLabel.setText(mountainIdValue);
                              displayHeightLabel.setText(String.valueOf(heightValue));
                              displayDurationLabel.setText(String.valueOf(durationValue));

                        } catch (NumberFormatException inputError) {
                              // Handle the case where parsing to double or integer fails
                              showErrorDialog("Invalid Input",
                                          "Error: Invalid input format. Please enter valid values.");

                        } catch (IllegalArgumentException inputError) {
                              // Handle the case where height or duration is not greater than zero
                              showErrorDialog("Invalid Input",
                                          "Error: Invalid input format. Please enter valid numeric values.");
                        } catch (Exception inputError) {
                              // Handle other unexpected exceptions
                              System.err.println("Error: Unexpected exception occurred.");
                        } finally {
                              // add the updated values to the grid in the GUI.
                              updateUI(grid2);
                        }

                  });
            });

            deletePopupButton.setOnAction(e1 -> {
                  // deleteing the top value of the list through the following method
                  newMM.deleteMountainFromList();
                  // Updating the grid again as to show the deleted item removed
                  updateUI(grid2);
            });

            // I added some mountains to the arraylist in the following method
            newMM.startlist();
            // add them to grid.
            updateUI(grid2);

            // similar code was used here when compared to the add functionality
            searchPopupButton.setOnAction(e -> {
                  Dialog<String> searchDialog = new Dialog<>();
                  searchDialog.setTitle("Search Mountain");
                  searchDialog.setHeaderText("Please enter mountainId details");

                  ButtonType searchButtonType = new ButtonType("Search", ButtonBar.ButtonData.OK_DONE);
                  searchDialog.getDialogPane().getButtonTypes().addAll(searchButtonType, ButtonType.CANCEL);

                  GridPane grid = new GridPane();
                  grid.setHgap(10);
                  grid.setVgap(10);
                  grid.setPadding(new Insets(20, 150, 10, 10));

                  TextField searchField = new TextField();
                  grid.add(new Label("Search:"), 0, 0);
                  grid.add(searchField, 1, 0);

                  searchDialog.getDialogPane().setContent(grid);

                  searchDialog.setResultConverter(dialogButton -> {
                        if (dialogButton == searchButtonType) {
                              return searchField.getText();
                        }
                        return null;
                  });
                  // Show the dialog and handle the result
                  searchDialog.showAndWait().ifPresent(result -> {
                        if (!result.isEmpty()) {
                              Mountain foundMountain = newMM.findMountainByIdWithIterator(result);
                              if (foundMountain != null) {
                                    // Move the found mountain to the top of the list
                                    newMM.mountainList.remove(foundMountain);
                                    newMM.mountainList.add(0, foundMountain);

                                    updateUI(grid2);
                              }
                        }
                  });
            });

            // show total was added here with the getMountainListLength Method
            showTotalButton.setOnAction(ex2 -> {
                  int length = newMM.getMountainListLength();
                  // updated the following label with String value of length
                  displayListLengthLabel.setText(String.valueOf(length));

            });

            // quit button to close GUI
            quitButton.setOnAction(event -> primaryStage.close()); // Close the application when the Quit button is
                                                                   // clicked

            // following code to set the javafx
            VBox layout = new VBox(10);
            layout.getChildren().addAll(imageView, displayTitleLabel, showTotalButton, displayListLengthLabel,
                        addPopupButton,
                        deletePopupButton, searchPopupButton, grid2, quitButton);
            // setting the items in the layout to centre
            layout.setAlignment(Pos.CENTER);
            // creating Scene called scene for javafx purposes and adding the layout to it
            Scene scene = new Scene(layout, 600, 800);

            // adding the scene to the Stage
            primaryStage.setScene(scene);
            // showing the primaryStage
            primaryStage.show();
      }

      // following method to be called when the grid2 grid needs to be updated
      private void updateUI(GridPane grid) {
            // Clear the existing content in grid2
            grid.getChildren().clear();
            grid.add(new Label("MountainId"), 0, 0);
            grid.add(new Label("Height in Metres"), 1, 0);
            grid.add(new Label("Walk Duration In Minutes"), 2, 0);

            int rowIndex = 2; // Start from the second row (index 1) to leave space for headers
            /*
             * for loop that will add a mountain to the grid and
             * increase the rowIndex each time as to move the next
             * mountain to the next line
             */
            for (Mountain mountain : newMM.mountainList) {
                  Label mountainIdLabel = new Label(mountain.getMountainId());
                  Label heightLabel = new Label(String.valueOf(mountain.getHeightMetres()));
                  Label durationLabel = new Label(String.valueOf(mountain.getWalkDurationMinutes()));
                  grid.add(mountainIdLabel, 0, rowIndex);
                  grid.add(heightLabel, 1, rowIndex);
                  grid.add(durationLabel, 2, rowIndex);
                  rowIndex++; // Move to the next row for the next mountain
            }

      }

      // show error to the user when entering wrong numberic values for height and
      // duration
      private void showErrorDialog(String title, String content) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
      }
}
