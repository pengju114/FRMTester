package com.example.tester.holders;

import android.view.View;
import android.view.View.OnClickListener;
import com.example.tester.R;
import com.pj.core.BaseActivity;
import com.pj.core.viewholders.HttpViewHolder;

public class FlipTestViewHolder extends HttpViewHolder implements OnClickListener{
	
	
	public FlipTestViewHolder(BaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
		setLayoutResource(R.layout.flip_module_test);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		closeModuleViewByFlipping();
	}

	@Override
	protected void onApplyView(View view) {
		// TODO Auto-generated method stub
		assignClickListener(this, R.id.btn_flip_back);
	}
}
