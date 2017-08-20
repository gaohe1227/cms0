package com.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class FfmpegUtil {
	 /**
     * 视频转码
     * @param ffmpegPath    转码工具的存放路径
     * @param upFilePath    用于指定要转换格式的文件,要截图的视频源文件
     * @param codcFilePath    格式转换后的的文件保存路径
     * @param mediaPicPath    截图保存路径
     * @return
     * @throws Exception
     */
    public boolean executeCodecs(String upFilePath,String mediaPicPath,HttpServletRequest request) throws Exception {
    	String basePath =  request.getSession().getServletContext().getRealPath("/tools");
    	String ffmpegPath = basePath +File.separator+"ffmpeg"+File.separator+"ffmpeg.exe";
	    Runtime r=Runtime.getRuntime();
		Process p=null;
      // 创建一个List集合来保存从视频中截取图片的命令
		String cmd = ffmpegPath + " -i "+ upFilePath + " -y -f image2 -ss 12 -t 0.001 -s 800*280 "+ mediaPicPath;
       
        boolean mark = true;
       
        try {
        	p=r.exec(cmd); 
        	
       } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }
   
   

}
// 创建一个List集合来保存转换视频文件为flv格式的命令
//List<String> convert = new ArrayList<String>();
//convert.add(ffmpegPath); // 添加转换工具路径
//convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
//convert.add(upFilePath); // 添加要转换格式的视频文件的路径
//convert.add("-qscale");     //指定转换的质量
//convert.add("6");
//convert.add("-ab");        //设置音频码率
//convert.add("64");
//convert.add("-ac");        //设置声道数
//convert.add("2");
//convert.add("-ar");        //设置声音的采样频率
//convert.add("22050");
//convert.add("-r");        //设置帧频
//convert.add("24");
//convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
//convert.add(codcFilePath);
//List<String> cutpic = new ArrayList<String>();
//cutpic.add(ffmpegPath);
//cutpic.add("-i");
//cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
//cutpic.add("-y");
//cutpic.add("-f");
//cutpic.add("image2");
//cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
//cutpic.add("17"); // 添加起始时间为第17秒
//cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
//cutpic.add("0.001"); // 添加持续时间为1毫秒
//cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
//cutpic.add("800*280"); // 添加截取的图片大小为350*240
//cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
