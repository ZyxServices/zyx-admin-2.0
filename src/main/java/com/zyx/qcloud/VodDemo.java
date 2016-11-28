package com.zyx.qcloud;

import com.zyx.qcloud.Module.Vod;
import com.zyx.qcloud.Utilities.Json.JSONObject;
import com.zyx.qcloud.Utilities.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;
import java.util.TreeMap;


public class VodDemo {
	private static Logger logger= LoggerFactory.getLogger(VodDemo.class);


	public String upload(String filePath,String fileName){
		String fileId=null;
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		Properties property = new Properties();
		try {
			//InputStream in = new BufferedInputStream(new FileInputStream("/qcloud.properties"));
			property.load(this.getClass().getResourceAsStream("/qcloud.properties"));
		}catch (Exception e){
			logger.info("读取腾讯云用户配置文件异常",e);
		}
		config.put("SecretId", property.get("SecretId"));
		config.put("SecretKey", property.get("SecretKey"));
		config.put("RequestMethod", "POST");
		config.put("DefaultRegion", "gz");
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Vod(), config);
		try{
			System.out.println("starting...");
			//String fileName = "d:\\Wildlife.wmv";
			long fileSize = new File(filePath).length();
			String fileSHA1 = SHA1.fileNameToSHA(filePath);
			int fixDataSize = 1024*1024*50;  //每次上传字节数，可自定义
			int firstDataSize = 1024*512;    //最小片字节数（默认不变）
			int tmpDataSize = firstDataSize;
			long remainderSize = fileSize;
			int tmpOffset = 0;
			int code, flag;
			String result = null;
			
			while (remainderSize>0) {
				TreeMap<String, Object> params = new TreeMap<String, Object>();
				params.put("fileSha", fileSHA1);
				params.put("fileType", fileName.split("\\.")[1]);
				params.put("fileName", fileName.split("\\.")[0]);
				params.put("fileSize", fileSize);
				params.put("dataSize", tmpDataSize);
				params.put("offset", tmpOffset);
				params.put("file", filePath);
				params.put("isTranscode",1);//是否转码 0不转 1使用控制中心设在的默认转码方式
				params.put("isScreenshot",1);//是否设在封面 0不设置 1设置
				params.put("isWatermark",1);//设置水印 0不设置  1设置（腾讯云控制中心必须已经上传水印图片）

				result = module.call("MultipartUploadVodFile", params);
				System.out.println(result);
				JSONObject json_result = new JSONObject(result);
				code = json_result.getInt("code");
				if (code == -3002) {               //服务器异常返回，需要重试上传(offset=0, dataSize=512K)
					tmpDataSize = firstDataSize;
					tmpOffset = 0;
					continue;
				} else if (code != 0) {
					return "";
				}
				flag = json_result.getInt("flag");
				if (flag == 1) {
					fileId = json_result.getString("fileId");
					System.out.println(fileId);
					break;
				} else {
					tmpOffset = Integer.parseInt(json_result.getString("offset"));
				}
				remainderSize = fileSize - tmpOffset;
				if (fixDataSize < remainderSize) {
					tmpDataSize = fixDataSize;
				} else {
					tmpDataSize = (int) remainderSize;
				}
			}
			System.out.println("end...");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error...");
		}
		return fileId;
	}
}
