package com.example.tester.holders;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;

import com.example.tester.R;
import com.pj.core.BaseActivity;
import com.pj.core.utilities.AppUtility;
import com.pj.core.viewholders.ViewHolder;

public class FirstHolder extends ViewHolder implements OnClickListener{

	public FirstHolder(BaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
		setLayoutResource(R.layout.first);
	}

	@Override
	protected void onApplyView(View view) {
		// TODO Auto-generated method stub
		assignClickListener(this, 
				R.id.btn_test_dlg,
				R.id.btn_filp_test,
				R.id.btn_module_test,
				R.id.button_rminput
				);
		
		getNavigationBar().setTitle("测试首页");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_test_dlg:
			getNavigationViewHolder().push(new DialogTestViewHolder(getActivity()), true);
			break;

		case R.id.btn_filp_test:
			FlipTestViewHolder flipTestViewHolder=new FlipTestViewHolder(getActivity());
			flipTestViewHolder.showAsModuleViewByFlipping();
			break;
		case R.id.btn_module_test:
			ModuleTestViewHolder moduleTestViewHolder=new ModuleTestViewHolder(getActivity());
			moduleTestViewHolder.showAsModuleView(true);
//			((ViewGroup)getActivity().getRootViewHolder().getView()).addView(moduleTestViewHolder.getView());
			break;
		case R.id.button_rminput:
//			((ViewGroup)((ViewGroup)getView()).getChildAt(0)).removeView(find(R.id.editText1));
//			InputMethodManager inputMethodManager=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//			inputMethodManager.hideSoftInputFromWindow(getView().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			
//			find(R.id.editText2).requestFocus();
			
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onViewWillDisappear(boolean animated) {
		// TODO Auto-generated method stub
		super.onViewWillDisappear(animated);
		AppUtility.hideInputSoft(getView());
	}

	
	@Override
	protected String getShareActivityAction() {
		// TODO Auto-generated method stub
		return "com.activity.share";
	}
}
