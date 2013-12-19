package com.brother7.wallpaper.tool;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

/*
 * 加载图片
 * */

public class GetImages extends AsyncTask<String, Void, Bitmap>{

	private String url;
	private final static String localPath    
    = Environment.getExternalStorageDirectory() + "/bizhiku/";
	private  final static int Buffer = 8 * 1024;
	private ImageView imageView;
	private Context context;
	private GetImageFromSDCard getImageFromSDCard = new GetImageFromSDCard();
	private ProgressDialog progressDialog;
	private boolean isSRC;
	private String urlString;
	//public GetImages(String url)
	public GetImages(ImageView imageView,Context context, boolean isSRC)
	{
		//this.url = url;
		File localFile = new File(localPath);
		this.imageView = imageView;
		this.context = context;
		this.isSRC = isSRC;
		if(isSRC)
		{
			urlString = "http://img0.androidesk.com/wallpaper?imgid=";   //true大图
			//urlString = "http://img0.androidesk.com/download/"; 
		}
		else
		{
			urlString = "http://img0.androidesk.com/download/";  //false小图
		}
		if(!localFile.exists())
			localFile.mkdir();
	}
	@Override
	protected Bitmap doInBackground(String... params){
		// TODO Auto-generated method stub
		url = params[0];
		try
		{
			//http://img0.androidesk.com/download/517b4dc948d5b95bf7cc9230
			URL url = new URL(urlString + params[0]);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			DataInputStream in = new DataInputStream(httpURLConnection.getInputStream());
			//bitmap = BitmapFactory.decodeStream(in);
			FileOutputStream out = new FileOutputStream(localPath + params[0] + ".jpg");
			byte[] buffer = new byte[Buffer];
		
			int len;
		
			while((len = in.read(buffer)) >= 0)
			{
				out.write(buffer, 0, len);
				out.flush();
			}
			out.close();
			in.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		return null;
	}
	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		//super.onPostExecute(result);
		imageView.setImageBitmap(new GetImageFromSDCard().getImage(localPath + url + ".jpg"));
		progressDialog.dismiss();		
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progressDialog = new ProgressDialog(context);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("载入中…");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}
	
	

	
}
