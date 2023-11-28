package com.example.rupizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * MainMenuController class handles the events triggered on the main menu GUI.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class MainMenuController {

    /**
     * Opens Specialty GUI when button on main menu is selected.
     * @param event that specialty button on main menu is selected
     */
    @FXML
    private void openSpecialtyPizzaGUI(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("specialty-pizzas.fxml"));
            Parent root = loader.load();

            SpecialtyPizzaController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Order Specialty Pizzas");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Customize Pizza GUI when button on main menu is selected.
     * @param event that customize pizza button on main menu is selected
     */
    @FXML
    private void openCustomizePizzaGUI(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customize-pizza.fxml"));
            Parent root = loader.load();

            CustomizePizzaController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Customize Your Pizza");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Current Order GUI when button on main menu is selected.
     * @param event that current order button on main menu is selected
     */
    @FXML
    private void openCurrentOrderGUI(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("current-order.fxml"));
            Parent root = loader.load();

            CurrentOrderController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Order Details");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens Store Orders GUI when button on main menu is selected.
     * @param event that store orders button on main menu is selected
     */
    @FXML
    private void openStoreOrdersGUI(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("store-orders.fxml"));
            Parent root = loader.load();

            StoreOrdersController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}