package com.haisenhong.nytimes.ui.mvp.main;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.haisenhong.nytimes.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hison7463 on 10/22/16.
 */

public class FilterDialog extends DialogFragment {

    private final String TAG = FilterDialog.class.getSimpleName();

    @BindView(R.id.filter_apply)
    Button apply;
    @BindView(R.id.filter_cancel)
    Button cancel;
    @BindView(R.id.filter_begin_date)
    TextView beginDate;
    @BindView(R.id.filter_sort_order)
    Spinner sortOrder;
    @BindView(R.id.filter_check_art)
    CheckBox artsCheck;
    @BindView(R.id.filter_check_fashion)
    CheckBox fashionCheck;
    @BindView(R.id.filter_check_sports)
    CheckBox sportsCheck;

    private DatePickerDialog dialog;

    private SimpleDateFormat format;
    private SimpleDateFormat displayFormat;
    private Calendar calendar;

    private boolean checkOne;
    private boolean checkTwo;
    private boolean checkThree;
    private String date;
    private int order;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.filter, container, false);
        ButterKnife.bind(this, view);
        if(calendar == null) {
            calendar = Calendar.getInstance();
        }
        format = new SimpleDateFormat("yyyyMMdd");
        displayFormat = new SimpleDateFormat("yyyy/MMM/dd");
        sortOrder.setPrompt("Select sort order");
        initView();
        return view;
    }

    private void initView() {
        if(date != null) {
            beginDate.setText(date);
        }
        else {
            beginDate.setText(displayFormat.format(calendar.getTime()));
        }
        sortOrder.setSelection(order);
        artsCheck.setChecked(checkOne);
        fashionCheck.setChecked(checkTwo);
        sportsCheck.setChecked(checkThree);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @OnClick(R.id.filter_apply)
    public void onClickApply() {
//        String fq = (artsCheck.isChecked()? ("news_desk:" + getString(R.string.arts) + " ") : "")
//                + (fashionCheck.isChecked()? ("news_desk:" + getString(R.string.fashion) + " ") : "")
//                + (sportsCheck.isChecked()? ("news_desk:" + getString(R.string.sports)) : "");
        String temp = (artsCheck.isChecked()? getString(R.string.arts) + " " : "")
                + (fashionCheck.isChecked()? getString(R.string.fashion) + " " : "")
                + (sportsCheck.isChecked()? getString(R.string.sports) : "");
        String fq = null;
        if(temp.length() > 0) {
            fq = "news_desk:(" + temp + ")";
        }
        Log.d(TAG, (fq == null) + "");
        ((MainActivity)getActivity()).onFilterClick(format.format(calendar.getTime()), sortOrder.getSelectedItem().toString(), fq);
        checkOne = artsCheck.isChecked();
        checkTwo = fashionCheck.isChecked();
        checkThree = sportsCheck.isChecked();
        date = beginDate.getText().toString();
        order = sortOrder.getSelectedItemPosition();
        dismiss();
    }

    @OnClick(R.id.filter_cancel)
    public void onClickCancel() {
        dismiss();
    }

    @OnClick(R.id.filter_begin_date)
    public void onClickCalender() {
        Calendar newCalendar = Calendar.getInstance();
        dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                beginDate.setText(displayFormat.format(calendar.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public interface OnFilterClick {
        public void onFilterClick(String beginDate, String sortOrder, String newsDesk);
    }
}
