package com.moreinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bitxty.R;
import com.sqlite.getdatafromSQ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class sugesstion extends Activity{
	private View view;
	private getdatafromSQ data=null;
	private List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
	private EditText editText;
    private Button submit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawableResource(R.color.white);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // ע��˳��
		view = this.getLayoutInflater().inflate(R.layout.more_suggesstion, null);
		setContentView(view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,      // ע��˳��
                R.layout.title);
		data =  ((getdatafromSQ)getApplicationContext());  
		list = data.getRSSlist(); 
		 data.addActivity(this);
		 editText = (EditText)findViewById(R.id.sugesstion_edit);
		 submit = (Button)findViewById(R.id.suggest_submit);
		 submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				//�����ı���ʽ
				emailIntent.setType("text/plain");
				//���öԷ��ʼ���ַ
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, "aietxm@hotmail.com");
				//���ñ�������
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"�û�����");
				//�����ʼ��ı�����
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,editText.getText().toString());
				startActivity(Intent.createChooser(emailIntent,"Choose Email Client"));
			}
		});
	}
	

}
