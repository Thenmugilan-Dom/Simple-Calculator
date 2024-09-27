package com.dom.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Float f1, f2, f;
    Boolean add, sub, div, mul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Creating object for buttons
        Button b1 = findViewById(R.id.b1);
        Button b2 = findViewById(R.id.b2);
        Button b3 = findViewById(R.id.b3);
        Button b4 = findViewById(R.id.b4);
        Button b5 = findViewById(R.id.b5);
        Button b6 = findViewById(R.id.b6);
        Button b7 = findViewById(R.id.b7);
        Button b8 = findViewById(R.id.b8);
        Button b9 = findViewById(R.id.b9);
        Button b0 = findViewById(R.id.b0);
        Button badd = findViewById(R.id.badd);
        Button bsub = findViewById(R.id.bsub);
        Button bmul = findViewById(R.id.bmul);
        Button bdiv = findViewById(R.id.bdiv);
        Button bopen = findViewById(R.id.bopen);
        Button bclose = findViewById(R.id.bclose);
        Button beql = findViewById(R.id.beql);
        Button bdot = findViewById(R.id.bdot);
        Button bc = findViewById(R.id.bc);
        Button bac = findViewById(R.id.bac);

        // Button operation
        EditText et = findViewById(R.id.et);

        // Number buttons
        b1.setOnClickListener(view -> et.setText(et.getText() + "1"));
        b2.setOnClickListener(view -> et.setText(et.getText() + "2"));
        b3.setOnClickListener(view -> et.setText(et.getText() + "3"));
        b4.setOnClickListener(view -> et.setText(et.getText() + "4"));
        b5.setOnClickListener(view -> et.setText(et.getText() + "5"));
        b6.setOnClickListener(view -> et.setText(et.getText() + "6"));
        b7.setOnClickListener(view -> et.setText(et.getText() + "7"));
        b8.setOnClickListener(view -> et.setText(et.getText() + "8"));
        b9.setOnClickListener(view -> et.setText(et.getText() + "9"));
        b0.setOnClickListener(view -> et.setText(et.getText() + "0"));

        // Clear buttons
        bc.setOnClickListener(view -> et.setText(""));
        bac.setOnClickListener(view -> et.setText(""));

        // Special buttons
        bdot.setOnClickListener(view -> et.setText(et.getText() + "."));
        bopen.setOnClickListener(view -> et.setText(et.getText() + "("));
        bclose.setOnClickListener(view -> et.setText(et.getText() + ")"));

        // Operation buttons
        badd.setOnClickListener(view -> prepareOperation(et, "ADD"));
        bsub.setOnClickListener(view -> prepareOperation(et, "SUB"));
        bmul.setOnClickListener(view -> prepareOperation(et, "MUL"));
        bdiv.setOnClickListener(view -> prepareOperation(et, "DIV"));

        // Equal button
        beql.setOnClickListener(view -> calculateResult(et));

        // Window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void prepareOperation(EditText et, String operation) {
        f1 = Float.parseFloat(et.getText().toString());
        et.setText("");
        switch (operation) {
            case "ADD":
                add = true;
                break;
            case "SUB":
                sub = true;
                break;
            case "MUL":
                mul = true;
                break;
            case "DIV":
                div = true;
                break;
        }
    }

    private void calculateResult(EditText et) {
        f2 = Float.parseFloat(et.getText().toString());
        float result = 0;
        boolean operationPerformed = false;

        switch (getCurrentOperation()) {
            case "ADD":
                result = f1 + f2;
                operationPerformed = true;
                break;
            case "SUB":
                result = f1 - f2;
                operationPerformed = true;
                break;
            case "MUL":
                result = f1 * f2;
                operationPerformed = true;
                break;
            case "DIV":
                if (f2 != 0) {
                    result = f1 / f2;
                    operationPerformed = true;
                } else {
                    et.setText("Error"); // Handle division by zero
                }
                break;
            default:
                break;
        }

        if (operationPerformed) {
            et.setText(String.valueOf(result));
            resetOperations();
        }
    }

    private String getCurrentOperation() {
        if (add) return "ADD";
        if (sub) return "SUB";
        if (mul) return "MUL";
        if (div) return "DIV";
        return null;
    }

    private void resetOperations() {
        add = false;
        sub = false;
        mul = false;
        div = false;
    }
}
