package com.cricket;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MarketActivity extends Activity 
{
	Context ctx;
	Database da;
	MarketAdapter adapter=new MarketAdapter();
	ArrayList<DataClass> names=new ArrayList<DataClass>();
	String[] name={"Sachin","Dravid","Ganguly","Dhoni","Rohit","Raina","Zaheer"};
	public void addNames(){
		
	}
	
	public void removeNames(){
		
	}
	
	public class MarketAdapter extends BaseAdapter
	{
		//Integer[] nos={100,100,100,100,100,100,100};
		//Double[] price={850.0,800.0,750.0,700.0,650.0,600.0,550.0};
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return names.size();
		}

		public Object getItem(int pos) 
		{
			// TODO Auto-generated method stub
			return names.get(pos).getName();
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
			pname.setText(names.get(pos).getName());
			pnos.setText(names.get(pos).getShares()+"");
			pprice.setText(names.get(pos).getPrice()+"");
			return convertView;
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		ctx=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.market);
		da=new Database(ctx);
		da.open();
		da.insertData("Sachin Tendulkar", 100, 100);
		da.insertData("Virendra Shewag", 90, 100);
		da.insertData("Zahir Khan", 80, 100);
		names=da.getData(null);
		ListView lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(adapter);
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
		EditText search=(EditText)findViewById(R.id.editText1);
		search.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Log.i("on", s+"");
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				Log.i("before", s+"");
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Log.i("after", s+"");
				names=da.getData(s+"");
				adapter.notifyDataSetChanged();
			}
		});
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		da.close();
		super.onDestroy();
	}
}
