package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private TextView screen;
    private TextView preview;
    private double num1;
    private double num2;
    private String oper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.screen = findViewById(R.id.pantalla);
        this.preview = findViewById(R.id.preview);
        this.screen.setText(null);

        this.num1 = 0;
        this.num2 = 0;
        this.oper = "";
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                this.screen.append("1");
                break;
            case R.id.two:
                this.screen.append("2");
                break;
            case R.id.three:
                this.screen.append("3");
                break;
            case R.id.four:
                this.screen.append("4");
                break;
            case R.id.five:
                this.screen.append("5");
                break;
            case R.id.six:
                this.screen.append("6");
                break;
            case R.id.seven:
                this.screen.append("7");
                break;
            case R.id.eight:
                this.screen.append("8");
                break;
            case R.id.nine:
                this.screen.append("9");
                break;
            case R.id.zero:
                this.screen.append("0");
                break;
            case R.id.dot:
                this.screen.append(".");
                break;
            case R.id.delete:
                this.delete();
                break;
            case R.id.erase:
                this.erase();
                break;
            case R.id.multiplication:
                this.oper = " x ";
                this.saveValues();
                break;
            case R.id.add:
                this.oper = " + ";
                this.saveValues();
                break;
            case R.id.division:
                this.oper = " / ";
                this.saveValues();
                break;
            case R.id.subtraction:
                this.oper = " - ";
                this.saveValues();
                break;
            case R.id.equals:
                this.result();
                break;
            case R.id.dark:
            case R.id.light:
                this.changeColor();
        }
    }

    private void changeColor() {
        Button light = findViewById(R.id.dark);
        Button dark = findViewById(R.id.light);
        RelativeLayout ly = findViewById(R.id.principal);

        if (dark.getVisibility() == View.INVISIBLE) {
            light.setVisibility(View.INVISIBLE);
            dark.setVisibility(View.VISIBLE);
            ly.setBackgroundColor(Color.BLACK);
            this.preview.setTextColor(Color.WHITE);
            this.screen.setTextColor(Color.WHITE);
        } else {
            light.setVisibility(View.VISIBLE);
            dark.setVisibility(View.INVISIBLE);
            ly.setBackgroundColor(Color.WHITE);
            this.preview.setTextColor(Color.BLACK);
            this.screen.setTextColor(Color.BLACK);
        }
    }

    private void result() {
        DecimalFormat decimalFormat = new DecimalFormat();
        // Do not show fraction with integer results
        decimalFormat.setDecimalSeparatorAlwaysShown(false);
        decimalFormat.setMaximumFractionDigits(5);
        double res = 0;

        try {
            this.num2 = Double.parseDouble(this.getValue());

            switch (oper) {
                case " x ":
                    res = num1 * num2;
                    break;
                case " + ":
                    res = num1 + num2;
                    break;
                case " - ":
                    res = num1 - num2;
                    break;
                case " / ":
                    res = num1 / num2;
                    break;
            }

            if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY || res == Double.NaN) {
                Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                this.delete();
            } else {
                this.screen.setText(decimalFormat.format(res));
                this.preview.setText(null);
            }
        } catch (NumberFormatException e) {
            System.err.println();
        }
    }

    private void erase() {
        try {
            StringBuilder content = new StringBuilder(this.getValue());
            content.deleteCharAt(content.length() - 1);
            this.screen.setText(content);
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println();
        }
    }

    private void delete() {
        this.screen.setText(null);
        this.preview.setText(null);
    }

    private void saveValues() {
        try {
            this.num1 = Double.parseDouble(this.getValue());
            this.setPreview();
        } catch (NumberFormatException e) {
            System.out.println();
        }
    }

    private String getValue() {
        return this.screen.getText().toString();
    }

    private void setPreview() {
        String a = this.getValue() + this.oper;
        this.preview.setText(a);
        this.screen.setText(null);
    }
}