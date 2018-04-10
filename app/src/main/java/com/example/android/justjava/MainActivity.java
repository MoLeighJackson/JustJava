package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the plus (+) button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the plus (+) button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        String name = nameInput.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.add_whipped_cream_checkbox);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();
        /**
         * message to test if add whipped cream logic is working
         * Log.v("MainActivity", "Add whipped cream: " + addWhippedCream);
         */
        CheckBox chocolateSyrupCheckBox = (CheckBox) findViewById(R.id.chocolate_syrup_checkbox);
        boolean addChocolateSyrup = chocolateSyrupCheckBox.isChecked();

        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, addWhippedCream, addChocolateSyrup, name);
        displayMessage(priceMessage);


    }
    /**
     * Calculates the price of the order.
     *
     * @return total price of order
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }
    /**
     * Declares: create order summary
     *
     * @param whippedCream is whether or not the user wants whipped cream topping
     * @param chocolate is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */

    private String createOrderSummary(int price, boolean whippedCream, boolean chocolate, String name){
        String priceMessage = "Name: " + name;
        priceMessage = "\nAdd whipped cream? " + whippedCream;
        priceMessage = "\nAdd chocolate syrup? " + chocolate;
        priceMessage = priceMessage + "\nNumber of coffees: " + quantity;
        priceMessage = priceMessage + "\nOrder total: $" + price;
        priceMessage = priceMessage + "\nThank You! \nYour order is on its way!";
        return priceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numOfCoffees);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
