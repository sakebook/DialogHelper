package com.sakebook.android.dialoghelper;

import android.content.DialogInterface;

/**
 * シンプルダイアログのイベントのコールバック.
 * */
public interface SimpleDialogsListener {
	
	public static final int DISMISS = -1;
	public static final int BUTTON_CANCEL = 0;
	public static final int BUTTON_CLICK_POSITIVE = 1;
	public static final int BUTTON_CLICK_NEGATVE = 2;
	public static final int BUTTON_CLICK_NEUTRAL = 3;
	
	public void simplePositiveClick(DialogInterface dialog, int which);
	public void simpleNegativeClick(DialogInterface dialog, int which);
	public void simpleNeutralClick(DialogInterface dialog, int which);
	public void simpleCancel(DialogInterface dialog, int which);
	public void simpleDismiss(int which);
}
