package com.pulllist;

import com.bitxty.R;

import android.R.integer;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;
import android.widget.TextView;

public class PT_menu extends View{
	public TextView name;
	public TextView num;
	public TextView shenf;
	public static boolean isMenuOpned = false;// �˵��Ƿ��
	
	private Scroller scroller;// ����
	private int distance;// ��������
	
	public PT_menu(Context context,String pt_name,String pt_num,String pt_shenf,String url) {
		super(context );
		// TODO Auto-generated constructor stub
		//view = this.getLayoutInflater().inflate(R.layout.life_phone1, null);
		this.findViewById(R.id.menu);
		//TypedArray ta=context.obtainStyledAttributes(R.layout.menu);  
		name = (TextView)findViewById(R.id.pt_name);
		num = (TextView)findViewById(R.id.pt_num);
		shenf = (TextView)findViewById(R.id.pt_shenf);
		
		
		
	}
	
	public PT_menu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public PT_menu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
//	@Override  
//    protected void onDraw(Canvas canvas) {  
//        super.onDraw(canvas);  
//        mPaint = new Paint();  
//        mPaint.setColor(Color.BLUE);  
//        // FILL���, STROKE���,FILL_AND_STROKE�������  
//        mPaint.setStyle(Style.FILL);  
//        canvas.drawRect(new Rect(10, 10, 100, 100), mPaint);// ��һ������  
//  
//        mPaint.setColor(Color.GREEN);  
//        mPaint.setTextSize(35.0f);  
//        canvas.drawText(mText, 10, 60, mPaint);  
//    }  
//  
	public void setusers(String pt_name,String pt_num,String pt_shenf,String url)
	{
		pt_name ="������"+pt_name;
		pt_num="ѧ�ţ�"+pt_num;
		pt_shenf="��ݣ�"+pt_shenf;
		name.setText(pt_name);
		num.setText(pt_num);
		shenf.setText(pt_shenf);
	}

}
