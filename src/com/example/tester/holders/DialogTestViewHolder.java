package com.example.tester.holders;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tester.R;
import com.pj.core.AsyncExecutor;
import com.pj.core.BaseActivity;
import com.pj.core.datamodel.DataWrapper;
import com.pj.core.dialog.CacheableDialog;
import com.pj.core.dialog.DialogListener;
import com.pj.core.dialog.InputDialog;
import com.pj.core.managers.LogManager;
import com.pj.core.managers.SDCardFileManager;
import com.pj.core.res.Constants;
import com.pj.core.viewholders.HttpViewHolder;
import com.pj.core.http.HttpDownloader;
import com.pj.core.http.HttpDownloader.HttpStateListener;
import com.pj.core.http.HttpRequest;
import com.pj.core.http.HttpResult;
import com.pj.core.http.HttpState;
import com.pj.core.http.HttpTransfer;

public class DialogTestViewHolder extends HttpViewHolder implements OnClickListener,AsyncExecutor<List<DataWrapper>>,HttpStateListener,DialogListener{
	
	private CheckBox checkBox;
	private TextView stateTextView;
	private ProgressBar progressBar;
	private HttpDownloader downloader;
	
	public DialogTestViewHolder(BaseActivity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
		setLayoutResource(R.layout.dialog_test);
	}
	
	@Override
	protected void initialize(BaseActivity activity, View view) {
		// TODO Auto-generated method stub
		super.initialize(activity, view);
		
		getNavigationBar().setTitle("测试对话框");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_confirm:
			getActivity().asyncExecute(this);
			getActivity().showConfirmDialog(0,"嗯","你看吗？","OK","cancel",this);
			break;
		case R.id.button_executing:
			getActivity().showExecutingDialog(0,"executing data",this);
			break;
		case R.id.button_info:
			getActivity().showMessageDialog(0, null, "message", "good", null);
			break;
		case R.id.button_progress:
			getActivity().showProgressDialog(0,"progress data",this);
			
			break;
		case R.id.button_web_req:
			HttpRequest request=new HttpRequest(getUrlByAction("s_login.action"), 1);
			request.setParameter("email", "pj@a.com");
			request.setParameter("password", "762354");
			LogManager.i(request);
			request.setHttpRequestListener(this);
			request.startAsynchronousRequest();
			
			break;
		case R.id.btn_download:
			download();
			break;
		case R.id.button_input:
			getActivity().showInputDialog(0,"你想说什么吗", "输入名字", "cc", "en", "OK", "Cancel", this);
			break;
		case R.id.button_stop:
			if (downloader!=null) {
				downloader.setAbort(true);
			}
			break;
			
		case R.id.btn_update:
			Intent intent=new Intent("com.framework.update");
			HttpTransfer transfer=new HttpTransfer();
			transfer.setFrom("http://www.appchina.com/market/d/7171/cop.baidu_0/packagename.apk");
			transfer.setTo(SDCardFileManager.APP_DIR);
			transfer.setDescription("僵尸大战");
			transfer.setThumbnail(BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher));
			intent.putExtra(Constants.Keys.TRANS_DATA, (Parcelable)transfer);
			getActivity().startService(intent);
			break;

		case R.id.button_flip:
			FlipTestViewHolder flipTestViewHolder=new FlipTestViewHolder(getActivity());
			flipTestViewHolder.showAsModuleViewByFlipping();
			break;
			
		case R.id.button_exit:
			getNavigationViewHolder().push(new ModuleTestViewHolder(getActivity()), true);
			
			break;
		default:
			break;
		}
	}
	
	@Override
	public String getUrlByAction(String action) {
		// TODO Auto-generated method stub
		return "http://mobile2.k3d.hk:8088/"+action;
	}

	
	private void download() {
		// TODO Auto-generated method stub
		HttpTransfer transfer=new HttpTransfer();
		transfer.setFrom("http://ttplayer.qianqian.com/download/ttpsetup_612-44059078.exe");
		transfer.setTo(SDCardFileManager.APP_DIR);
		downloader=new HttpDownloader(transfer);
		downloader.setBreakpointContinuinglySupport(checkBox.isChecked());
		downloader.setHttpStateListener(this);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					downloader.download();
				} catch (Exception e) {
					// TODO: handle exception
					getActivity().showMessageDialog(0,"错误",null,null,e.getMessage(),null);
				}
			}
		}).start();
	}
	
	@Override
	public void beforeHttpRequest(HttpRequest request) {
		// TODO Auto-generated method stub
		super.beforeHttpRequest(request);
		
		getActivity().showExecutingDialog(0,"正在登陆...",null);
	}
	
	@Override
	public void onHttpResponse(HttpRequest request, HttpResult result) {
		// TODO Auto-generated method stub
		super.onHttpResponse(request, result);
		if (isHttpSuccessAndNotify(result)) {
			getActivity().showMessageDialog(0, "OK", result.getResponseData().toString(), null, null, null);
		}
	}
	
	@Override
	public void onHttpRequestCancelled(HttpRequest request) {
		// TODO Auto-generated method stub
		super.onHttpRequestCancelled(request);
	}
	
	public void handleMessage(int id,Object data) {
		super.handleMessage(id, data);
		if (id==HttpStateListener.STATE_RUNNING) {
			HttpState httpState=(HttpState) data;
			float p=(float)httpState.getTotalTransferBytes()/(float)httpState.getLength();
			progressBar.setProgress((int)(p*100));
			stateTextView.setText(progressBar.getProgress()+"%");
		}else if (id==HttpStateListener.STATE_FINISH) {
			getActivity().showTip("下载完成");
		}
	}

	@Override
	protected void onApplyView(View view) {
		// TODO Auto-generated method stubViewGroup group=(ViewGroup)rootView;
		ViewGroup group=(ViewGroup) view;
		for (int i = 0; i < group.getChildCount(); i++) {
			group.getChildAt(i).setOnClickListener(this);
		}
		
		checkBox=(CheckBox) group.findViewById(R.id.chk_bpsp);
		stateTextView=(TextView) group.findViewById(R.id.text_state);
		progressBar=(ProgressBar) group.findViewById(R.id.progress);
		group.findViewById(R.id.btn_download).setOnClickListener(this);
		group.findViewById(R.id.button_stop).setOnClickListener(this);
	}

	
	@Override
	public List<DataWrapper> execute() {
		// TODO Auto-generated method stub
		LogManager.i("线程开始",Thread.currentThread());
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ArrayList<DataWrapper>();
	}

	@Override
	public void executeComplete(List<DataWrapper> value) {
		// TODO Auto-generated method stub
		checkBox.setChecked(true);
		LogManager.i("线程返回"+Thread.currentThread(),value);
	}

	@Override
	public void onDialogClose(int requestCode, CacheableDialog dialog,
			int triggerbtn, Object cacheData) {
		// TODO Auto-generated method stub
		if (triggerbtn==DialogListener.BTN_OK) {
			getActivity().showTip(dialog.getObject(InputDialog.KEY_TEXT));
		}
	}

	@Override
	public void httpStateChange(int state, HttpState httpState,
			HttpDownloader downloader, String statusText) {
		// TODO Auto-generated method stub
		postMessage(state, httpState);
	}

	@Override
	public void executePrepare() {
		// TODO Auto-generated method stub
		
	}
}
