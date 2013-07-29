package com.activity;

import java.util.Map;

import com.bitxty.*;
import com.intnet.FromWebservice;
import com.sqlite.getdatafromSQ;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;

public class PT_dengluActivity extends Activity {
	/** Called when the activity is first created. */
	private Button DLok;
	private EditText username, password;
	private String result;
	private FromWebservice getdata;
	private int PT_ID=0;
	@Override
	public void finish()
	{
		Intent itemintent = new Intent(PT_dengluActivity.this,mainPT.class);
		startActivity(itemintent);
		super.onDestroy();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		getWindow().setBackgroundDrawableResource(R.color.white);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // ע��˳��
		setContentView(R.layout.pingt_main); // ע��˳��
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, // ע��˳��
				R.layout.title);
		
		Intent intent = getIntent();//�õ���һ���ļ������ID��
		Bundle i = intent.getExtras();
		PT_ID = i.getInt("ID");//���õ���ID�Ŵ��ݸ�����num
		DLok = (Button) findViewById(R.id.signin_button);
		username = (EditText) findViewById(R.id.username_edit);
		password = (EditText) findViewById(R.id.password_edit);
		getdata = new FromWebservice(this,PT_ID);
		
		DLok.setOnClickListener(new OnClickListener() {// ��btn_ok��ť���ڵ����������
			@Override
			public void onClick(View v) {
				String strname = new String();
				String strpass = new String();
				strname = username.getText().toString();
				strpass = password.getText().toString();
				String result = getdata.log(strname, strpass);
				if (result.equals("��¼ʧ��")) {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							PT_dengluActivity.this);
					builder.setTitle("�û���¼");
					builder.setPositiveButton("ȷ��", null);
					builder.setIcon(android.R.drawable.ic_dialog_info);
					builder.setMessage("��¼ʧ�ܣ��û������������");
					builder.show();
				} 
				else {
					if (result.equals("�������Ӵ���")) {

						AlertDialog.Builder builder = new AlertDialog.Builder(
								PT_dengluActivity.this);
						builder.setTitle("�û���¼");
						builder.setPositiveButton("ȷ��", null);
						builder.setIcon(android.R.drawable.ic_dialog_info);
						builder.setMessage("�޷����ӷ�����");
						builder.show();
					} 
					else {
						Intent it = new Intent(PT_dengluActivity.this,PT_mainActivity.class);
						Bundle b = new Bundle();
						b.putInt("PT_ID", PT_ID);
						it.putExtra("com.rss.data.RssFeed", b);
						startActivity(it);

					}
				}
			}
		});

	}
}