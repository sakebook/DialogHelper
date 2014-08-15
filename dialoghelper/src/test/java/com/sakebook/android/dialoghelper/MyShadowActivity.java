package com.sakebook.android.dialoghelper;

import android.content.DialogInterface;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

/**
 * Created by sakemotoshinya on 2014/08/10.
 */
@Implements(SimpleDialogsListener.class)
public class MyShadowActivity extends CustomDialogs{
    @RealObject
    private SimpleDialogsListener listener;

    @Implementation
    public void simplePositiveClick(DialogInterface dialog, int which){
    }

    @Implementation
    public void simpleNegativeClick(DialogInterface dialog, int which){
    }

    @Implementation
    public void simpleNeutralClick(DialogInterface dialog, int which){
    }

    @Implementation
    public void simpleCancel(DialogInterface dialog, int which){
    }

    @Implementation
    public void simpleDismiss(int which){
    }

}