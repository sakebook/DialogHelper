package com.sakebook.android.dialoghelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.robolectric.Robolectric;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowApplication;

import java.util.ArrayList;

/**
 * Created by sakemotoshinya on 2014/08/12.
 */
@Implements(CustomDialogs.class)
public class CustomDialogsShadow {

    @RealObject
    private CustomDialogs dialogs;

    private CustomDialogsShadow shadow;

    @RealObject
    private CustomDialogsListener listener;

    @Implementation
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

    public CustomDialogsShadow getDialogs() {
        shadow = new CustomDialogsShadow();
        listener = new CustomDialogsListener() {
            @Override
            public void customClick(int id, View v) {
                Log.d("TAG", "customClick");
            }

            @Override
            public void customCancel(int id) {
                Log.d("TAG", "customCancel");

            }

            @Override
            public void customDismiss(int id) {
                Log.d("TAG", "customDismiss");

            }
        };
        return shadow;
    }


    @Implementation
    public static CustomDialogs newInstance(int bodyLayoutId, ArrayList<Integer> eventList) {
        return newInstance(bodyLayoutId, eventList, true, true);
    }


    @Implementation
    public void onCancel(DialogInterface dialog) {
        listener.customCancel(CustomDialogsListener.BUTTON_CANCEL);
    }


    @Implementation
    public void dismiss() {
        listener.customDismiss(CustomDialogsListener.DISMISS);
    }


    @Implementation
    public int onClick() {
        return CustomDialogsListener.BUTTON_CLICK;
    }
}
