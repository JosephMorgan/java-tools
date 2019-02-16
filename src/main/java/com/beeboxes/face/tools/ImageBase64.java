package com.beeboxes.face.tools;
//package com.beeboxes.face.Tools;
///**
// * Description: 图片base64加密
// */
//import java.awt.Graphics;
//import java.awt.GraphicsConfiguration;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
//import java.awt.HeadlessException;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.Transparency;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Base64;
//import java.util.Optional;
//
//import javax.annotation.Resource;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//
//
//import com.beeboxes.white.config.FaceImageProperties;
//
//import lombok.NonNull;
//import lombok.experimental.UtilityClass;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//
///**
// * 文件处理
// * @author tianzc
// */
//
//public class ImageBase64 {
//    /**
//     * Base64编码字符串格式化（移除换行）正则表达式
//     */
//    public static final String BASE64_FORMAT_REGEX = "[\n\r]*";
//
////    public static void main(String[] args) {
////        try {
//////            String str = "/data/pic/WechatIMG396.png";
////            String str = "/data/pic/crop/396.jpeg";
////            getBufferedImageByFilePath("/data/pic/xzc.png");
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//    /**
//     * 通过文件地址(imgFilePath)获取图片Base64编码字符串
//     * @param imgFilePath 文件地址
//     * @return 图片Base64编码字符串
//     */
//    public static String getImageBase64Str(String imgFilePath) throws IOException {
//        // imgFile待处理的图片地址
//        String imgBase64 = getImageBase64Str(new File(imgFilePath));
//        return imgBase64;
//    }
//
//    /**
//     * 通过文件(File)获取图片Base64编码字符串
//     * @param imgFile 文件
//     * @return 图片Base64编码字符串
//     */
//    public static String getImageBase64Str( File imgFile) throws IOException {
//        // imgFile待处理的图片地址
//        String imgBase64;
//        //读取图片字节数组
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(imgFile);
//            imgBase64 = getImageBase64Str(inputStream);
//        } catch (FileNotFoundException e) {
//            throw e;
//        } catch (IOException e) {
//            throw e;
//        } finally {
//            IOUtils.closeQuietly(inputStream);
//        }
//        return imgBase64;
//    }
//
//    /**
//     * 通过文件输入流(inputStream)获取图片Base64编码字符串
//     * @param inputStream 文件输入流
//     * @return 图片Base64编码字符串
//     */
////    public static String getImageBase64Str(InputStream inputStream) throws IOException {
////        byte[] data;
////        //读取图片字节数组
////        if (0 == inputStream.available()) {
////            // --------文件内容为空-------
////            throw new NullPointerException("--文件输入流为空");
////        }
////        data = readInputStream(inputStream);
////        return Base64.getEncoder().encodeToString(data);
////    }
//
//    /**
//     * 通过文件链接地址(imgFileUrl)远程获取图片Base64编码字符串
//     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
//     * @param imgFileUrl 图片链接地址
//     * @return 图片Base64编码字符串
//     */
////    public static String getImageStrRemote(String imgFileUrl) throws IOException {
////        byte[] data;
////        InputStream inputStream = null;
////        //读取图片字节数组  
////        try {
////            //new一个URL对象
////            URL url = new URL(imgFileUrl);
////            //打开链接
////            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
////            //通过输入流获取图片数据
////            inputStream = conn.getInputStream();
////            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
////            data = readInputStream(inputStream);
////        }catch (Exception e){
////            // --Remote图片转化成base64字符串异常;
////            throw e;
////        } finally {
////            IOUtils.closeQuietly(inputStream);
////        }
////        return Base64.getEncoder().encodeToString(data);
////    }
//
//    /**
//     * base64字符串转化成图片
//     * @param imgBase64 图片Base64编码字符串
//     * @param prefix 图片名前缀
//     * @param suffix 图片后缀名
//     * @param tempDir 输出图片文件目录
//     * @return 图片文件路径
//     */
////    public static String generateImage( String imgBase64, String prefix,String suffix,  String tempDir) throws IOException {
////        String imgFilePath = null;
////        // Base64解码
////        byte[] data = Base64.getDecoder().decode(imgBase64);
////        imgFilePath = generateImage(data, prefix, suffix, tempDir);
////        return imgFilePath;
////    }
//
//    /**
//     * base64字符串转化成图片
//     * 对字节数组字符串进行Base64解码并生成图片
//     * @param b 图片byte[]
//     * @param prefix 图片名前缀
//     * @param suffix 图片后缀名
//     * @param tempDir 输出图片文件目录
//     * @return 图片文件路径
//     */
////    public static String generateImage(byte[] b,String prefix,String suffix,String tempDir) throws IOException {
////        String imgFilePath = tempDir + File.separator + prefix + "." + suffix;
////        OutputStream outputStream = null;
////        try {
////            File dir = new File(tempDir);
////            // 校验文件是否存在, 并且是目录
////            if (!dir.exists() && !dir.isDirectory()) {
////                boolean mkdirsFlag = dir.mkdirs();
////                if (!mkdirsFlag) {
////                 // ------文件目录创建失败--------
////                 throw new NullPointerException("--文件目录创建失败");
////                }
////            }
////            for (int i = 0; i < b.length; ++i) {
////                // 调整异常数据
////                if (b[i] < 0) {
////                    b[i] += 256;
////                }
////            }
////            // 新生成的图片
////            outputStream = new FileOutputStream(imgFilePath);
////            outputStream.write(b);
////            outputStream.flush();
////        } catch (IOException e){
////            // --base64字符串转化成图片异常;
////            throw e;
////        } finally {
////            IOUtils.closeQuietly(outputStream);
////        }
////        return imgFilePath;
////    }
//
//    /**
//     * 根据base64获取byte[]
//     * @param imgBase64
//     * @return
//     */
//    public static byte[] getByteByImgBase64(String imgBase64) {
//        // Base64解码
//        byte[] b = Base64.getDecoder().decode(imgBase64);
//        return b;
//    }
//
//    /**
//     * 根据IO获取byte[]
//     * @param inputStream
//     * @return
//     * @throws IOException
//     */
////    public static byte[] readInputStream(InputStream inputStream) throws IOException {
////        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
////        // 创建一个Buffer字符串
////        byte[] buffer = new byte[1024];
////
////        byte[] data;
////        try {
////            if (0 == inputStream.available()) {
////                // --------文件内容为空-------
////                throw new NullPointerException("--文件输入流为空");
////            }
////            // 每次读取的字符串长度，如果为-1，代表全部读取完毕
////            int len;
////            // 使用一个输入流从buffer里把数据读取出来
////            while( -1 != (len = inputStream.read(buffer))){
////                // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
////                outStream.write(buffer, 0, len);
////            }
////            // 把outStream里的数据写入内存
////            data = outStream.toByteArray();
////        } catch (IOException e) {
////            // --readInputStream获取byte异常
////            throw e;
////        } finally {
////            IOUtils.closeQuietly(outStream);
////        }
////        return data;
////    }
//
//    /**
//     * 通过base64字符串，获取BufferedImage
//     * @param imgBase64 图片Base64编码字符串
//     * @return BufferedImage对象
//     */
////    public static Optional<BufferedImage> getBufferedImage( String imgBase64) {
////        BufferedImage bi;
////        byte[] data = Base64.getDecoder().decode(imgBase64);
////        // 获取输入流
////        InputStream inputStream = null;
////        try {
////            // --获取bufferedImage, ImageIO.read
////            inputStream = new ByteArrayInputStream(data);
////            bi = ImageIO.read(inputStream);
////        } catch (IOException e) {
////            // --bi = ImageIO.read()异常参数：{}\n\t{}",imgBase64,e.getMessage())
////            try {
////                // --获取bufferedImage, toBufferedImage
////                Image srcImg = Toolkit.getDefaultToolkit().createImage(data);
////                bi = toBufferedImage(srcImg);
////            }catch (Exception ea){
////                throw ea;
////            }
////        } finally {
////            IOUtils.closeQuietly(inputStream);
////        }
////        return Optional.ofNullable(bi);
////    }
//
//    /**
//     * 获取BufferedImage，通过文件路径
//     * @param imageFilePath 图片文件路径
//     * @return BufferedImage对象
//     */
//    public static Optional<BufferedImage> getBufferedImageByFilePath(String imageFilePath) {
//        BufferedImage bi = null;
//        try {
//            // --获取bufferedImage, ImageIO.read
//            File file = new File(imageFilePath);
//            if (file.isFile()) {
//                bi = ImageIO.read(file);
//            }
//        } catch (Exception e) {
//            // --bi = ImageIO.read()异常参数：{}\n\t{}", imageFilePath, e.getMessage())
//            try {
//                // --获取bufferedImage, toBufferedImage
//                Image srcImg = Toolkit.getDefaultToolkit().getImage(imageFilePath);
//                bi = toBufferedImage(srcImg);
//            } catch (Exception ea) {
//                throw ea;
//            }
//        }
//        return Optional.ofNullable(bi);
//    }
//
//    /**
//     * 通过图片文件，获取BufferedImage
//     * @param imgFile 图片文件
//     * @return BufferedImage对象
//     */
//    public static Optional<BufferedImage> getBufferedImage(File imgFile) throws IOException {
//        Optional<BufferedImage> bi = getBufferedImage(new FileInputStream(imgFile));
//        return bi;
//    }
//
//    /**
//     * 通过InputStream字符串，获取BufferedImage
//     * @param inputStream 图片输入流
//     * @return BufferedImage对象
//     */
//    public static Optional<BufferedImage> getBufferedImage(InputStream inputStream) throws IOException {
//        BufferedImage bi = null;
//        try {
//            // --通过ImageIO.read(inputStream)获取bufferedImage
//            bi = ImageIO.read(inputStream);
//        } catch (Exception e) {
//            // bi = ImageIO.read(srcImageFile)获取异常", e.getMessage())
//            try {
//                // --通过Toolkit.getDefaultToolkit()-toBufferedImage获取bufferedImage
//                Image srcImg = Toolkit.getDefaultToolkit().createImage(readInputStream(inputStream));
//                bi = toBufferedImage(srcImg);
//            }catch (Exception ea){
//                throw ea;
//            }
//        }
//        return Optional.ofNullable(bi);
//    }
//    
//    /**
//     * 校验文件像素大小
//     * @param file
//     * @return
//     * @throws Exception
//     */
////    public boolean checkImagePixelFile(File file,int width,int height) throws Exception{
////        boolean flag = false;
////        InputStream inputStream = null;
////        try {
////            inputStream = new FileInputStream(file);
////            flag = checkImagePixelFile(inputStream,width,height);
////        } catch (Exception e) {
////            log.error("图片处理失败：{}", e);
////            throw e;
////        } finally {
////            IOUtils.closeQuietly(inputStream);
////        }
////        return flag;
////    }
//
//    private boolean checkImagePixelFile(InputStream inputStream,int width,int height){
//        boolean flag = false;
//        try {
//            Optional<BufferedImage> image = ImageBase64.getBufferedImage(inputStream);
//            int widthResult = image.get().getWidth();
//            int heightResult = image.get().getHeight();
//            if (image.isPresent()) {
//                if (width>widthResult || height>heightResult) {
//                    //log.info("图片最小像素({}x{})校验失败，图片像素 width：{}，height：{}",width,height, image.get().getWidth(), image.get().getWidth());
//                } else {
//                    return true;
//                }
//            }
//        } catch (Exception e) {
//            //log.error("图片处理失败：{}", e);
//        }
//        return flag;
//    }
//
//    /**
//     * Image 转 BufferedImage
//     * @param image Image对象
//     * @return 返回BufferedImage
//     */
//    public static BufferedImage toBufferedImage(Image image) {
//        if (image instanceof BufferedImage) {
//            return (BufferedImage)image;
//        }
//        // This code ensures that all the pixels in the image are loaded
//        image = new ImageIcon(image).getImage();
//
//        // Determine if the image has transparent pixels; for this method's
//        // implementation, see e661 Determining If an Image Has Transparent Pixels
//        // boolean hasAlpha = hasAlpha(image);
//
//        // Create a buffered image with a format that's compatible with the screen
//        BufferedImage bimage = null;
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        try {
//            // Determine the type of transparency of the new buffered image
//            int transparency = Transparency.OPAQUE;
//           /* if (hasAlpha) {
//                 transparency = Transparency.BITMASK;
//             }*/
//
//            // Create the buffered image
//            GraphicsDevice gs = ge.getDefaultScreenDevice();
//            GraphicsConfiguration gc = gs.getDefaultConfiguration();
//            bimage = gc.createCompatibleImage(
//                    image.getWidth(null), image.getHeight(null), transparency);
//        } catch (HeadlessException e) {
//            // The system does not have a screen
//            throw e;
//        }
//        if (bimage == null) {
//            // Create a buffered image using the default color model
//            int type = BufferedImage.TYPE_INT_RGB;
//            //int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
//            /*if (hasAlpha) {
//                 type = BufferedImage.TYPE_INT_ARGB;
//             }*/
//            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
//        }
//        // Copy image to buffered image
//        Graphics g = bimage.createGraphics();
//        // Paint the image onto the buffered image
//        g.drawImage(image, 0, 0, null);
//        g.dispose();
//        return bimage;
//    }
//    
//
//}
