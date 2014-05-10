package com.sakebook.android.dialoghelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;

public class SimpleDialogs extends DialogFragment implements OnClickListener {

	private SimpleDialogsListener mListener;
	
	private String title = "";
	private String message = "";
	private String positive = "";
	private String negative = "";
	private String neutral = "";
	private Boolean outsideCancel = true;
	private Boolean backCancel = true;
	
	public SimpleDialogs() {
	}
	
	
	/** 
	 * Fragmentはコンストラクタに引数は使えないため、Bundleに詰める。
	 * @param title title
	 * @param message message
	 * @param positive positive button
	 * @param negative negative button
	 * @param neutral neutral button
	 * @return SimpleDialogs
	 *  */
	public static SimpleDialogs newInstance(String title, String message, String positive, String negative,
			String neutral, Boolean outsideCancel, Boolean backCancel) {
		SimpleDialogs instance = new SimpleDialogs();
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		bundle.putString("message", message);
		bundle.putString("positive", positive);
		bundle.putString("negative", negative);
		bundle.putString("neutral", neutral);
		bundle.putBoolean("outsideCancel", outsideCancel);
		bundle.putBoolean("backCancel", backCancel);
		instance.setArguments(bundle);
		
		return instance;
	}
	
	public static SimpleDialogs newInstance(String title, String message, String positive, String negative,
			String neutral) {
		return newInstance(title, message, positive, negative, neutral, true, true);
	}
	

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (activity instanceof SimpleDialogsListener == false) {  
			Log.w(DialogHelper.class.getSimpleName(), "FragmentActivity が SimpleDialogListener を実装していません.");
			this.dismiss();
			return;
        }
        this.mListener = ((SimpleDialogsListener) activity);  
	}

	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		this.title = getArguments().getString("title");
		this.message = getArguments().getString("message");
		this.positive = getArguments().getString("positive");
		this.negative = getArguments().getString("negative");
		this.neutral = getArguments().getString("neutral");
		this.outsideCancel = getArguments().getBoolean("outsideCancel");
		this.backCancel = getArguments().getBoolean("backCancel");
		
		builder.setTitle(title);
		builder.setMessage(message);
		if (!TextUtils.isEmpty(positive)) {
			builder.setPositiveButton(positive, this);
		}
		if (!TextUtils.isEmpty(negative)) {
			builder.setNegativeButton(negative, this);
		}
		if (!TextUtils.isEmpty(neutral)) {
			builder.setNeutralButton(neutral, this);
		}
		
		Dialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(outsideCancel);
		setCancelable(backCancel);
		
		return dialog;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}


	@Override
	public void dismiss() {
		super.dismiss();
		mListener.simpleDismiss(SimpleDialogsListener.DISMISS);
	}

	
	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		mListener.simpleCancel(dialog, SimpleDialogsListener.BUTTON_CANCEL);
	}

	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case SimpleDialogsListener.BUTTON_CLICK_POSITIVE:
			mListener.simplePositiveClick(dialog, which);
			break;
		case SimpleDialogsListener.BUTTON_CLICK_NEGATVE:
			mListener.simpleNegativeClick(dialog, which);
			break;
		case SimpleDialogsListener.BUTTON_CLICK_NEUTRAL:
			mListener.simpleNeutralClick(dialog, which);
			break;
		case SimpleDialogsListener.DISMISS:
			mListener.simpleDismiss(which);
			break;
		default:
			mListener.simpleCancel(dialog, SimpleDialogsListener.BUTTON_CANCEL);
			break;
		}
	}
}
