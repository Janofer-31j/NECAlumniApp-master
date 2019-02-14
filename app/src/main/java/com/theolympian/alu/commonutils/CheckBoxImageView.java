package com.theolympian.alu.commonutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.theolympian.alu.R;


public class CheckBoxImageView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {
    boolean checked;
    int defImageRes;
    int checkedImageRes;
    OnCheckedChangeListener onCheckedChangeListener;

    public CheckBoxImageView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init(attr, defStyle);
    }

    public CheckBoxImageView(Context context, AttributeSet attr) {
        super(context, attr);
        init(attr, -1);
    }

    public CheckBoxImageView(Context context) {
        super(context);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        this.setImageResource(checked ? checkedImageRes : defImageRes);
    }

    @SuppressLint("ResourceType")
    private void init(AttributeSet attributeSet, int defStyle) {
        TypedArray a = null;
        if (defStyle != -1)
            a = getContext().obtainStyledAttributes(attributeSet, R.styleable.CheckBoxImageView, defStyle, 0);
        else
            a = getContext().obtainStyledAttributes(attributeSet, R.styleable.CheckBoxImageView);
        defImageRes = a.getResourceId(0, R.drawable.check_unchecked);
        checkedImageRes = a.getResourceId(1, R.drawable.check_checked);
        checked = a.getBoolean(2, false);
        a.recycle();
        this.setImageResource(checked ? checkedImageRes : defImageRes);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        checked = !checked;
        this.setImageResource(checked ? checkedImageRes : defImageRes);
        onCheckedChangeListener.onCheckedChanged(this, checked);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public static interface OnCheckedChangeListener {
        void onCheckedChanged(View buttonView, boolean isChecked);
    }
}