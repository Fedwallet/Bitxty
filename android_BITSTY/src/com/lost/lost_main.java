package com.lost;
import java.io.IOException;
import java.util.ArrayList;  
import java.util.Date;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;


import com.club.DynamicListView;
import com.club.club_main;
import com.club.club_publish;
import com.club.DynamicListView.DynamicListViewListener;
import com.sqlite.getdatafromSQ;
import com.bitxty.R;
//import com.example.welcome.MainActivity.MyThread;

//import com.sqlite.getdatafromSQ;


import android.annotation.SuppressLint;  
import android.app.Activity;  
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;  
import android.os.Handler;  
import android.os.Looper;
import android.os.Message;  
import android.util.Log;  
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;  
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;  
import android.widget.AdapterView.OnItemClickListener;  
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;  
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;   


@SuppressWarnings("unused")
@SuppressLint("HandlerLeak")

public class lost_main extends Activity implements DynamicListViewListener {
	private static final String[] m={"ʧ������ƽ̨","��ʧ��Ʒ","����Ʒ"};
	private static final String[] n={"�������","Ǯ��","��Ƭ ","���Ӳ�Ʒ","������Ʒ","����"};
	private Spinner spinner;
	private Spinner spinner2;
	private String [] str={"mold","name","state","place","time"};
	DynamicListView listView;
	ImageView button;
	private HashMap<String, Object> map = new HashMap<String, Object>(); 
	private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();	
	private ArrayAdapter<String> adapter1; 
    private ArrayAdapter<String> adapter2;
    private String flag="0";
	private String  type;
	private String type1;
	private String goodsresult;
	private String lost_id;
	private String data;
	private String lost=null;
	private View view;
	 private getdatafromSQ data2=null;
		private List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();

    SimpleAdapter adapter;  
  
    
		Handler handler = new Handler() {  
           @Override  
        public void handleMessage(Message msg) {  
            if (msg.what == 0) {  
                adapter.notifyDataSetChanged();  
                listView.doneRefresh();  
                Toast.makeText(lost_main.this, "�¼���"+msg.arg1+"������!",0).show();  
            } else if (msg.what == 1) {  
               adapter.notifyDataSetChanged();  
                listView.doneMore();  
            } else {  
                super.handleMessage(msg);  
            }  
        }  
    };  

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 getWindow().setBackgroundDrawableResource(R.color.white);
			requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // ע��˳��
			view= this.getLayoutInflater().inflate(R.layout.lost_main, null);
			setContentView(view);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,      // ע��˳��
	                R.layout.title);
		
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(adapter1);
        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);         
        //���������б�ķ��  
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);            
        //��adapter 1��ӵ�spinner��  
        spinner.setAdapter(adapter1);          
        //����¼�Spinner�¼�����    
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());            
        //����Ĭ��ֵ  
        spinner.setVisibility(View.VISIBLE); 
        
        
        spinner2 = (Spinner)findViewById(R.id.spinner2);	
		spinner2.setAdapter(adapter2);
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,n);         
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);            
        spinner2.setAdapter(adapter2);          
        spinner2.setOnItemSelectedListener(new SpinnerSelectedListener1());            
        spinner2.setVisibility(View.VISIBLE);
        
        listView = (DynamicListView)findViewById(R.id.list_list);
        button=(ImageView)findViewById(R.id.ib1);
//        map.put("mold", "mold");
//        map.put("name", "name");
//        map.put("state","state");
//        map.put("place","place");
//        map.put("time","time");
//        for (int i = 1; i < 10; ++i) {  
//	    	listItem.add(map);
         button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				data2 =  ((getdatafromSQ)getApplicationContext());  
  				data2.updatalog();
  				list = data2.getlog();
  				Map<String , Object> map = new HashMap<String,Object>();
  				Intent intent =new Intent();
  				map=list.get(0);
  				int islog = (Integer) map.get("islog");
  				if(islog==1){
  				
  				intent.putExtra("user_id", (String)map.get("user_id"));
  				intent.setClass(lost_main.this, lost_announce.class);
  				startActivity(intent);
  				}
  				else if(islog==0){
  					Toast.makeText(lost_main.this, "���¼�󷢲�", 0).show();
  					intent.setClass(lost_main.this, com.moreinfo.login.class);
  					startActivity(intent);
  				}
				}
				
	    });   		       
	         adapter = new SimpleAdapter(this, listItem, R.layout.lost_item, str, new int[]{R.id.mold,R.id.name,R.id.state,R.id.place,R.id.time});
             listView.setAdapter(adapter);  
             listView.setOnItemClickListener(new OnItemClickListener() {

			     @Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			    	 
				String item =arg0.getItemAtPosition(arg2).toString();
				System.out.println(item);
				String [] str =item.split(",");
				System.out.println(str);				
				String [] str2=str[4].split("=");
				//System.out.println(str2);
				Intent intent=new Intent();	
				intent.putExtra("lost_id", str2[1].toString());		
				System.out.println("lost_id##########" + str2[1].toString());
				intent.setClass(lost_main.this, lost_expand.class);
				startActivity(intent);	
			}  
        }); 
                
          listView.setDoMoreWhenBottom(false);    // �������Ͷ˵�ʱ���Լ����ظ���  
          listView.setOnRefreshListener(this);  
          listView.setOnMoreListener(this);  
	}

	public void pulllist(){
		String method="showlist";
		String strspinner1;
		String  strspinner;
	
		String serviceurl="http://10.1.151.26/ydzw.asmx";
		SoapObject request = new SoapObject("http://tempuri.org/","GetLostList");
		if(flag.equals("0")==true){
			request.addProperty("lost_id", lost.toString());	
		}
		if(flag.equals("1")==true)
		{request.addProperty("lost_id", "");} 
		request.addProperty("flag", flag.toString()); //sessionID
		if(type.equals("ʧ������ƽ̨")==true)
		   {strspinner1="";
		request.addProperty("lost_flag", strspinner1.toString());}
		else if(type.equals("��ʧ��Ʒ")==true)
		{   strspinner1="0";
			request.addProperty("lost_flag", strspinner1.toString());}
		else if(type.equals("����Ʒ")==true)
		{
			strspinner1="1";
			request.addProperty("lost_flag", strspinner1.toString());
		}
		if(type1.equals("�������")!=true){
			request.addProperty("lost_tag",type1.toString());
		}
		else{
			request.addProperty("lost_tag","");
		}
		request.addProperty("lost_state","");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);//����WebService������SOAP������Ϣ  ����SoapSerializationEnvelope���󣬲�ָ��WebService�İ汾 
        envelope.bodyOut=request;                                    // ����bodyOut����
        envelope.dotNet=true;                                       //ָ��webService������
        HttpTransportSE http = new HttpTransportSE(serviceurl);    //ָ��HttpTransportSE 
    	(new MarshalBase64()).register(envelope);
        try {
        	http.call(null,envelope);                           //����webService������ 
           SoapObject result = (SoapObject)envelope.bodyIn;   //��÷���ֵ     getResponse()
           SoapObject newlist= (SoapObject)((SoapObject)result.getProperty(0)).getProperty(0);
            String error=(((SoapObject)newlist.getProperty(0)).getProperty(0)).toString();
            System.out.println(error);
            System.out.println((SoapObject)newlist.getProperty(0));
            if(error.equals("anyType{}")==true){
            	HashMap<String, Object> map = null;
            	for(int i=1;i<newlist.getPropertyCount();i++){           //����
                	  SoapObject newlist2=(SoapObject)newlist.getProperty(i);
                	  System.out.println(newlist2.getPropertyCount());
                	   map = new HashMap<String, Object>();
                	 for(int j=0;j<newlist2.getPropertyCount();j++){
                		 if(j==0){
                			 if(i==newlist.getPropertyCount()-1){
                				lost=(newlist2.getProperty(j)).toString();
                				System.out.println(lost);
                			 }
                			 map.put("lost_id", newlist2.getProperty(j).toString());
                			System.out.println("1:"+newlist2.getProperty(j).toString());
                		 }
                		 else{
                			 if(j==1){
                                      if(newlist2.getProperty(j).toString().equals("0"))
                                      {
                                    	  strspinner1="ʧ��"; 
                                    	  System.out.println(strspinner1);
                    			          map.put("mold", strspinner1.toString());   //ʹ��getProperty()�������WebService�������ؽ��
                    		          }
                                      else if(newlist2.getProperty(j).toString().equals("1")){
                                    	  strspinner1="����";                                           
                             			  map.put("mold", strspinner1.toString()); 
                                      }
                			        }
                    		 else if(j==2){
                    			 map.put("time", (newlist2.getProperty(j)).toString());
                    		 }
                    		 else if(j==3){ 
                    			    if(newlist2.getProperty(j).toString().equals("0"))
                                     {
                               	       strspinner="δ���"; 
                               	       System.out.println(strspinner);
               			               map.put("state", strspinner.toString());   //ʹ��getProperty()�������WebService�������ؽ��
               		                 }
                                    else if(newlist2.getProperty(j).toString().equals("1")){
                               	      strspinner="�ѽ��";                                           
                        			  map.put("state", strspinner.toString()); 
                                 }	 
                    		 }
                    		 else if(j==4){
                    			 map.put("place",(newlist2.getProperty(j)).toString());
                    		 }
                    		 else if(j==5){
                    			 map.put("name",(newlist2.getProperty(j)).toString());
                    		 }	 
                    		 
                    	}
                	}
                	 listItem.add(map);	 
            	}
            }
           
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	


class SpinnerSelectedListener implements OnItemSelectedListener{  
   	  
         public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                 long arg3) {  
         	type=m[arg2].toString();
             System.out.println(type);
             onRefreshOrMore(listView,true);
           
         }  
   
         public void onNothingSelected(AdapterView<?> arg0) {  
         }  
     } 
class SpinnerSelectedListener1 implements OnItemSelectedListener{  
     	  
         public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
                 long arg3) {  
         	type1=n[arg2].toString();
             System.out.println(type1);
             onRefreshOrMore(listView,true);
         }  
   
         public void onNothingSelected(AdapterView<?> arg0) {  
         }  
     }

     @Override  
     public boolean onRefreshOrMore(DynamicListView dynamicListView, boolean isRefresh) {  
         if (isRefresh) { 
         	listItem .clear();
             new Thread(new Runnable() {  
                 @Override  
                 public void run() {  
                     // ˢ��
                 	flag="1"; 
                     pulllist();   
                     Message message = new Message();  
                     message.what = 0;  
                     message.arg1 = listItem.size();  
                     handler.sendMessage(message);
                 }  
             }).start();  
         } else {  
             new Thread(new Runnable() {  
                 @Override  
                 public void run() {
                 	flag="0"; 
                     pulllist(); 
                     // ���ظ���  
                     Message message = new Message();  
                     message.what = 1;  
                     message.arg1 = listItem.size();  
                     handler.sendMessage(message);
                 }  
             }).start();  
         }  
         return false;
     } 
 } 