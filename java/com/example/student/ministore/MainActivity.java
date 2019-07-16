package com.example.student.ministore;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    int numberOfShoes = 0;
    CheckBox cleaningKitCheckBox;
    CheckBox shoeStringsCheckBox;
    boolean addCleaningKit;
    boolean addShoeStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cleaningKitCheckBox = (CheckBox) findViewById(R.id.cleaning_kit_checkbox);
        shoeStringsCheckBox = (CheckBox) findViewById(R.id.shoe_strings_checkbox);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        String orderMessage = "What's good Wambamkazamazon Customer! Your order details are below:" + "\n";

        if(addCleaningKit) {
            orderMessage += "Added Cleaning Kit ($10)\n" ;
        }

        if(addShoeStrings) {
            orderMessage += "Added White Shoe Strings ($4)\n" ;
        }

        orderMessage += "Pairs of shoes: " + numberOfShoes + "\n"
                + "Total: $" + calculatePrice();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Wambamkazamazon order");
        intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    public void displayPrice(View view) {
        int price = calculatePrice();
        Button orderButton = (Button) findViewById(R.id.order_button);
        orderButton.setText("Order ($" + price + ")");
    }

    public void increase(View view) {
        numberOfShoes++;
        displayQuantity(numberOfShoes);
        displayPrice(null);
    }

    public void decrease(View view) {
        if(numberOfShoes > S0) {
            numberOfShoes--;
        }
        displayQuantity(numberOfShoes);
        displayPrice(null);
    }


    private int calculatePrice() {

        addCleaningKit = cleaningKitCheckBox.isChecked();
        addShoeStrings = shoeStringsCheckBox.isChecked();

        int price = numberOfShoes * 150;

        if (addCleaningKit) {
            price += 10;
        }

        if (addShoeStrings) {
            price += 4;
        }

        return price;
    }

}
