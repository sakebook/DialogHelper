package com.sakebook.android.dialoghelper;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Dialogを呼び出すクラス。
 * */
public class DialogHelper {
	
	
	/**
	 * 通常のダイアログを呼び出す。
	 * @param context コンテキスト。親クラスがFragmentActivityを継承している必要がある。
	 * @param title ダイアログのタイトル。不要な場合はnullを入れる。
	 * @param message ダイアログの内容。不要な場合はnullを入れる。
	 * @param positive ポジティブボタン。不要な場合はnullを入れる。
	 * @param negative ネガティブボタン。不要な場合はnullを入れる。
	 * @param neutral ニュートラルボタン。不要な場合はnullを入れる。
	 * @param tag タグ。不要な場合はnullを入れる。
	 * */
	public static void callDialog(Context context, String title, 
			String message, String positive, String negative, String neutral, String tag) {
		if (checkExtends(context)) {
			FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
			SimpleDialogs dialog = SimpleDialogs.newInstance(title, message, positive, negative, neutral);
			dialog.show(manager, tag);
		}
	}
	
	
	/**
	 * 通常のダイアログを呼び出す。
	 * @param context コンテキスト。親クラスがFragmentActivityを継承している必要がある。
	 * @param titleId ダイアログのタイトル。不要な場合は0を入れる。
	 * @param messageId ダイアログの内容。不要な場合は0を入れる。
	 * @param positiveId ポジティブボタン。不要な場合は0を入れる。
	 * @param negativeId ネガティブボタン。不要な場合は0を入れる。
	 * @param neutralId ニュートラルボタン。不要な場合は0を入れる。
	 * @param tag タグ。不要な場合はnullを入れる。
	 * */
	public static void callDialog(Context context, int titleId, int messageId, 
			int positiveId, int negativeId, int neutralId, String tag) {
		if (checkExtends(context)){
			FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
			SimpleDialogs dialog = SimpleDialogs.newInstance(titleId, messageId, positiveId, negativeId, neutralId);
			dialog.show(manager, tag);
		}
	}
	
	
	/**
	 * CustomLayoutのダイアログを使う場合に用いる。
	 * @param context コンテキスト。親クラスがFragmentActivityを継承している必要がある。
	 * @param layoutId 利用したいレイアウトのID。
	 * @param eventList クリックイベントを起こしたいViewのIDを詰める。
	 * @param tag タグ。不要な場合はnullを入れる。
	 * */
	public static void callCustomDialog(Context context, int layoutId, ArrayList<Integer> eventList, String tag) {
		if (checkExtends(context)){
			FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
	        CustomDialogs dialogFragment = CustomDialogs.newInstance(layoutId, eventList);
	        dialogFragment.show(manager, tag);
		}
	}
	
	private static boolean checkExtends(Context context) {
		if (context instanceof FragmentActivity == false) {  
			Log.w(DialogHelper.class.getSimpleName(), "FragmentActivity を継承していません.");
			return false;
        }  
		return true;
	}

}
