package com.sakebook.android.dialoghelper;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

/**
 * Created by sakemotoshinya on 2014/08/09.
 */
@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class DialogHelperTest {

    Activity mActivity;

    @Before
    public void setup() {
        this.mActivity = new Activity();
    }

    @After
    public void teardown() {
    }

    @Test
    public void testCheckInstance() {

        DialogHelper.Builder builder = DialogHelper.create(mActivity);
        assertNotNull(builder);

        DialogHelper.CustomBuilder customBuilder = DialogHelper.customCreate(mActivity);
        assertNotNull(customBuilder);
    }

    @Test
    public void testCheckBuild() {

        DialogHelper.Builder builder = DialogHelper.create(mActivity);

        try {
            builder.build();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCheckImplements() {
        assertFalse(mActivity instanceof FragmentActivity);
    }


    @Test
    public void testCheckBuilderParam() {
        DialogHelper.Builder builder = DialogHelper.create(mActivity);
        assertSame(builder, builder.setTitle(""));
        builder.build();
    }

}

