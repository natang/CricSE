package com.cricket;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class TabsActivity extends TabActivity 
{
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    //Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this,HomeActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec=tabHost.newTabSpec("home").setIndicator("Home").setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, MarketActivity.class);
	    spec=tabHost.newTabSpec("market").setIndicator("Market").setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, PortfolioActivity.class);
	    spec=tabHost.newTabSpec("portfolio").setIndicator("Portfolio").setContent(intent);
	    tabHost.addTab(spec);
	   
	    intent = new Intent().setClass(this, RequestActivity.class);
	    spec=tabHost.newTabSpec("request").setIndicator("Request").setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, BiddingActivity.class);
	    spec=tabHost.newTabSpec("bidding").setIndicator("Bidding").setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}
}