package com.brother7.wallpaper.tool;

import java.io.File;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

/*
 * ����ͼƬ�����ƴ�SD���л�ȡͼƬ*/
public class GetImageFromSDCard {
	
	public Bitmap getImage(String url)
	{
		File imagePath = new File(url);
		Bitmap bitmap = null;
		if(imagePath.exists())
		{
		//System.out.println("Get localImage!");
		//System.out.println(localPath + url + ".jpg");
			bitmap = BitmapFactory.decodeFile(url);
		
			return bitmap;
		}
		return null;
	}

}
