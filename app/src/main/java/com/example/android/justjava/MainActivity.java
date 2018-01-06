package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.justjava.R;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1 ;

    /**
     * This method increment the quantity by one.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this,"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method decrement the quantity by one.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     //* @param quantity is the number of cups of coffee ordered
    *
    private void calculatePrice(int quantity) {
        int price = quantity * 5;
    }
    **/

    private int calculatePrice () {

        if (whippedCreamIsChecked() && chocolateIsChecked()) {
            int price = quantity * (5 + 1 + 2);
            return price;

        } else if (whippedCreamIsChecked()) {
            int price = quantity * ( 5 + 1) ;
            return price;

        } else if (chocolateIsChecked()) {
            int price = quantity * (5 + 2) ;
            return price;
        } else {
            int price = quantity * 5 ;
            return price;
        }
    }

    private boolean whippedCreamIsChecked () {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean whippedCreamIsChecked = whippedCream.isChecked();
        return whippedCreamIsChecked;
    }

    private boolean chocolateIsChecked () {
        CheckBox chocolateTopping = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean chocolateToppingIsChecked = chocolateTopping.isChecked();
        return chocolateToppingIsChecked;
    }

    private String fullName () {
        EditText fullName = (EditText) findViewById(R.id.type_your_name);
        String saveFullName = fullName.getText().toString();
        return saveFullName;
    }

    private String createOrderSummary () {
        int price = calculatePrice();
        String orderSummary = "Name : "  + fullName() + "\n" + "Add whipped cream : " + whippedCreamIsChecked() + "\n" + "Add chocolate : " + chocolateIsChecked() + "\n" + "Quantity : " + quantity + "\n" + "Total : " + price + "\n" + "Thank you!";
        return orderSummary;
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        String priceMessage = createOrderSummary();
//        displayMessage(priceMessage);
        Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
        sendEmail.setData(Uri.parse("mailto:"));
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order By : " + fullName());
        sendEmail.putExtra(sendEmail.EXTRA_TEXT, createOrderSummary());
        if (sendEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendEmail);
        }
    }

    private void displayQuantity(int numberOfQuantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfQuantity);
    }

    /**
     * This method displays the given price on the screen.

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.  );
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
     */

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}