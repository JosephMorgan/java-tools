package com.beeboxes.face.tools;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


import java.io.OutputStream;


import java.util.Base64;

import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

/**
 * 
 *
 */
public class ImgBase64 {
	public static void main(String[] args) {

//		String filePath = "C:/PicPath.csv";
//		
//		ArrayList<String[]> dataArrayList = ReadCSV.readCSVFile(filePath);
//		for(int row = 0 ; row < dataArrayList.size();row++) {
//			//System.out.println(dataArrayList.get(row)[0]);
//			String strImg = getImageStr(dataArrayList.get(row)[0]);
//			writeStringToFile("C:/file1.txt", strImg);
//		}
		String strImg3 = getImageStr("C:/HU7201/a29.jpg");
		//String strImg2=strImg.replaceAll("\n", "");
		//String strImg3=strImg2.replaceAll("\r", "");
		System.out.println(strImg3);
		//String strImg1 = "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";
		//generateImage(strImg1, "C:/86619-107.jpg");
	}
	
     /**
	 * @Description: 将base64编码字符串转换为图片,不过需要注意的是，一般插件返回的base64编码的字符串都是有一个
	 * 前缀的。"data:image/jpeg;base64," 解码之前这个得去掉。
	 * @param imgStr base64编码字符串
	 * @param path 图片路径-具体到文件
	*/
	public static boolean generateImage(String imgStr, String path) {
	if (imgStr == null) {
	return false;
	}
	BASE64Decoder decoder = new BASE64Decoder();
	try {
	//解密
	byte[] b = decoder.decodeBuffer(imgStr);
	//处理数据
	for (int i = 0; i < b.length; ++i) {
	if (b[i] < 0) {
	b[i] += 256;
	}
	}

	OutputStream out = new FileOutputStream(path);
	out.write(b);
	out.flush();
	out.close();
	return true;
	} catch (IOException e) {
	e.printStackTrace();
	return false;
	}
	}

	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author:
	 * @CreateTime:
	 * @return
	 */
	public static String getImageStr(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		//BASE64Encoder encoder = new BASE64Encoder();
		//return encoder.encode(data);
		return Base64.getEncoder().encodeToString(data);
		
	}
	
	/**
	 * @Description: 将字符串写到文件中
	 */
	
	public static void writeStringToFile(String filePath,String content) {
		try {
			FileWriter fw = new FileWriter(filePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content+"\r\n");
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	


}
