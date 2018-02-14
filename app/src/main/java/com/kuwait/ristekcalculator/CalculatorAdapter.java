package com.kuwait.ristekcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by pc on 2/13/2018.
 */

public class CalculatorAdapter extends BaseAdapter {
    Context kons;
    CharSequence[] text;

    public CalculatorAdapter(Context kons, CharSequence[] text) {
        this.kons = kons;
        this.text = text;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final CharSequence written = text[i];
        if(view == null) {
            final LayoutInflater li = LayoutInflater.from( kons );
            view = li.inflate( R.layout.calculator_buttons, null );
        }
        if(written.equals( " " )) {
            view.setVisibility( View.GONE );
        }
        final TextView textView = view.findViewById( R.id.calculatorButton);
        textView.setText( written );
        return view;
    }
}
