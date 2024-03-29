package com.kuwait.ristekcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        GridView gv = findViewById(R.id.buttongrid);
        final TextView res = findViewById(R.id.result);
        final StringBuilder inside = new StringBuilder(res.getText());
        final Stack<Integer> states = new Stack<>();
        states.push(Integer.MIN_VALUE);
        final CharSequence[] text = {
                getText(R.string.button7), getText(R.string.button8), getText(R.string.button9), getText(R.string.buttonDiv),
                getText(R.string.button4), getText(R.string.button5), getText(R.string.button6), getText(R.string.buttonMult),
                getText(R.string.button1), getText(R.string.button2), getText(R.string.button3), getText(R.string.buttonAdd),
                getText(R.string.buttonDot), getText(R.string.button0), getText(R.string.buttonEq), getText(R.string.buttonMin),
                " ", " ", getText(R.string.buttonClear), getText(R.string.buttonDel),
        };
        gv.setAdapter(new CalculatorAdapter(this, text));
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 19:
                        inside.setLength(0);
                        inside.append(getString(R.string.button0));
                        states.add(R.string.buttonClear);
                        res.setText(inside);
                        return true;
                }
                return false;
            }
        });
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (inside.toString().equals("SYNTAX ERROR")) {
                    inside.setLength(0);
                }
                switch (i) {
                    //Clear
                    case 18:
                        inside.setLength(0);
                        inside.append(getString(R.string.button0));
                        states.add(R.string.buttonClear);
                        res.setText(inside);
                        break;
                    //Equal
                    case 14:
                        String hasil = calculate((String) res.getText());
                        inside.setLength(0);
                        inside.append(hasil);
                        states.add(R.string.buttonEq);
                        res.setText(hasil);
                        break;
                    //Delete
                    case 19:
                        if (states.peek() == R.string.buttonEq) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button0));
                        } else {
                            if (inside.length() != 0) inside.deleteCharAt(inside.length() - 1);
                            if (inside.length() == 0) inside.append(getString(R.string.button0));

                        }
                        states.push(R.string.buttonDel);
                        res.setText(inside);
                        break;
                    //0
                    case 13:
                        if (states.peek() == R.string.buttonEq) {
                            inside.setLength(0);
                        }
                        if (!res.getText().equals("0")) inside.append(getString(R.string.button0));
                        states.push(R.string.button0);
                        res.setText(inside);
                        break;
                    //1
                    case 8:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button1));
                        } else inside.append(getString(R.string.button1));
                        states.push(R.string.button1);
                        res.setText(inside);
                        break;
                    //2
                    case 9:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button2));
                        } else inside.append(getString(R.string.button2));
                        states.push(R.string.button2);
                        res.setText(inside);
                        break;
                    //3
                    case 10:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button3));
                        } else inside.append(getString(R.string.button3));
                        states.push(R.string.button3);
                        res.setText(inside);
                        break;
                    //4
                    case 4:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button4));
                        } else inside.append(getString(R.string.button4));
                        states.push(R.string.button4);
                        res.setText(inside);
                        break;
                    //5
                    case 5:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button5));
                        } else inside.append(getString(R.string.button5));
                        states.push(R.string.button5);
                        res.setText(inside);
                        break;
                    //6
                    case 6:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button6));
                        } else inside.append(getString(R.string.button6));
                        states.push(R.string.button6);
                        res.setText(inside);
                        break;
                    //7
                    case 0:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button7));
                        } else inside.append(getString(R.string.button7));
                        states.push(R.string.button7);
                        res.setText(inside);
                        break;
                    //8
                    case 1:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button8));
                        } else inside.append(getString(R.string.button8));
                        states.push(R.string.button8);
                        res.setText(inside);
                        break;
                    //9
                    case 2:
                        if (states.peek() == R.string.buttonEq) inside.setLength(0);
                        if (res.getText().equals("0")) {
                            inside.setLength(0);
                            inside.append(getString(R.string.button9));
                        } else inside.append(getString(R.string.button9));
                        states.push(R.string.button9);
                        res.setText(inside);
                        break;
                    //Dot
                    case 12:
                        if (states.peek() == R.string.buttonDot) inside.setLength(0);
                        if (states.peek() == R.string.stateOpp)
                            inside.setCharAt(inside.length() - 1, getString(R.string.buttonDot).charAt(0));
                        else {
                            inside.append(getString(R.string.buttonDot));
                        }
                        res.setText(inside);
                        states.push(R.string.stateOpp);
                        break;
                    //Mult
                    case 7:
                        if (states.peek() == R.string.stateOpp)
                            inside.setCharAt(inside.length() - 1, getString(R.string.buttonMult).charAt(0));
                        else
                            inside.append(getString(R.string.buttonMult));

                        res.setText(inside);
                        states.push(R.string.stateOpp);
                        break;
                    //Div
                    case 3:
                        if (states.peek() == R.string.stateOpp)
                            inside.setCharAt(inside.length() - 1, getString(R.string.buttonDiv).charAt(0));
                        else
                            inside.append(getString(R.string.buttonDiv));
                        res.setText(inside);
                        states.push(R.string.stateOpp);
                        break;
                    //Add
                    case 11:
                        if (states.peek() == R.string.stateOpp)
                            inside.setCharAt(inside.length() - 1, getString(R.string.buttonAdd).charAt(0));
                        else
                            inside.append(getString(R.string.buttonAdd));
                        res.setText(inside);
                        states.push(R.string.stateOpp);
                        break;
                    //Min
                    case 15:
                        if (states.peek() == R.string.stateOpp)
                            inside.setCharAt(inside.length() - 1, getString(R.string.buttonMin).charAt(0));
                        else
                            inside.append(getString(R.string.buttonMin));
                        res.setText(inside);
                        states.push(R.string.stateOpp);
                        break;
                }
            }
        });
    }

    public String calculate(String all) {
        if (!Character.isDigit(all.charAt(all.length() - 1))) {
            return "SYNTAX ERROR";
        }
        String[] numbers = all.split("[*/+-]");
        try {
            if (numbers.length < 2) {
                return all;
            } else {
                String[] operators = all.replaceAll("\\d", "").split("");
                ArrayList<String> nums = new ArrayList<>(Arrays.asList(numbers));
                ArrayList<String> ops = new ArrayList<>(Arrays.asList(operators));
                ops.remove(".");
                ops.remove("");
                int indexMult = ops.indexOf("*");
                int indexDiv = ops.indexOf("/");
                int indexAdd = ops.indexOf("+");
                int indexSub = ops.indexOf("-");

                if (indexMult != -1 && (indexMult < indexDiv || indexDiv == -1)) {
                    double resMult = Double.parseDouble(nums.get(indexMult)) * Double.parseDouble(nums.get(indexMult + 1));
                    String inserted = Double.toString(resMult);
                    String replaced = nums.get(indexMult) + "*" + nums.get(indexMult + 1);
                    all = all.replace(replaced, inserted);
                    return calculate(all);
                }

                if (indexDiv != -1 && (indexDiv < indexMult || indexMult == -1)) {
                    double resDiv = Double.parseDouble(nums.get(indexDiv)) / Double.parseDouble(nums.get(indexDiv + 1));
                    String inserted = Double.toString(resDiv);
                    if (inserted.split("\\.")[1].length() > 8)
                        inserted = String.format(Locale.ENGLISH, "%.8f", resDiv);
                    String replaced = nums.get(indexDiv) + "/" + nums.get(indexDiv + 1);
                    all = all.replace(replaced, inserted);
                    return calculate(all);
                }

                if (indexAdd != -1) {
                    double resAdd = Double.parseDouble(nums.get(indexAdd)) + Double.parseDouble(nums.get(indexAdd + 1));
                    String inserted = Double.toString(resAdd);
                    String replaced = nums.get(indexAdd) + "+" + nums.get(indexAdd + 1);
                    all = all.replace(replaced, inserted);
                    return calculate(all);
                }

                if (indexSub != -1) {
                    double resSub = Double.parseDouble(nums.get(indexSub)) - Double.parseDouble(nums.get(indexSub + 1));
                    String inserted = Double.toString(resSub);
                    String replaced = nums.get(indexSub) + "-" + nums.get(indexSub + 1);
                    all = all.replace(replaced, inserted);
                    return calculate(all);
                }
                return "";
            }
        } catch (Exception e) {
            return "SYNTAX ERROR";
        }

    }
}
