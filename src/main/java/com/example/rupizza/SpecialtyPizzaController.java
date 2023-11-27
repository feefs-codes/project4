package com.example.rupizza;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SpecialtyPizzaController {
    @FXML
    private ComboBox<String> pizzaTypeComboBox;
    @FXML
    private RadioButton smallRadioButton, mediumRadioButton, largeRadioButton;
    @FXML
    private ToggleGroup sizeToggleGroup;
    @FXML
    private CheckBox extraCheeseCheckBox, extraSauceCheckBox;
    @FXML
    private TextField priceTextField, sauceTextField;
    @FXML
    private TextArea toppingsTextArea;
    @FXML
    private Button orderButton;
    @FXML
    private ImageView pizzaImageView;

    StoreOrders storeOrders = StoreOrders.getInstance();


    public void initialize() {
        pizzaTypeComboBox.setItems(FXCollections.observableArrayList("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni"));
        smallRadioButton.setToggleGroup(sizeToggleGroup);
        mediumRadioButton.setToggleGroup(sizeToggleGroup);
        largeRadioButton.setToggleGroup(sizeToggleGroup);

        orderButton.setDisable(true);

        Image image = new Image(getClass().getResourceAsStream("/GUI_images/specialtypizzas.png"));
        pizzaImageView.setImage(image);

        // add listener to pizzaTypeComboBox to update toppings, sauce, and price textfields
        pizzaTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            Image newImage = new Image(getClass().getResourceAsStream("/GUI_images/" + newValue.toLowerCase() + "pizza.png"));
            pizzaImageView.setImage(newImage);
            sizeToggleGroup.selectToggle(smallRadioButton);
            extraCheeseCheckBox.setSelected(false);
            extraSauceCheckBox.setSelected(false);

            keyReleasedProperty();
            updatePizzaInfo();
        });
        // add listener to sizeToggleGroup to update price textfield
        sizeToggleGroup.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            keyReleasedProperty();
            updatePizzaInfo();
        });
        extraCheeseCheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            updatePizzaInfo();
        });
        extraSauceCheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            updatePizzaInfo();
        });
    }

    @FXML
    private void keyReleasedProperty() {
        orderButton.setDisable(pizzaTypeComboBox.getValue() == null || sizeToggleGroup.getSelectedToggle() == null);
    }

    private void updatePizzaInfo() {
        Pizza pizza = PizzaMaker.createPizza(pizzaTypeComboBox.getValue());
        if (pizza != null) {
            toppingsTextArea.setText(pizza.getToppings());
            sauceTextField.setText(pizza.getSauce());

            Toggle selectedSizeToggle = sizeToggleGroup.getSelectedToggle();
            if (selectedSizeToggle != null) {
                pizza.setSize(Size.valueOf(((RadioButton) selectedSizeToggle).getText().toUpperCase()));
            }
            if (extraCheeseCheckBox.isSelected()) {
                pizza.addExtraCheese();
            }
            if (extraSauceCheckBox.isSelected()) {
                pizza.addExtraSauce();
            }
            priceTextField.setText(String.format("$%.2f", pizza.price()));
        }
    }

    @FXML
    private void handleOrderButtonAction() {
        String sizeString = ((RadioButton) sizeToggleGroup.getSelectedToggle()).getText();
        Size size = null;
        switch (sizeString) {
            case "small":
                size = Size.SMALL;
                break;
            case "medium":
                size = Size.MEDIUM;
                break;
            case "large":
                size = Size.LARGE;
                break;
        }
        boolean extraCheese = extraCheeseCheckBox.isSelected();
        boolean extraSauce = extraSauceCheckBox.isSelected();
        String pizzaType = pizzaTypeComboBox.getValue();
        Pizza pizza = PizzaMaker.createPizza(pizzaType);
        pizza.setSize(size);
        if (extraCheese) {
            pizza.addExtraCheese();
        }
        if (extraSauce) {
            pizza.addExtraSauce();
        }

        // add pizza to order
        Order currentOrder = storeOrders.getCurrentOrder();
        if (currentOrder != null) {
            currentOrder.addToOrder(pizza);
        }
         else {
             storeOrders.startNewOrder();
             storeOrders.getCurrentOrder().addToOrder(pizza);
         }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(pizzaType + " specialty pizza added to order!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // close the window
            Stage stage = (Stage) orderButton.getScene().getWindow();
            stage.close();
        }
    }

}