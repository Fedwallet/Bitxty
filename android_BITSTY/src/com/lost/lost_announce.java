package com.lost;


import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import com.bitxty.R;
//import com.example.Announce;
//import com.example.welcome.R.layout;
//import com.example.welcome.log.MyHandler;
//import com.example.welcome.log.MyThread;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class lost_announce extends Activity{

	Spinner spinner3;
	Spinner spinner4;	
	private String type;
	private String type1;
	private static final String[] m={"ʧ������","��ʧ��Ʒ","����Ʒ"};
	private static final String[] n={"����","Ǯ��","��Ƭ","������Ʒ","������Ʒ","����"};
	private ArrayAdapter<String> adapter;  
	private ArrayAdapter<String> adapter1; 
//	private String user_id;
	private TextView name;
	private TextView phone;
	private TextView place;
	private TextView content;
	private String [] goodsresult={"","","","","","",""};
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 getWindow().setBackgroundDrawableResource(R.color.white);
			requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // ע��˳��
			view= this.getLayoutInflater().inflate(R.layout.lost_announce, null);
			setContentView(view);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,      // ע��˳��
	                R.layout.title);
		name=(EditText)findViewById(R.id.name);
		phone=(EditText)findViewById(R.id.phone);
		place=(EditText)findViewById(R.id.place);	
		content=(EditText)findViewById(R.id.content);
		
		spinner3 = (Spinner) findViewById(R.id.state);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);         
	        //���������б�ķ��  
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  	          
	        //��adapter ��ӵ�spinner��  
	    spinner3.setAdapter(adapter);  	          
	        //����¼�Spinner�¼�����    
	    spinner3.setOnItemSelectedListener(new SpinnerSelectedListener());  	          
	        //����Ĭ��ֵ  
	    spinner3.setVisibility(View.VISIBLE);
	    
		spinner4 = (Spinner) findViewById(R.id.leixing);
	    adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,n);         
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  	           
        spinner4.setAdapter(adapter1);  	              
        spinner4.setOnItemSelectedListener(new SpinnerSelectedListener1());  	          
        spinner4.setVisibility(View.VISIBLE); 	

	
		Button btn_announce = (Button)findViewById(R.id.btn_announce);
		 name=(EditText)findViewById(R.id.name);
		 place=(EditText)findViewById(R.id.place);
		 content=(EditText)findViewById(R.id.content);
	     phone=(EditText)findViewById(R.id.phone);
	     spinner3=(Spinner)findViewById(R.id.state);
	     spinner4=(Spinner)findViewById(R.id.leixing);
		btn_announce.setOnClickListener(new View.OnClickListener() {
			@Override
		public void onClick(View arg0){
				if(name.getText().toString().equals("")){
					name.setError(getString(R.string.error_field_required));
					Toast.makeText(lost_announce.this, "����д������Ϣ",0).show();
					}
				else if(place.getText().toString().equals(""))
				    {
					place.setError(getString(R.string.error_field_required));
					Toast.makeText(lost_announce.this, "����д������Ϣ",0).show();
					}
				else if(phone.getText().toString().equals(""))
				    {
					phone.setError(getString(R.string.error_field_required));
					Toast.makeText(lost_announce.this, "����д������Ϣ",0).show();
					}
				else if(content.getText().toString().equals(""))
				    {
					content.setError(getString(R.string.error_field_required));
					Toast.makeText(lost_announce.this, "����д������Ϣ",0).show();
					}
			
				
				else{
					new MyThread().start();
				    }
				}
		});
	}

    class SpinnerSelectedListener implements OnItemSelectedListener{  
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
            long arg3) {  
    	type=m[arg2].toString();
        System.out.println(type);              
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	} 
}
    class SpinnerSelectedListener1 implements OnItemSelectedListener{  
    	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                long arg3) { 
    		type1=n[arg2].toString();
            System.out.println(type1);

           }
    	@Override
    	public void onNothingSelected(AdapterView<?> arg0) {
    		// TODO Auto-generated method stub    		
    	} 
    }

    class MyHandler extends Handler{
		public MyHandler(Looper looper){
			super(looper);
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			String [] str=(String [])msg.obj;
			for(int i=0;i<2;i++)
			{System.out.println(str);}
			  if(str[0].toString().equals("anyType{}")==true){
			     	System.out.println(str[0]);
			        Toast.makeText(getApplicationContext(),"�����ɹ�",Toast.LENGTH_SHORT).show();
			    	 Intent intent =new Intent();
			    	 intent.setClass(lost_announce.this, lost_main.class);
			    	 startActivity(intent);
			    	 finish();
			    }
			    else{
			    	Toast.makeText(getApplicationContext(),str[0],Toast.LENGTH_SHORT).show();
			    }
		}
	}
class MyThread extends Thread{
	public void run(){
		Bundle bundle = getIntent().getExtras();
		String userid=bundle.getString("user_id");
	//	System.out.println(userid);
		String strspinner1;
		if(type.toString().equals("��ʧ��Ʒ"))
		{
			 strspinner1="0";
		}
		else
		{
			 strspinner1="1";
		}
		
		String strspinner2=type1.toString();
		String strname=name.getText().toString();
		String strplace=place.getText().toString();
		String strphone=phone.getText().toString();
		String strdetail=content.getText().toString();
		String serviceurl="http://10.1.151.26/ydzw.asmx";
		SoapObject request = new SoapObject("http://tempuri.org/","InsertLost");
		request.addProperty("lost_flag", strspinner1.toString());
		request.addProperty("lost_tag",strspinner2.toString());
		request.addProperty("goods_name", strname.toString()); 
		request.addProperty("goods_address", strplace.toString()); 
		request.addProperty("goods_contact", strphone.toString()); 
		request.addProperty("goods_detail",strdetail.toString());
		request.addProperty("user_id",userid.toString());
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.bodyOut=request;
        envelope.dotNet=true;
        HttpTransportSE http = new HttpTransportSE(serviceurl);
    	(new MarshalBase64()).register(envelope);
    	try {
        	http.call(null,envelope);
            SoapObject sb = (SoapObject)envelope.bodyIn;
            SoapObject contactlist1 = (SoapObject)((SoapObject)( (SoapObject)sb.getProperty(0)).getProperty(0)).getProperty(0);
        	System.out.println(contactlist1);
            for(int i=0;i<contactlist1.getPropertyCount();i++){
            	System.out.println(contactlist1.getProperty(i).toString());
            	System.out.println((contactlist1.getProperty(i)).toString());
            	System.out.println((contactlist1.getProperty(i)).toString());
            	System.out.println((contactlist1.getProperty(i)).toString());
              	System.out.println((contactlist1.getProperty(i)).toString());
             	System.out.println((contactlist1.getProperty(i)).toString());
            	goodsresult[i]=(contactlist1.getProperty(i)).toString();
            	System.out.println(goodsresult[i]);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Looper looper = Looper.getMainLooper();
		MyHandler handler = new MyHandler(looper);
		Message message = handler.obtainMessage(1,2,3,goodsresult);
		handler.sendMessage(message);
	
	}
	
}	
	

}


	



