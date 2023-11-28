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

    public void initialize() {
        updateOrderInfo();

        removePizzaButton.setOnAction((event) -> {
            handleRemovePizzaButtonAction();
            updateOrderInfo();
        });
    }

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