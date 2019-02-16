package com.dengbin.tools;

import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * Description: 检查图片的完整性
 * @author dengbin
 * @date 2018年12月4日
 * @time 下午10:18:45
 */
public class PicCheck {

    
    public static void main(String[] args){
        int count = 0;
        File dir = new File("C:/Users/bin/Desktop/a/");
        for(File file: dir.listFiles()){
            if(!isJPEG(file.getAbsolutePath())){
                System.out.println(file.getName());
                file.delete();
                count++;
            }
        }
        System.out.println(count);
    }

    public static Boolean isJPEG(String fileName) {
        boolean canRead = false;
        try(ImageInputStream iis = ImageIO.createImageInputStream(new File(fileName))){
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");//仅列出image/jpeg的例子
            while (readers.hasNext()) {
                ImageReader reader = readers.next();
                reader.setInput(iis);
                reader.read(0);
                canRead = true;
                break;
            }
        }catch (Exception e){

        }
        return canRead;
    }

}