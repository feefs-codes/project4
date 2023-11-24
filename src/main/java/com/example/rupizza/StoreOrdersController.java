package com.example.rupizza;

import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
            updateOrderInfo();
        });
        exportStoreOrdersButton.setOnAction((event) -> {
            handleExportStoreOrdersButtonAction();
        });
        
    }
    private void updateOrderInfo() {
        // get first order in orders, and display its order number, pizzas, and total
        Order order = storeOrders.getOrder(Integer.parseInt(orderNumber.getValue()));
        if (order != null) {
            List<String> pizzaStrings = order.getPizzas().stream()
                                        .map(Pizza::toString)
                                        .collect(Collectors.toList());
            orderDetails.setText(String.join("\n", pizzaStrings));
            orderTotal.setText(String.format("$%.2f", order.calculateTotal()));
        }
        else {
            orderDetails.setText("");
            orderTotal.setText("");
        }
    }

    @FXML
    private void handleCancelOrderButtonAction() {
        // remove order from orders
        Order order = storeOrders.getOrder(Integer.parseInt(orderNumber.getValue()));
        if (order != null) {
            storeOrders.removeFromOrders(order);
            orderNumber.getItems().remove(order.getOrderNumber() + "");
            orderDetails.setText("");
            orderTotal.setText("");
        }
    }

    @FXML
    private void handleExportStoreOrdersButtonAction() {
        storeOrders.exportOrders();
    }

}