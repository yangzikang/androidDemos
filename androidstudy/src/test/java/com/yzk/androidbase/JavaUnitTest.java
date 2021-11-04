package com.yzk.androidbase;

import android.util.Log;

import com.yzk.baselib.tools.LogUtil;

import org.junit.Test;

public class JavaUnitTest {
    @Test
    public void stringPlusNull() {
        String append = null;
        System.out.println("String" + append);
    }
}
