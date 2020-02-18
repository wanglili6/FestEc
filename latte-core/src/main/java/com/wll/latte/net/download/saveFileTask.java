package com.wll.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.wll.latte.app.Latte;
import com.wll.latte.net.callback.IRequest;
import com.wll.latte.net.callback.ISuccess;
import com.wll.latte.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author wanglili
 * @description:
 * @date : 2020-02-18 15:30
 */
public class saveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest iRequest;
    private final ISuccess iSuccess;

    public saveFileTask(IRequest iRequest, ISuccess iSuccess) {
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];//请求体
        String name = (String) params[3];//请求体
        final InputStream inputStream = body.byteStream();

        if (null == downloadDir || downloadDir.equals("")) {
//            指定下载目录
            downloadDir = "down_loads";
        }
        if (null == extension || extension.equals("")) {
            extension = "";
        }
        if (null == name) {
            //没有完整的文件名
            return FileUtil.writeToDisk(inputStream, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(inputStream, downloadDir, name);
        }
    }

    /**
     * 异步执行完成，返回主线程
     *
     * @param file
     */
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (null != iSuccess) {
            iSuccess.onSuccess(file.getPath());
        }
        if (null != iRequest) {
            iRequest.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        //判断是否是apk文件
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }

    }
}
