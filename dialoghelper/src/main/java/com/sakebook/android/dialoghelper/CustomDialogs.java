package com.sakebook.android.dialoghelper;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class CustomDialogs extends DialogFragment implements OnClickListener {
	
	public CustomDialogsListener mListener;

	public CustomDialogs() {
	}

	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof CustomDialogsListener == false) {  
			Log.w(DialogHelper.class.getSimpleName(), "FragmentActivity が CustomDialogListener を実装していません.");
			this.dismiss();
        }  
        this.mListener = ((CustomDialogsListener) activity);  
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_FRAME, R.style.Theme_dialogLayout);
		setRetainInstance(true);
	}

	
	/** 
	 * 受け取ったlayoutIdとイベントを起こしたいViewのIdをbundleにセット
	 *  */
	public static CustomDialogs newInstance(int bodyLayoutId, ArrayList<Integer> eventList, Boolean outsideCancel, Boolean backCancel) {
		CustomDialogs instance = new CustomDialogs();
		Bundle bundle = new Bundle();
		bundle.putInt("body", bodyLayoutId);
		bundle.putIntegerArrayList("eventList", eventList);
		bundle.putBoolean("outsideCancel", outsideCancel);
		bundle.putBoolean("backCancel", backCancel);
		instance.setArguments(bundle);
		
		return instance;
	}

	public static CustomDialogs newInstance(int bodyLayoutId, ArrayList<Integer> eventList) {
		return newInstance(bodyLayoutId, eventList, true, true);
	}

	
	/** 
	 * ダイアログの中だけ変更したい場合こちらを使う。
	 *  */
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if (Build.VERSION.SDK_INT <= 10) {
			return super.onCreateDialog(savedInstanceState);
		}

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View body = inflater.inflate(getArguments().getInt("body"), null, false);
		
		ArrayList<Integer> eventList = getArguments().getIntegerArrayList("eventList");
		for (int i=0; i< eventList.size(); i++) {
			body.findViewById(eventList.get(i)).setOnClickListener(this);
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(body);
		Dialog dialog = builder.create();
		
		dialog.setCanceledOnTouchOutside(getArguments().getBoolean("outsideCancel"));
		setCancelable(getArguments().getBoolean("backCancel"));
		
		return dialog;
	}
	
	
	/** 
	 * 2.x台はフレームが残ってしまうので、onCreateDialogではなくこちらを使ってダイアログを一から生成。
	 * bundleにセットされた情報を加え、レイアウトを返す。 */
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
		if (Build.VERSION.SDK_INT > 10) {
			return super.onCreateView(inflater, view, bundle);
		}
		View body = inflater.inflate(getArguments().getInt("body"), null, false);

		ArrayList<Integer> eventList = getArguments().getIntegerArrayList("eventList");
		for (int i=0; i< eventList.size(); i++) {
			body.findViewById(eventList.get(i)).setOnClickListener(this);
		}
		return body;
	}
	
	
	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		mListener.customCancel(CustomDialogsListener.BUTTON_CANCEL);
	}

	
	@Override
	public void dismiss() {
		super.dismiss();
		mListener.customDismiss(CustomDialogsListener.DISMISS);
	}

	
	@Override
	public void onClick(View v) {
		mListener.customClick(CustomDialogsListener.BUTTON_CLICK, v);
		dismiss();
	}
}
