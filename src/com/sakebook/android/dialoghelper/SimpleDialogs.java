package com.sakebook.android.dialoghelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
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
	
	public SimpleDialogs() {
	}
	
	
	/** Singleton. Fragmentはコンストラクタに引数は使えないため、Bundleに詰める。
	 * @param title title
	 * @param message message
	 * @param positive positive button
	 * @param negative negative button
	 * @param neutral neutral button
	 *  */
	public static SimpleDialogs newInstance(String title, String message, String positive, String negative,
			String neutral) {
		SimpleDialogs instance = new SimpleDialogs();
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		bundle.putString("message", message);
		bundle.putString("positive", positive);
		bundle.putString("negative", negative);
		bundle.putString("neutral", neutral);
		instance.setArguments(bundle);
		
		return instance;
	}
	
	/** Singleton. Fragmentはコンストラクタに引数は使えないため、Bundleに詰める。リソースID用。
	 * @param context resource取得用context
	 * @param title title
	 * @param message message
	 * @param positive positive button
	 * @param negative negative button
	 * @param neutral neutral button
	 *  */
	public static SimpleDialogs newInstance(Context context, int titleId, int messageId, int positiveId, int negativeId,
			int neutralId) {
		SimpleDialogs instance = new SimpleDialogs();
		Resources res = context.getResources();
		Bundle bundle = new Bundle();
		bundle.putString("title", res.getString(titleId));
		bundle.putString("message", res.getString(messageId));
		bundle.putString("positive", res.getString(positiveId));
		bundle.putString("negative", res.getString(negativeId));
		bundle.putString("neutral", res.getString(neutralId));
		instance.setArguments(bundle);
		
		return instance;
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
		return builder.create();
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}


	@Override
	public void dismiss() {
		super.dismiss();
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
		default:
			mListener.simpleCancel(dialog, SimpleDialogsListener.BUTTON_CANCEL);
			break;
		}
	}
}
