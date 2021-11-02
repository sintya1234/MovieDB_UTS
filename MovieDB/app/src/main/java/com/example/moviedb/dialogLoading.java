package com.example.moviedb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

import com.example.moviedb.view.fragments.NowPlayingFragment;

public class dialogLoading {
   private Fragment fragment;
   private AlertDialog dialog;
    //private Context context;


    public dialogLoading(Fragment myfragment){
        fragment = myfragment;
    }
    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
        LayoutInflater inflater = fragment.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }
    public void dismissDialog(){
            dialog.dismiss();
        }
    }

