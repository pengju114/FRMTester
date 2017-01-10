package com.example.tester;

import com.example.tester.holders.FirstHolder;
import com.pj.core.AsyncExecutor;
import com.pj.core.BaseActivity;
import com.pj.core.dialog.HolderDialog;
import com.pj.core.utilities.ThreadUtility;
import com.pj.core.viewholders.NavigationViewHolder;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements AsyncExecutor<String>,OnClickListener{

	private TextView  textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSystemBarTintColor(Color.parseColor("#ff0654"));
		NavigationViewHolder navigationViewHolder=new NavigationViewHolder(new FirstHolder(this));
		setContentView(navigationViewHolder);
	}

	
	public String execute() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		FirstHolder holder=new FirstHolder(this);
		HolderDialog dialog=new HolderDialog(holder);
		dialog.show();
		return "哈哈";
	}

	public void executeComplete(String value) {
		// TODO Auto-generated method stub
		textView.setText(value);
	}

	public void executePrepare() {
		// TODO Auto-generated method stub
	     textView.setText("executePrepare");
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		ThreadUtility.execute(this);
	}


	@Override
	public boolean isExecuteCancel() {
		// TODO Auto-generated method stub
		return false;
	}
}
