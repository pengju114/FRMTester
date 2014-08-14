package com.example.tester.holders;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.tester.R;
import com.pj.core.BaseActivity;
import com.pj.core.http.HttpRequest;
import com.pj.core.http.HttpResult;
import com.pj.core.viewholders.HttpViewHolder;

public class LoginHolder extends HttpViewHolder implements OnClickListener{

	public LoginHolder(BaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
		setLayoutResource(R.layout.login);
	}

	@Override
	protected void onApplyView(View view) {
		// TODO Auto-generated method stub
		assignClickListener(this, R.id.btn_login,R.id.btn_get_userinfo);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btn_login) {
			HttpRequest request=makeRequest(1, "10003",null , "name","pengju","password","762354");
			request.setExpectedDataFormat(HttpRequest.EXPRCTED_DATAWRAPPER);
			request.setResponseDataFormat(HttpRequest.RESPONSE_JSON);
			
			asyncRequest(request);
		}else if (v.getId() == R.id.btn_get_userinfo) {
			HttpRequest request=makeRequest(2, "10004",null);
			request.setExpectedDataFormat(HttpRequest.EXPRCTED_DATAWRAPPER);
			request.setResponseDataFormat(HttpRequest.RESPONSE_JSON);
			
			asyncRequest(request);
		}
	}
	
	@Override
	public void onHttpResponse(HttpRequest request, HttpResult result) {
		// TODO Auto-generated method stub
		super.onHttpResponse(request, result);
		if (request.getRequestCode() == 1) {
			if (isHttpSuccessAndNotify(result)) {
				getActivity().showMessageDialog(0, "登录", "登录成功", "OK", null);
			}
		}else if (request.getRequestCode() == 2) {
			if (isHttpSuccessAndNotify(result)) {
				((TextView)find(R.id.label_name)).setText(result.getDataList().get(0).getString("name"));
			}
			
		}
	}

	@Override
	public String getUrlByAction(String action) {
		// TODO Auto-generated method stub
		return "http://192.168.1.102:8080/web-framework/client.service?service="+action;
	}
}
