package com.yzk.baselib.tools;

import android.text.InputFilter;
import android.widget.EditText;

public class EditTextUtil {
    public static void disable(EditText editText) {
        editText.setKeyListener(null);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }

    public static void setMaxLength(EditText editText, int maxLength) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }
}
