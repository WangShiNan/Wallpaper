package com.brother7.wallpaper.tool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * �Ի�ȡ��json���д���������ȡ���е�_src��_id������
 **/

public class GetJSON {

	private List<String> id = new ArrayList<String>();
	private List<String> src = new ArrayList<String>();
	private List<ResponseString> responseList = new ArrayList<ResponseString>();
	private void getId(String s) throws Exception
	{

		JSONTokener jsonTokener = new JSONTokener(s);
		for(int i = 0; i <18;i ++)
		{
			jsonTokener.skipPast("_id\": \""); 
			id.add(jsonTokener.next(24));
		}
	}
	private void getSrc(String s) throws Exception
	{

		JSONTokener jsonTokener = new JSONTokener(s);
		for(int i = 0; i <18;i ++)
		{
			jsonTokener.skipPast("img\": \""); 
			src.add(jsonTokener.next(24));
		}
	}
	
	public List<ResponseString> getJ(String s) throws Exception
	{
		this.getSrc(s);
		this.getId(s);
		for(int i = 0; i < 18; i++)
		{
			ResponseString responseString = new ResponseString();
			responseString.putSrc(src.get(i));
			responseString.putId(id.get(i));
			responseList.add(responseString);
		}
		
		return responseList;	
	}
}
