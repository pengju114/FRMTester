package com.example.tester.holders;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.tester.R;
import com.pj.core.BaseActivity;
import com.pj.core.utilities.AppUtility;
import com.pj.core.viewholders.ViewHolder;

public class ModuleTestViewHolder extends ViewHolder implements View.OnClickListener{

	public ModuleTestViewHolder(BaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
		
		setLayoutResource(R.layout.module_test);
	}
	
	@Override
	protected void initialize(BaseActivity activity, View view) {
		// TODO Auto-generated method stub
		super.initialize(activity, view);
		
		getNavigationBar().setTitle("测试模式视图");
		
		Button button=new Button(getActivity());
		button.setText("关闭");
		button.setBackgroundResource(R.drawable.c_navigation_item_selector);
		
		getNavigationBar().setNavigationRightView(button, this);
	}


	@Override
	protected void onApplyView(View view) {
		// TODO Auto-generated method stub
		getNavigationBar().attachToRelativeLayout((RelativeLayout)find(R.id.nav_bar));
		assignClickListener(this, R.id.button_show);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId()==R.id.button_show) {
			find(R.id.editText1).requestFocus();
//			getActivity().showTip(AppUtility.showInputSoft(find(R.id.editText1))+"");
			return;
		}
		
		closeModuleView(true);
	}
	
	@Override
	public void onViewDidAppear(boolean animated) {
		// TODO Auto-generated method stub
		super.onViewDidAppear(animated);
		
	}

}
