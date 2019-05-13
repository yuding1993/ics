package org.wheport.ics.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@SuppressWarnings("serial")
public class VerifyCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	  
		    throws ServletException, IOException {
		  
		        this.doPost(request, response);  
		  
		    }  
		  
		    // 生成数字和字母的验证码    
		  
		    public void doPost(HttpServletRequest request, HttpServletResponse response)
		  
		    throws ServletException, IOException {
		        BufferedImage img = new BufferedImage(68, 22,  
		  
		        BufferedImage.TYPE_INT_RGB);  
		  
		        // 得到该图片的绘图对象    
		  
		        Graphics g = img.getGraphics();  
		  
		        Random r = new Random();  
		  
		        Color c = new Color(210, 214, 200);  
		  
		        g.setColor(c);  
		  
		        // 填充整个图片的颜色    
		  
		        g.fillRect(0, 0, 68, 22);  
		  
		        // 向图片中输出数字和字母    
		  
		        StringBuffer sb = new StringBuffer();  
		  
		        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
		  
		        int index, len = ch.length;  
		  
		        for (int i = 0; i < 4; i++) {  
		  
		            index = r.nextInt(len);  
		  
		            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt  
		  
		            (255)));  
		  
		            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
		            // 输出的  字体和大小                      
		  
		            g.drawString("" + ch[index], (i * 15) + 3, 18);  
		            //写什么数字，在图片 的什么位置画    
		  
		            sb.append(ch[index]);  
		  
		        }  
		  
		        request.getSession().setAttribute("complaintCaptcha", sb.toString());  
		        
		        // 禁止图像缓存。    
		        response.setHeader("Pragma", "no-cache");    
		        response.setHeader("Cache-Control", "no-cache");    
		        response.setDateHeader("Expires", 0);    
		    
		        response.setContentType("image/jpeg");  
		  
		        ImageIO.write(img, "jpeg", response.getOutputStream());  
		  
		    }  
}
