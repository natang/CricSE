package com.cricket;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class PortfolioActivity extends Activity 
{
	boolean flag=false;
	ListView lv;
	Context ctx;
	ArrayList<Portfolio> ap=new ArrayList<Portfolio>();
	public class MarketAdapter extends BaseAdapter
	{
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return ap.size();
		}

		public Object getItem(int pos) 
		{
			// TODO Auto-generated method stub
			return (ap.get(pos)).getShareName();
		}

		public long getItemId(int arg0) 
		{
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int pos, View convertView, ViewGroup parent) 
		{
			// TODO Auto-generated method stub
			LayoutInflater li=getLayoutInflater();
			Portfolio p=ap.get(pos);
			convertView=li.inflate(R.layout.portfolioitem, null);
			TextView s_id=(TextView)convertView.findViewById(R.id.textView1);
			TextView share_name=(TextView)convertView.findViewById(R.id.textView2);
			TextView share_nos=(TextView)convertView.findViewById(R.id.textView3);
			TextView price=(TextView)convertView.findViewById(R.id.textView4);
			TextView percent=(TextView)convertView.findViewById(R.id.textView5);
			s_id.setText(p.getId()+"");
			share_name.setText(p.getShareName());
			share_nos.setText(p.getNoOfShares()+"");
			return convertView;
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		ctx=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.portfolio);
		lv=(ListView)findViewById(R.id.listView1);
		MarketAdapter pa=new MarketAdapter();
		lv.setAdapter(pa);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) 
			{
				MarketAdapter ma=new MarketAdapter();
				// TODO Auto-generated method stub
				new AlertDialog.Builder(ctx)
				.setTitle("Select a choice for "+ma.getItem(pos))
				.setPositiveButton("Sell", new DialogInterface.OnClickListener() {				
					public void onClick(DialogInterface dialog, int which) 
					{
						// TODO Auto-generated method stub
						
					}
				})
				.setNegativeButton("Bid",new DialogInterface.OnClickListener() {	
					public void onClick(DialogInterface dialog, int which) 
					{
						// TODO Auto-generated method stub
						
					}
				})
				.show();
			}
		});
		DatabaseAccess da=new DatabaseAccess(ctx);
		da.open();
		if(da.addEntry(1, "Sachin", 100))
			{}
		else
		{
			Toast.makeText(ctx, "ERROR", Toast.LENGTH_SHORT).show();
		}
		ap=da.getData();
		da.close();
		}
}
