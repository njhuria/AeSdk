package com.adelement.aesdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

//import com.adelement.listinstalledapp.AllAppsActivity;
//import com.adelement.listinstalledapp.ApplicationAdapter;
//import com.adelement.listinstalledapp.R;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;

public class MyHttpAsyncTask extends AsyncTask<String, Void, String> {
	private List<ApplicationInfo> applist = null;
	private PackageManager packageManager = null;
	private Map msg;
	public MyHttpAsyncTask(Map message) {
		this.msg=message;
	}
	@Override
	protected String doInBackground(String... s) {
		try{
			Map map = new HashMap();
			map.put("id", Build.ID);
			map.put("info", msg);
			//applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
			//map.put("applist", applist);
			HttpClient httpclient = new DefaultHttpClient();
        
			String url ="http://192.168.1.103:8090";
			// 2. make POST request to the given URL
			HttpPost httpPost = new HttpPost(url);

			StringEntity se = new StringEntity(map.toString());
			httpPost.setEntity(se);

			httpPost.setHeader("Accept", "application/json");

			httpPost.setHeader("Content-type", "application/json");
			
			
			//HttpResponse httpResponse = httpclient.execute(httpPost);
			httpclient.execute(httpPost);
		}catch(Exception e){

		}
		return "OK";
	}
	private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
		ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
		for (ApplicationInfo info : list) {
			try {
				if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
					applist.add(info);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;	}
}

