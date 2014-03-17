package com.sakebook.android.dialoghelper;

import android.view.View;

/**
 * ダイアログのイベントのコールバック.
 * idはここで定義して場合分けに用いる。
 * vはイベントが起きたView。
 * */
public interface CustomDialogsListener {
	
	public static final int BUTTON_CLICK = 1;
	public static final int BUTTON_CANCEL = 0;
	
	public void customClick(int id, View v);
	public void customCancel(int id);
}
