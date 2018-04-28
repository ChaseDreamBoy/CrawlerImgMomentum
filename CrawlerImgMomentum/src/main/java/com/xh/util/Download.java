package com.xh.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download {
	
	public static int downLoadFromUrl(String urlStr, String fileName, String saveAddress)  throws IOException {
		URL url;
		InputStream inputStream = null;
		FileOutputStream fos = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(urlStr);
			conn =  (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); 
			conn.setConnectTimeout(15 * 1000);
			// Prevent screen program grabbing and return 403 status.
			//conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// Set whether to read from httpUrlConnection, default true.    
			conn.setDoInput(true);
			inputStream = conn.getInputStream();
			byte[] getData = readInputStream(inputStream);
			// File save address.
			File saveDir = new File(saveAddress);
			if (!saveDir.exists()) {
				boolean isSave = saveDir.mkdirs();
				if (!isSave) {
					throw new IOException("create save path error!");
				}
			}
			File file = new File(saveDir + File.separator + fileName);
			fos = new FileOutputStream(file);
			fos.write(getData);
			return 1;
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			try {
				if (fos != null) {
					fos.flush();;
				}
				if (fos != null) {
					fos.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				throw new IOException(e);
			}
		}
	}
	
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				throw new IOException(e);
			}
		}
		return bos.toByteArray();
	}
	
	public static void main(String[] args) {

		try {
			Download.downLoadFromUrl("https://farm9.staticflickr.com/8499/8338439169_660102b693_k.jpg", "mytest.jpg", "F:\\myResource\\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
