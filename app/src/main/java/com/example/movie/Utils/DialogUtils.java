package com.example.movie.Utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.movie.R;


/**
 * 加载通用对话框
 *
 */
public class DialogUtils{

	private static Dialog loadingDialog;
	
	//进度
	private static ProgressBar progressBar;
	private static TextView progressContent;
	public static final int MAX = 100;
	/**
	 * 显示加载框
	 * @param context
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context) {  
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_content_layout, null);
		ImageView vLoading = (ImageView) view.findViewById(R.id.dialog_img);
		// 加载动画  
		Animation anim = AnimationUtils.loadAnimation(  
                context, R.anim.dialog_load_rotate_repeat);  
		vLoading.startAnimation(anim);
		loadingDialog = new Dialog(context, R.style.theme_customer_progress_dialog);// 创建自定义样式
		loadingDialog.setContentView(view,new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.MATCH_PARENT,  
                LinearLayout.LayoutParams.MATCH_PARENT));
		loadingDialog.setCancelable(false);// 不可以用“返回键”取消  
        return loadingDialog;
    }  
	/**
	 * 显示加载框
	 * @param context
	 * @param content 提示文本
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context,String content) {  
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_content_layout, null);
		ImageView vLoading = (ImageView) view.findViewById(R.id.dialog_img);
		TextView vContent = (TextView) view.findViewById(R.id.dialog_content);
		vContent.setText(content);
		vContent.setTextColor(Color.BLACK);
		vContent.setVisibility(View.VISIBLE);
		// 加载动画  
		Animation anim = AnimationUtils.loadAnimation(  
                context, R.anim.dialog_load_rotate_repeat);  
		vLoading.startAnimation(anim);
		loadingDialog = new Dialog(context, R.style.theme_customer_progress_dialog);// 创建自定义样式
		loadingDialog.setContentView(view,new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.MATCH_PARENT,  
                LinearLayout.LayoutParams.MATCH_PARENT));
		loadingDialog.setCancelable(false);// 不可以用“返回键”取消  
        return loadingDialog;
    }  
	
	public static Dialog createProgressDialog(Context context) {  
		View view = LayoutInflater.from(context).inflate(R.layout.progressbar_dialog_layout, null);
		progressBar = (ProgressBar) view.findViewById(R.id.progress);
		progressContent = (TextView) view.findViewById(R.id.dialog_content);
		progressContent.setVisibility(View.VISIBLE);
		progressBar.setMax(MAX);
		loadingDialog = new Dialog(context);// 创建自定义样式
		loadingDialog.setContentView(view,new RelativeLayout.LayoutParams(  
				RelativeLayout.LayoutParams.MATCH_PARENT,  
				RelativeLayout.LayoutParams.MATCH_PARENT));
		loadingDialog.setCancelable(false);// 不可以用“返回键”取消  
        return loadingDialog;
    } 
	/**
	 *设置进度
	 * @param progress
	 */
	public static void setProgress(int progress){
		if (progressBar != null) {
			progressBar.setProgress(progress);
			int i = progress/MAX;
			progressContent.setText(String.valueOf(i) + "%");
		}
	}
	/**
	 * 关闭对话框
	 */
	public static void closeLoadingDialog(){
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialog.dismiss();
			loadingDialog = null;
		}
	}
	
}
