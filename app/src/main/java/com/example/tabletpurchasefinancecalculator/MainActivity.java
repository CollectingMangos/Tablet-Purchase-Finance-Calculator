package com.example.tabletpurchasefinancecalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView1, textView2, textView3, textView4, textViewResult;
    private EditText editTextName, editTextCustomMessage, editTextSizeChoice;
    private Button buttonCalculate;
    private final double[] tabletPrice = {2500.00,3500.00,4500.00};
    private final double interestRate = 0.8540;
    private final int termMonths = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textViewResult = findViewById(R.id.textViewResult);
        editTextName = findViewById(R.id.editTextName);
        editTextCustomMessage = findViewById(R.id.editTextCustomMessage);
        editTextSizeChoice = findViewById(R.id.editTextSizeChoice);
        buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(v -> calculatePrices());
    }

    private void calculatePrices(){
        String name = editTextName.getText().toString();
        String customMessage = editTextCustomMessage.getText().toString();
        String sizeChoice = editTextSizeChoice.getText().toString().toUpperCase();

        if (name.isEmpty() || customMessage.isEmpty() || sizeChoice.isEmpty()) {
            textViewResult.setText("Please fill in all fields!");
            return;
        }

        double basePrice;
        switch (sizeChoice) {
            case "S":
                basePrice = tabletPrice[0];
                break;
            case "M":
                basePrice = tabletPrice[1];
                break;
            case "L":
                basePrice = tabletPrice[2];
                break;
            default:
                textViewResult.setText("Invalid choice. Please enter S, M, or L.");
                return;
        }

        double totalPrice = basePrice * (1 + interestRate);
        double installmentAmount = totalPrice / termMonths;

        String formattedInstallmentAmount= String.format("%.2f", installmentAmount);
        String formattedTotalPrice = String.format("%.2f", totalPrice);

        String result = "Hello " + name + ".\n" + customMessage + ".\nMonthly Installment: R" + formattedInstallmentAmount + "\nTotal Credit Price: R" + formattedTotalPrice;
        textViewResult.setText(result);
    }
}