package com.life;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitxty.*;
import com.activity.life_busActivity;


public class bus_checiAdapter extends BaseAdapter {


	private ArrayList<String> data;
	Context mContext;
	private LayoutInflater mInflater;
	private int typeIndex = 0;
	
	public int gettypeIndex() 
	{
		return typeIndex;
	}
	public void settypeIndex(int i) 
	{
		typeIndex =i;
	}
	public bus_checiAdapter(Context context)
	{
		mContext = context;
		data = getData();
		this.mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() 
	{
		return data.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return null;
	}

	@Override
	public long getItemId(int position) 
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		convertView = mInflater.inflate(R.layout.bus_checimenu_item, null);
		
		String area = data.get(position);
		
		((TextView) convertView.findViewById(R.id.id_area)).setText(area);

			View view = new View(mContext);
			LayoutParams param = new LayoutParams(30, 30);
			view.setLayoutParams(param);
			if(position==typeIndex)convertView.findViewById(R.id.ic_checked).setVisibility(View.VISIBLE);
			((LinearLayout)convertView).addView(view, 0);


		return convertView;
	}
	
	
	private ArrayList<String> getData()
	{
		ArrayList<String> data = new ArrayList<String>();
		data.add("���г���");
		data.add("�йش塪����    ��");
		data.add("��    �硪���йش� ");
		data.add("�йش塪��������");
		data.add("�����졪���йش�");
		data.add("��    �硪��������");
		data.add("�����졪����    ��");
		return data;
	}
}
