package com.example.rupizza;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * CustomizePizzaController class handles the events triggered on the Build-Your-Own/Customized Pizza GUI.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class CustomizePizzaController {
    @FXML
    private TextField priceTextField;
    @FXML
    private CheckBox extraCheeseCheckBox, extraSauceCheckBox;
    @FXML
    private RadioButton tomatoSauceRadioButton, alfredoSauceRadioButton;
    @FXML
    private ToggleGroup sauceToggleGroup;
    @FXML
    private ComboBox<String> pizzaSizeComboBox;
    @FXML
    private Button addToppingButton, removeToppingButton, addToOrderButton;
    @FXML
    private ListView<String> availableToppingsListView, selectedToppingsListView;

    String[] toppings = {"Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom", "Ham", "Black Olive", "Beef", "Shrimp", "Squid", "Crab Meats", "Jalapeno", "Pineapple"};

    StoreOrders storeOrders = StoreOrders.getInstance();

    /**
     * Initializes event handlers and properties in the Customize Pizza GUI:
     * setting up and configuring all radio buttons/checkboxes and their toggle groups,
     * using listeners to track changes in text fields,
     * and disabling the addToOrder button unless there are valid inputs in each tab.
     */
    public void initialize() {
        pizzaSizeComboBox.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));
        tomatoSauceRadioButton.setToggleGroup(sauceToggleGroup);
        alfredoSauceRadioButton.setToggleGroup(sauceToggleGroup);

        availableToppingsListView.setItems(FXCollections.observableArrayList(toppings));

        addToOrderButton.setDisable(true);

        pizzaSizeComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            keyReleasedProperty();
            updateCustomPizzaInfo();
        });
        sauceToggleGroup.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            keyReleasedProperty();
            updateCustomPizzaInfo();
        });
        extraCheeseCheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            updateCustomPizzaInfo();
        });
        extraSauceCheckBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            updateCustomPizzaInfo();
        });
        addToppingButton.setOnAction((event) -> {
            handleAddToppingButton();
            updateCustomPizzaInfo();
        });
        removeToppingButton.setOnAction((event) -> {
            handleRemoveToppingButton();
            updateCustomPizzaInfo();
        });
    }

    /**
     * Updates the information in the custom pizza based on user input.
     * Price of the pizza is also updated and displayed.
     */
    private void updateCustomPizzaInfo() {
        Pizza pizza = PizzaMaker.createPizza("Custom");
        String selectedSize = pizzaSizeComboBox.getValue();
        if (selectedSize != null) {
            pizza.setSize(Size.valueOf(selectedSize.toUpperCase()));
        }
        Toggle selectedSauceToggle = sauceToggleGroup.getSelectedToggle();
        if (selectedSauceToggle != null) {
            if (((RadioButton) selectedSauceToggle).getText().equals("tomato sauce")) {
                pizza.setSauce(Sauce.TOMATO);
            }
            else {
                pizza.setSauce(Sauce.ALFREDO);
            }
        }
        if (extraCheeseCheckBox.isSelected()) {
            pizza.addExtraCheese();
        }
        if (extraSauceCheckBox.isSelected()) {
            pizza.addExtraSauce();
        }
        for (String topping : selectedToppingsListView.getItems()) {
            if (!pizza.toppings.contains(Topping.getTopping(topping))) {
                pizza.toppings.add(Topping.getTopping(topping));
            }
        }
        
        priceTextField.setText(String.format("$%.2f", pizza.price()));
    }

    /**
     * Handles the "Add Custom Pizza" button.
     * Ensures all user input is valid to activate the button and confirms when pizza is added to order,
     * or communicates there is invalid input.
     */
    @FXML
    private void handleAddCustomPizzaButton() {
        Pizza pizza = PizzaMaker.createPizza("Custom");
        String selectedSize = pizzaSizeComboBox.getValue();
        if (selectedSize != null) {
            pizza.setSize(Size.valueOf(selectedSize.toUpperCase()));
        }
        Toggle selectedSauceToggle = sauceToggleGroup.getSelectedToggle();
        if (selectedSauceToggle != null) {
            if (((RadioButton) selectedSauceToggle).getText().equals("tomato sauce")) {
                pizza.setSauce(Sauce.TOMATO);
            }
            else {
                pizza.setSauce(Sauce.ALFREDO);
            }
        }
        if (extraCheeseCheckBox.isSelected()) {
            pizza.addExtraCheese();
        }
        if (extraSauceCheckBox.isSelected()) {
            pizza.addExtraSauce();
        }
        for (String topping : selectedToppingsListView.getItems()) {
            if (!pizza.toppings.contains(Topping.getTopping(topping))) {
                pizza.toppings.add(Topping.getTopping(topping));
            }
        }
        // if there are less than 3 toppings, have an alert pop up and tell the user to add more toppings
        if (pizza.toppings.size() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Not Enough Toppings");
            alert.setContentText("Please add at least 3 toppings to your pizza.");
            alert.showAndWait();
            return;
        }
        
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
        alert.setHeaderText("Custom Pizza Added to Order");
        alert.setContentText(pizza.toString());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) addToOrderButton.getScene().getWindow();
            stage.close();
        }
        
        
    }

    /**
     * Handles "Add Topping" button, where if an available topping is selected when pressed,
     * then that topping will be added to the custom pizza.
     * This also updates the view of toppings where it is removed from the list of available toppings
     * and is added to the list of toppings already added.
     */
    @FXML
    private void handleAddToppingButton() {
        // add selected topping to selectedToppingsListView
        // remove selected topping from availableToppingsListView
        String selectedTopping = availableToppingsListView.getSelectionModel().getSelectedItem();
        // Topping topping = Topping.getTopping(selectedTopping);
        if (selectedTopping != null && selectedToppingsListView.getItems().size() < 7) {
            selectedToppingsListView.getItems().add(selectedTopping);
            availableToppingsListView.getItems().remove(selectedTopping);
        }
        addToppingButton.setDisable(selectedToppingsListView.getItems().size() >= 7);
    }

    /**
     * Handles "Remove Topping" button, where if an added topping is selected when pressed,
     * then that topping will be removed from the custom pizza.
     * This also updates the view of toppings where it is removed from the list of toppings already added
     * and is added to the list of available toppings.
     */
    @FXML
    private void handleRemoveToppingButton() {
        // add selected topping to availableToppingsListView
        // remove selected topping from selectedToppingsListView
        String selectedTopping = selectedToppingsListView.getSelectionModel().getSelectedItem();
        if (selectedTopping != null) {
            availableToppingsListView.getItems().add(selectedTopping);
            selectedToppingsListView.getItems().remove(selectedTopping);
        }
        addToppingButton.setDisable(selectedToppingsListView.getItems().size() >= 7);
    }

    /**
     * Keeps the "Add to Order" button disabled if the size and sauce selections are not chosen.
     */
    @FXML
    private void keyReleasedProperty() {
        boolean sizeSelected = pizzaSizeComboBox.getSelectionModel().getSelectedItem() != null;
        boolean sauceSelected = sauceToggleGroup.getSelectedToggle() != null;
        addToOrderButton.setDisable(!(sauceSelected && sizeSelected));
    }


}