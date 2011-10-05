package com.cricket;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Bidding3 extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView textview = new TextView(this);
        textview.setText("This is the Bidding3 tab");
        setContentView(textview);

	}
}
