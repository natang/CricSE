package com.cricket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class MarketActivity extends Activity 
{
	boolean flag=false;
	ListView lv;
	Context ctx;
	String[] name={"Sachin","Dravid","Ganguly","Dhoni","Rohit","Raina","Zaheer"};
	Integer[] nos={100,100,100,100,100,100,100};
	Double[] price={850.0,800.0,750.0,700.0,650.0,600.0,550.0};
	public class MarketAdapter extends BaseAdapter
	{
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return name.length;
		}

		public Object getItem(int pos) 
		{
			// TODO Auto-generated method stub
			return name[pos];
		}

		public long getItemId(int arg0) 
		{
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int pos, View convertView, ViewGroup parent) 
		{
			// TODO Auto-generated method stub
			if(convertView==null)
			{
				LayoutInflater li=getLayoutInflater();
				convertView=li.inflate(R.layout.marketitem, null);
			}
			TextView pname=(TextView)convertView.findViewById(R.id.textView1);
			TextView pnos=(TextView)convertView.findViewById(R.id.textView2);
			TextView pprice=(TextView)convertView.findViewById(R.id.textView3);
			pname.setText(name[pos]);
			pnos.setText(nos[pos]+"");
			pprice.setText(price[pos]+"");
			return convertView;
		}
		
		public Integer getNos(int pos)
		{
			return nos[pos];
		}
		
		public Double getPrice(int pos)
		{
			return price[pos];
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		ctx=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.market);
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(new MarketAdapter());
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) 
			{
				MarketAdapter ma=new MarketAdapter();
				// TODO Auto-generated method stub
				new AlertDialog.Builder(ctx)
				.setTitle("Select a choice for "+ma.getItem(pos))
				.setPositiveButton("Buy", new DialogInterface.OnClickListener() {				
					public void onClick(DialogInterface dialog, int which) 
					{
						// TODO Auto-generated method stub
						
					}
				})
				.setNegativeButton("Watch Bid",new DialogInterface.OnClickListener() {	
					public void onClick(DialogInterface dialog, int which) 
					{
						// TODO Auto-generated method stub
						
					}
				})
				.setNeutralButton("Request",new DialogInterface.OnClickListener() {					
					public void onClick(DialogInterface dialog, int which) 
					{
						// TODO Auto-generated method stub
						
					}
				})
				.show();
			}
		});
		AutoCompleteTextView act=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		ArrayAdapter aa=new ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, name);
		act.setAdapter(aa);
		act.setThreshold(1);
		act.setOnItemClickListener(new OnItemClickListener() {
			class MarketItemAdapter extends BaseAdapter
			{
				public int getCount() 
				{
					// TODO Auto-generated method stub
					return 1;
				}

				public Object getItem(int position) 
				{
					// TODO Auto-generated method stub
					return null;
				}

				public long getItemId(int position) 
				{
					// TODO Auto-generated method stub
					return 0;
				}

				public View getView(int pos, View convertView,
						ViewGroup parent) 
				{
					// TODO Auto-generated method stub
					if(convertView==null)
					{
						LayoutInflater li=getLayoutInflater();
						convertView=li.inflate(R.layout.marketitem, null);
					}
					MarketAdapter ma=new MarketAdapter();
					TextView pname=(TextView)convertView.findViewById(R.id.textView1);
					TextView pnos=(TextView)convertView.findViewById(R.id.textView2);
					TextView pprice=(TextView)convertView.findViewById(R.id.textView3);
					pname.setText(ma.getItem(pos)+"");
					pnos.setText(ma.getNos(pos)+"");
					pprice.setText(ma.getPrice(pos)+"");
					return convertView;
				}
				
			}
			public void onItemClick(AdapterView<?> arg0, View convertView, int pos,
					long arg3) 
			{
				// TODO Auto-generated method stub
				lv.setAdapter(new MarketItemAdapter());
			}	
		});
	}
}
