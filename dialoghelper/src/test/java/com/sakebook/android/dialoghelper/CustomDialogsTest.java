package com.sakebook.android.dialoghelper;

import android.app.Activity;
import android.net.VpnService;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewStub;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

/**
 * Created by sakemotoshinya on 2014/08/11.
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows = CustomDialogsShadow.class)
public class CustomDialogsTest {
//    CustomDialogsShadow mDialogs;

    @Before
    public void setup() {
//        mDialogs = Robolectric.shadowOf_(CustomDialogs.newInstance(R.layout.layout_dialog_test, null));
//        CustomDialogsShadow mDialogs = Robolectric.shadowOf_(CustomDialogsShadow.class);
    }

    @After
    public void teardown() {
    }

    @Test
    public void testClick() {
        CustomDialogsShadow mDialogs = new CustomDialogsShadow();
        Assert.assertEquals(CustomDialogsListener.BUTTON_CLICK, mDialogs.onClick());
    }


}
