package com.brother7.wallpaper.tool;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class CheckTheIntenet {
	//¼ì²éÍøÂçÁ¬½Ó×´Ì¬£¬Monitor network connections (Wi-Vi, GPRS, UMTS, etc.)
	public static boolean IsHaveInternet(final Context context) {

		try {

			ConnectivityManager manger = (ConnectivityManager)

			context.getSystemService(Context.CONNECTIVITY_SERVICE);

			NetworkInfo info = manger.getActiveNetworkInfo();

			return (info != null && info.isConnected());

		} catch (Exception e) {

			return false;

		}

	}

}
