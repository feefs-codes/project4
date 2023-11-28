package com.example.rupizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * CurrentOrderController class handles the events triggered on the Current Order GUI.
 *
 * @author Pranay Bhatt and Fiona Wang
 */

public class CurrentOrderController {
    @FXML
    private TextField orderNumber, subTotal, tax, total;
    @FXML
    private ListView<String> pizzasListView;
    @FXML
    private Button removePizzaButton, placeOrderButton;

    StoreOrders storeOrders = StoreOrders.getInstance();

    /**
     * Initializes event handlers and properties in the Current Order GUI:
     * updates the order info, if the Remove Pizza Button is active and activated,
     * then call a helper method that performs the action done by the button and update the order info.
     */
    public void initialize() {
        updateOrderInfo();

        removePizzaButton.setOnAction((event) -> {
            handleRemovePizzaButtonAction();
            updateOrderInfo();
        });
    }

    /**
     * Helper method that updates the current order information including:
     * what pizzas are in the order, the subtotal, tax, and total price.
     */
    private void updateOrderInfo() {
        if (storeOrders.getCurrentOrder() == null) {
            return;
        }
        List<String> pizzaStrings = storeOrders.getCurrentOrder().getPizzas().stream()
                                       .map(Pizza::toString)
                                       .collect(Collectors.toList());
        pizzasListView.setItems(FXCollections.observableArrayList(pizzaStrings));
        orderNumber.setText(storeOrders.getCurrentOrder().getOrderNumber()+"");
        subTotal.setText(String.format("$%.2f", storeOrders.getCurrentOrder().calculateSubTotal()));
        tax.setText(String.format("$%.2f", storeOrders.getCurrentOrder().calculateTax()));
        total.setText(String.format("$%.2f", storeOrders.getCurrentOrder().calculateTotal()));
    }

    /**
     * Handles the "Remove Pizza" button.
     * When a pizza is selected to be removed, then that pizza will be removed from the current order.
     */
    @FXML
    private void handleRemovePizzaButtonAction() {
        String selectedPizza = pizzasListView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            Order currentOrder = storeOrders.getCurrentOrder();
            ArrayList<Pizza> pizzas = currentOrder.getPizzas();
            pizzas.removeIf(pizza -> pizza.toString().equals(selectedPizza));
            pizzasListView.getItems().remove(selectedPizza);
        }
    }

    /**
     * Handles the "Place Order" button.
     * If the current order has items when this button is pressed, that order will be placed and the GUI will alert the
     * user so. Another order will be started.
     */
    @FXML
    private void handlePlaceOrderButtonAction() {
        Order currentOrder = storeOrders.getCurrentOrder();
        if (currentOrder != null && !currentOrder.getPizzas().isEmpty()) {
            storeOrders.startNewOrder();
            updateOrderInfo();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setHeaderText(null);
            alert.setContentText("Order #" + currentOrder.getOrderNumber() + " has been placed.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Stage stage = (Stage) placeOrderButton.getScene().getWindow();
                stage.close();
            }
        }
    }
}