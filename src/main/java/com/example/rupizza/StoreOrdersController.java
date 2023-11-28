package com.example.rupizza;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class StoreOrdersController {
    @FXML
    private TextField orderTotal;
    @FXML
    private ChoiceBox<String> orderNumber;
    @FXML
    private TextArea orderDetails;
    @FXML
    private Button cancelOrderButton, exportStoreOrdersButton;

    StoreOrders storeOrders = StoreOrders.getInstance();

    public void initialize() {
        for (Order order : storeOrders.getAllOrders()) {
            orderNumber.getItems().add(order.getOrderNumber() + "");
        }
        orderNumber.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            updateOrderInfo();
        });
        cancelOrderButton.setOnAction((event) -> {
            handleCancelOrderButtonAction();
            // updateOrderInfo();
            // orderNumber.getSelectionModel().clearSelection();
            // orderNumber.setValue(null);
        });
        exportStoreOrdersButton.setOnAction((event) -> {
            handleExportStoreOrdersButtonAction();
        });
        
    }
    private void updateOrderInfo() {
        // get first order in orders, and display its order number, pizzas, and total
        if (orderNumber.getValue() == null) {
            return;
        }
        Order order = storeOrders.getOrder(Integer.parseInt(orderNumber.getValue()));
        if (order != null) {
            List<String> pizzaStrings = order.getPizzas().stream()
                                        .map(Pizza::toString)
                                        .collect(Collectors.toList());
            orderDetails.setText(String.join("\n", pizzaStrings));
            orderTotal.setText(String.format("$%.2f", order.calculateTotal()));
        }
        else {
            // orderNumber.getSelectionModel().clearSelection();
            orderDetails.setText("");
            orderTotal.setText("");
        }
    }

    @FXML
    private void handleCancelOrderButtonAction() {
        // remove order from orders
        Order order = storeOrders.getOrder(Integer.parseInt(orderNumber.getValue()));
        if (order != null) {
            // if order is current order, tell user to place order first before cancelling
            if (order == storeOrders.getCurrentOrder()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot Cancel Order");
                alert.setHeaderText(null);
                alert.setContentText("Cannot cancel current order. Please place order first.");
                alert.showAndWait();
                return;
            }
            storeOrders.removeFromOrders(order);
            orderNumber.getItems().remove(order.getOrderNumber() + "");
            orderNumber.setValue(null);
            // set order number dropdown to empty
            // orderNumber.getSelectionModel().clearSelection();
            orderDetails.setText("");
            orderTotal.setText("");
        }
        // show alert that order has been cancelled
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancelled Order");
        alert.setHeaderText(null);
        alert.setContentText("Order " + order.getOrderNumber() + " has been cancelled.");
        alert.showAndWait();
    }

    @FXML
    private void handleExportStoreOrdersButtonAction() {
        storeOrders.exportOrders();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exported Orders");
        alert.setHeaderText(null);
        alert.setContentText("Orders have been exported to store_orders.txt");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // close the window
            Stage stage = (Stage) exportStoreOrdersButton.getScene().getWindow();
            stage.close();
        }
    }

}