package com.sakebook.android.dialoghelper;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * Dialogを呼び出すクラス。
 * @author sakemotoshinya
 * */
public class DialogHelper {
	
	/**
	 * 通常のダイアログを作成。
	 * @param context コンテキスト。親クラスがFragmentActivityを継承している必要がある。
	 * */
	public static Builder create(Context context) {
		return new Builder(context);
	}
	
	
	/**
	 * CustomLayoutのダイアログを使う場合に用いる。
	 * @param context コンテキスト。親クラスがFragmentActivityを継承している必要がある。
	 * */
	public static CustomBuilder customCreate(Context context) {
		return new CustomBuilder(context);
	}
	
	
	private DialogHelper() {
	}

	
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
	@Deprecated
	public static void callDialog(Context context, String title, 
			String message, String positive, String negative, String neutral, String tag) {
		if (checkExtends(context)) {
			FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
			SimpleDialogs dialog = SimpleDialogs.newInstance(title, message, positive, negative, neutral);
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
   	@Deprecated
	public static void callCustomDialog(Context context, int layoutId, ArrayList<Integer> eventList, String tag) {
		if (checkExtends(context)){
			FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
	        CustomDialogs dialogFragment = CustomDialogs.newInstance(layoutId, eventList);
	        dialogFragment.show(manager, tag);
		}
	}
	
	
	private static boolean checkExtends(Context context) {
		if ((context instanceof FragmentActivity == false)) {  
			Log.w(DialogHelper.class.getSimpleName(), "FragmentActivity を継承していません.");
			return false;
        }  
		return true;
	}


	public static class Builder {

		private Context context;
		private String title = "";
		private String message = "";
		private String positive = "";
		private String negative = "";
		private String neutral = "";
		private String tag = "";
		private Boolean outsideCancel = true;
		private Boolean backCancel = true;
		private SimpleDialogs dialog;

		private Builder(Context context) {
			this.context = context;
		}


		/**
		 * タイトルを指定。
		 * @param title タイトルに利用する文字列
		 * */
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}


		/**
		 * タイトルをリソースから指定。
		 * @param titleId タイトルに利用する文字列のリソースID
		 * */
		public Builder setTitle(int titleId) {
			return setTitle(context.getResources().getString(titleId));
		}


		/**
		 * メッセージを指定。
		 * @param message メッセージに利用する文字列
		 * */
		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}


		/**
		 * メッセージをリソースから指定。
		 * @param messageId メッセージに利用する文字列のリソースID
		 * */
		public Builder setMessage(int messageId) {
			return setMessage(context.getResources().getString(messageId));
		}


		/**
		 * Positiveボタンラベルを指定。
		 * @param label ラベルに利用する文字列
		 * */
		public Builder setPositive(String label) {
			this.positive = label;
			return this;
		}


		/**
		 * Positiveボタンラベルをリソースから指定。
		 * @param labelId ラベルに利用する文字列のリソースID
		 * */
		public Builder setPositive(int labelId) {
			return setPositive(context.getResources().getString(labelId));
		}


		/**
		 * Negativeボタンラベルを指定。
		 * @param label ラベルに利用する文字列
		 * */
		public Builder setNegative(String label) {
			this.negative = label;
			return this;
		}


		/**
		 * Negativeボタンラベルをリソースから指定。
		 * @param labelId ラベルに利用する文字列のリソースID
		 * */
		public Builder setNegative(int labelId) {
			return setNegative(context.getResources().getString(labelId));
		}


		/**
		 * Neutralボタンラベルを指定。
		 * @param label ラベルに利用する文字列
		 * */
		public Builder setNeutral(String label) {
			this.neutral = label;
			return this;
		}


		/**
		 * Neutralボタンラベルをリソースから指定。
		 * @param labelId ラベルに利用する文字列のリソースID
		 * */
		public Builder setNeutral(int labelId) {
			return setNeutral(context.getResources().getString(labelId));
		}


		/**
		 * タグを指定。
		 * @param tag タグに利用する文字列
		 * */
		public Builder setTag(String tag) {
			this.tag = tag;
			return this;
		}


		/**
		 * タグをリソースから指定。
		 * @param tagId タグに利用する文字列のリソースID
		 * */
		public Builder setTag(int tagId) {
			return setTag(context.getResources().getString(tagId));
		}


		/**
		 * ダイアログ外をタッチして終了するか指定。
		 * @param bool 終了させるならtrue. デフォルトはtrue
		 * */
		public Builder setTouchCancelable(boolean bool) {
			this.outsideCancel = bool;
			return this;
		}


		/**
		 * backボタンで終了するか指定。
		 * @param bool 終了させるならtrue. デフォルトはtrue
		 * */
		public Builder setBackCancelable(boolean bool) {
			this.backCancel = bool;
			return this;
		}


		/**
		 * ダイアログを表示させる。
		 * 親クラスがFragmentActivityを継承している必要がある。
		 * */
		public void build() {
			if (checkExtends(context)){
				FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
				dialog = SimpleDialogs.newInstance(title, message, positive, negative, neutral, outsideCancel, backCancel);
				FragmentTransaction ft = manager.beginTransaction();
				ft.add(dialog, this.tag);
				ft.commitAllowingStateLoss();
			}
		}


		/**
		 * ダイアログを終了させる。
		 * インスタンスチェックを内包している。
		 * */
		public void dismiss() {
			if (dialog != null) {
				dialog.dismiss();
			}
		}

	}

	public static class CustomBuilder {

		private Context context;
		private String tag = "";
		private int layoutId = 0;
		private ArrayList<Integer> eventList;
		private Boolean outsideCancel = true;
		private Boolean backCancel = true;
		private CustomDialogs dialog;

		private CustomBuilder(Context context) {
			this.context = context;
		}


		/**
		 * 利用したいlayoutを指定。
		 * @param layout 利用したいlayoutのリソースID
		 * */
		public CustomBuilder setLayout(int layout) {
			this.layoutId = layout;
			return this;
		}


		/**
		 * イベントを起こしたいViewを指定。
		 * @param eventList イベントを起こしたいViewリソースIDのリスト
		 * */
		public CustomBuilder setEventList(ArrayList<Integer> eventList) {
			this.eventList = eventList;
			return this;
		}


		/**
		 * タグを指定。
		 * @param tag タグに利用する文字列
		 * */
		public CustomBuilder setTag(String tag) {
			this.tag = tag;
			return this;
		}


		/**
		 * タグをリソースから指定。
		 * @param tagId タグに利用する文字列のリソースID
		 * */
		public CustomBuilder setTag(int tagId) {
			return setTag(context.getResources().getString(tagId));
		}


		/**
		 * ダイアログ外をタッチして終了するか指定。
		 * @param bool 終了させるならtrue. デフォルトはtrue
		 * */
		public CustomBuilder setTouchCancelable(boolean bool) {
			this.outsideCancel = bool;
			return this;
		}


		/**
		 * backボタンで終了するか指定。
		 * @param bool 終了させるならtrue. デフォルトはtrue
		 * */
		public CustomBuilder setBackCancelable(boolean bool) {
			this.backCancel = bool;
			return this;
		}


		/**
		 * ダイアログを表示させる。
		 * 親クラスがFragmentActivityを継承している必要がある。
		 * */
		public void build() {
			if (checkExtends(context)){
				FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
				dialog = CustomDialogs.newInstance(layoutId, eventList, outsideCancel, backCancel);
				FragmentTransaction ft = manager.beginTransaction();
				ft.add(dialog, this.tag);
				ft.commitAllowingStateLoss();
			}
		}


		/**
		 * ダイアログを終了させる。
		 * インスタンスチェックを内包している。
		 * */
		public void dismiss() {
			if (dialog != null) {
				dialog.dismiss();
			}
		}
	}

}