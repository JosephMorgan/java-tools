package com.dengbin.tools;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Description: 用腾讯邮箱服务器发送邮件
 * @author dengbin
 * @date 2019年1月3日
 * @time 下午2:03:55
 */
public class SendMail {
	public static void main(String[] args) {
		
	}
	
	static void sendMailByQQ(String fromMail,String recipients){
		Properties prop = new Properties();//创建一个Properties文件对象
		prop.put("mail.smtp.host", "smtp.qq.com");//设置邮件服务器的信息，这里设置smtp主机名称
		prop.put("mail.smtp.socketFactory.port", "465");//设置socket factory的端口
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//设置socket factory
		prop.put("mail.smtp.auth", "true");//设置需要身份验证
		prop.put("mail.smtp.port", "25");//设置smtp的端口，QQ邮箱smtp端口是25
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromMail, "uxwqqteqwmrziahf");//第二个参数smtp的授权码
			}
		});//身份验证实现
				
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));//设置发件人的地址
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));//设置收件人邮箱地址
			message.setSubject("有票啦");//设置邮件主题
			MimeBodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("有票啦");//设置邮件正文
			//MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			//String fileName = ".\\target\\surefire-reports\\html\\index.html";//邮件的附件的路径
			// dataSource = new FileDataSource(fileName);//创建一个datasource对象，并传递文件						
			//messageBodyPart2.setDataHandler(new DataHandler(dataSource));//设置handler
			//messageBodyPart2.setFileName(fileName);//加载文件
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(messageBodyPart1);//添加正文内容1
			//multiPart.addBodyPart(messageBodyPart2);//添加正文内容2其实是附件
			message.setContent(multiPart);//设置内容
			Transport.send(message);//发送邮件
			System.out.println("---------------------------------邮件已发送-----------------------------");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	
	}
}