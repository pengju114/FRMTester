package com.example.tester.holders;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;

import com.example.tester.R;
import com.pj.core.BaseActivity;
import com.pj.core.BaseApplication;
import com.pj.core.managers.LogManager;
import com.pj.core.utilities.AppUtility;
import com.pj.core.viewholders.ViewHolder;

public class FirstHolder extends ViewHolder implements OnClickListener{

	public FirstHolder(BaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
		setLayoutResource(R.layout.first);
	}

	
	protected void onApplyView(View view) {
		// TODO Auto-generated method stub
		assignClickListener(this, 
				R.id.btn_login,
				R.id.btn_get_userinfo,
				R.id.btn_module_test,
				R.id.btn_dialog_test,
				R.id.btn_exemd_test
				);
		
		getNavigationBar().setTitle("测试首页");
	}

	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			getNavigationViewHolder().push(new DialogTestViewHolder(getActivity()), true);
			break;

		case R.id.btn_get_userinfo:
			FlipTestViewHolder flipTestViewHolder=new FlipTestViewHolder(getActivity());
			flipTestViewHolder.showAsModuleViewByFlipping();
			break;
		case R.id.btn_module_test:
			ModuleTestViewHolder moduleTestViewHolder=new ModuleTestViewHolder(getActivity());
			moduleTestViewHolder.showAsModuleView(true);
//			((ViewGroup)getActivity().getRootViewHolder().getView()).addView(moduleTestViewHolder.getView());
			break;
		case R.id.btn_dialog_test:
			ModuleTestViewHolder testViewHolder = new ModuleTestViewHolder(getActivity());
			testViewHolder.showInDialog();
			
			break;
			
		case R.id.btn_exemd_test:
			executeMethodInBackground(1000,this, "backgroundTask", (Object[])null);
			
			break;
		default:
			break;
		}
	}
	
	private void backgroundTask(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LogManager.i("线程跑完");
		executeMethodInMainThread(1000,this, "onClick", findViewById(R.id.btn_module_test));
	}
	
	
	public void onViewWillDisappear(boolean animated) {
		// TODO Auto-generated method stub
		super.onViewWillDisappear(animated);
		AppUtility.hideInputSoft(getView());
	}

	
	
	protected String getShareActivityAction() {
		// TODO Auto-generated method stub
		return "com.activity.share";
	}
}
