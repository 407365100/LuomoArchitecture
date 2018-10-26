package com.luomo.commonsdk.widget.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.luomo.base.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义的 异常处理类 , 实现了 UncaughtExceptionHandler接口
 * 
 * @author renpan
 */
public class CrashHandler implements UncaughtExceptionHandler {

	private static final String TAG = "e-zhantu";

	private static CrashHandler mCrashHandler;
	private Context mContext;
	private SimpleDateFormat mSimpleDateFormat;

	// 私有构造方法
	private CrashHandler() {

	}

	// 单例异常处理类
	public static synchronized CrashHandler getInstacnce() {

		if (mCrashHandler == null) {
			mCrashHandler = new CrashHandler();
		}
		return mCrashHandler;

	}

	public void init(Context mContext) {
		this.mContext = mContext;
		this.mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Log.e(TAG, "honey ,exception  protected --- ");
	}

	// 异常处理回调方法
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e(TAG, "honey ,exception start --- ");

		// 1.获取当前程序的版本号. 版本的id
		String versioninfo = getVersionInfo();

		// 2.获取手机的硬件信息.
		String mobileInfo = getMobileInfo();

		// 3.把错误的堆栈信息 获取出来
		String errorinfo = getErrorInfo(ex);

		// 4.异常时间
		String expTime = mSimpleDateFormat.format(new Date());

		String pMessage = "异常时间 ：\n" + expTime + "\n异常版本  ： \n" + versioninfo + "\n手机信息 ： \n" + mobileInfo + "\n错误信息 ：\n" + errorinfo;

		Log.d(TAG, pMessage);

		// 处理问题信息
		handleCrashMsg(pMessage, expTime);

		// 给出一定时间存日志
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Log.e(TAG, "honey ,exception finish --- ");
		// 干掉当前的程序
		System.exit(0);
//		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * 显示信息
	 */
	private void handleCrashMsg(final String pMessage, final String expTime) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(mContext, "安全应用出现未知错误（环境异常）", Toast.LENGTH_LONG).show();
				String fileName = expTime + ".txt";
				File file = new File(Constants.PATH_DIRECTORY_LOG, fileName);
				// 如果文件夹不存在，则创建
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				try {
					FileOutputStream fos = new FileOutputStream(file, true);
					fos.write(pMessage.getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
				}
				Looper.loop();
				System.exit(0);
			}
		}).start();
	}

	/**
	 * 获取错误的信息
	 * 
	 * @param arg1
	 * @return
	 */
	private String getErrorInfo(Throwable arg1) {
		Writer writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		arg1.printStackTrace(pw);
		pw.close();
		return writer.toString();
	}

	/**
	 * 获取手机的硬件信息
	 * 
	 * @return
	 */
	private String getMobileInfo() {
		StringBuilder sb = new StringBuilder();
		// 通过反射获取系统的硬件信息
		try {
			Field[] fields = Build.class.getDeclaredFields();
			for (Field field : fields) {
				// 暴力反射 ,获取私有的信息
				field.setAccessible(true);
				String name = field.getName();
				String value = field.get(null).toString();
				sb.append(name + "=" + value);
				sb.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 获取手机的版本信息
	 * 
	 * @return
	 */
	private String getVersionInfo() {
		try {
			PackageManager pm = mContext.getPackageManager();
			PackageInfo info = pm.getPackageInfo(mContext.getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "版本号未知";
		}
	}
}