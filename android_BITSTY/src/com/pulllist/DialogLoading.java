package com.pulllist;

import com.bitxty.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DialogLoading  extends Dialog {
	 Context context;  
	  
	    public DialogLoading(Context context, int theme) {  
	        super(context, theme);  
	        this.context = context;  
	        ini();  
	    }  
	  
	    public DialogLoading(Context context) {  
	        super(context);  
	        this.context = context;  
	        ini();  
	    }  
	  
	    void ini() {  
	        /** 
	         * "������"���֣��˲��ֱ���ӵ�ListView��Footer�С� 
	         */  
	        LinearLayout contentView = new LinearLayout(context);  
	        contentView.setMinimumHeight(60);  
	        contentView.setGravity(Gravity.CENTER);  
	        contentView.setOrientation(LinearLayout.HORIZONTAL);  
	  
	        /** 
	         * ��"������"���������һ��Բ�ͽ������� 
	         */  
	        ImageView image = new ImageView(context);  
	        image.setImageResource(R.drawable.zzloading26);  
	        Animation anim = AnimationUtils.loadAnimation(context,  
	                R.anim.rotate_repeat);  
	        LinearInterpolator lir = new LinearInterpolator();  
	        anim.setInterpolator(lir);  
	        image.setAnimation(anim);  
	  
	        contentView.addView(image);  
	        setContentView(contentView);  
	  
	    }  
	  
	    @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        // ���¼����Ϸ��ذ�ť  
	        if (keyCode == KeyEvent.KEYCODE_BACK) {  
	            this.dismiss();  
	        }  
	        return true;  
	    }  

}
