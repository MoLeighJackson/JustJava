package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if (quantity == 25) {
            // Show an error message when customer tries to order more than 25 coffees
            Toast.makeText(this, "You cannot order more than 25 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the plus (+) button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            // Shows an error message if customer tries to order less than 1 coffee
            Toast.makeText(this, "You must order at least 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameInput = (EditText) findViewById(R.id.name_input);
        String name = nameInput.getText().toString();

        /**
         * add chocolate syrup
         */
        CheckBox chocolateSyrupCheckBox = (CheckBox) findViewById(R.id.chocolate_syrup_checkbox);
        boolean addChocolateSyrup = chocolateSyrupCheckBox.isChecked();

        /**
         * add whipped cream
         */
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.add_whipped_cream_checkbox);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();
        /**
         * message to test if add whipped cream logic is working
         * Log.v("MainActivity", "Add whipped cream: " + addWhippedCream);
         */

        double price = calculatePrice(addWhippedCream, addChocolateSyrup);
        String priceMessage = createOrderSummary(name, price, addWhippedCream, addChocolateSyrup);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(intent.EXTRA_TEXT, "Order Summary: " + priceMessage); // body of the email
        if (intent.resolveActivity(getPackageManager()) !=null) {
            startActivity(intent);
        }
//        displayMessage(priceMessage);
    }
    /**
     * Calculates the price of the order.
     *
     * @return total price of order
     */
    private double calculatePrice(boolean whippedCreamTopping, boolean chocolateTopping) {
        double basePrice = 5.00;

        if (whippedCreamTopping) {
            basePrice = basePrice + 0.50;
        }

        if (chocolateTopping) {
            basePrice = basePrice + 0.75;
        }
        return quantity * basePrice;
    }

    /**
     * Declares: create order summary
     *
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(String name, double price, boolean whippedCream, boolean chocolate){
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + whippedCream;
        priceMessage += "\nAdd chocolate syrup? " + chocolate;
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
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

}
