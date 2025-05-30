package controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import utility.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Controller class for Toolbar UI
public class ToolbarController implements Initializable {
    @FXML
    private JFXHamburger viewOptionsPopUpBurger;
    @FXML
    private StackPane viewOptionsPopUpContainer;
    @FXML
    private JFXRippler toolbarPopUpRippler;
    @FXML
    private StackPane toolbarPopUpBurger;

    private JFXPopup toolbarPopUp;
    private JFXPopup viewPopUp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadToolbarPopUp();
        loadToolbarPopUpActionListener();
        loadViewOptionsPopUp();
        loadViewOptionsPopUpActionListener();
    }

    // EFFECTS: load options pop up (setting, exit)
    private void loadToolbarPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/ToolbarPopUp.fxml"));
            fxmlLoader.setController(new ToolbarPopUpController());
            toolbarPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // EFFECTS: load view selector pop up (list view, priority view, status view)
    private void loadViewOptionsPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/ViewOptionsPopUp.fxml"));
            fxmlLoader.setController(new ViewOptionsPopUpController());
            viewPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // EFFECTS: show view selector pop up when its icon is clicked
    private void loadViewOptionsPopUpActionListener() {
        viewOptionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                viewPopUp.show(viewOptionsPopUpBurger,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.LEFT,
                    12,
                    15);
            }
        });
    }

    // EFFECTS: show options pop up when its icon is clicked
    private void loadToolbarPopUpActionListener() {
        toolbarPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                toolbarPopUp.show(toolbarPopUpBurger,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.RIGHT,
                    -12,
                    15);
            }
        });
    }

    // Inner class: view selector pop up controller
    class ViewOptionsPopUpController {
        @FXML
        private JFXListView<?> viewPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = viewPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("TodobarActionsPopUpController", "List View Selected");
                    break;
                case 1:
                    Logger.log("TodobarActionsPopUpController", "Priority View is not supported in this version!");
                    break;
                case 2:
                    Logger.log("TodobarActionsPopUpController", "Status View is not supported in this version!");
                    break;
                default:
                    Logger.log("TodobarActionsPopUpController", "No action is implemented for the selected option");
            }
            viewPopUp.hide();
        }
    }

    // Inner class: option pop up controller
    class ToolbarPopUpController {
        @FXML
        private JFXListView<?> toolbarPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = toolbarPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("TodobarOptionsPopUpController", "Setting is not supported in this version");
                    break;
                case 1:
                    Logger.log("TodobarOptionsPopUpController", "Close application");
                    Platform.exit();
                    break;
                default:
                    Logger.log("TodobarOptionsPopUpController", "No action is implemented for the selected option");
            }
            toolbarPopUp.hide();
        }
    }
}
