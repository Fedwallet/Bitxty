package com.bitxty;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pulllist.getintnetstate;
import com.schoolnews.updatalist;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import com.sqlite.getdatafromSQ;
import com.schoolnews.updatalist;;
public class updataservice extends Service{

	 CommandReceiver cmdReceiver;
     boolean flag;
     private getdatafromSQ SQdata= null;
     updatalist updatalist = null;
 	private getdatafromSQ data=null;
     @Override
     public void onCreate() {//��дonCreate����
             flag = true;
             cmdReceiver = new CommandReceiver();
             super.onCreate();
             updatalist = new updatalist(this);
             data =  ((getdatafromSQ)getApplicationContext());  
     }
     @Override
     public IBinder onBind(Intent intent) {//��дonBind����
             // TODO Auto-generated method stub
             return null;
     }
     @Override
     public int onStartCommand(Intent intent, int flags, int startId) {//��дonStartCommand����
             IntentFilter filter = new IntentFilter();//����IntentFilter����
             filter.addAction("com.pulllist.updataservice");
             registerReceiver(cmdReceiver, filter);//ע��Broadcast Receiver
             doJob();//���÷��������߳�
             return super.onStartCommand(intent, flags, startId);
     }
     //������
     public void doJob(){
             new Thread(){
                     public void run(){
                             while(flag){
                            	 update();
                                 try{//˯��һ��ʱ��
                                         Thread.sleep(20000);
                                 }
                                 catch(Exception e){
                                         e.printStackTrace();
                                 }
                             }                                
                     }
                     
             }.start();
     }        
     private class CommandReceiver extends BroadcastReceiver{//�̳���BroadcastReceiver������
             @Override
             public void onReceive(Context context, Intent intent) {//��дonReceive����
                     int cmd = intent.getIntExtra("cmd", -1);//��ȡExtra��Ϣ
                     if(cmd == 1){//�����������Ϣ��ֹͣ����                                
                             flag = false;//ֹͣ�߳�
                             stopSelf();//ֹͣ����
                     }if(cmd==2){
                    	 
                     }
             }                
     }
     @Override
     public void onDestroy() {//��дonDestroy����
             this.unregisterReceiver(cmdReceiver);//ȡ��ע���CommandReceiver
             super.onDestroy();
     }        
     public void update()
     { 
		getintnetstate net = new getintnetstate();

		if (net.detect(this)) {
			updatalist.updatenews();
		}

	}

}
