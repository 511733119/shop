package cn.it.shop.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.it.shop.model.FileImage;

//实现文件上传的工具类
@Component("fileUpload")
public class FileUploadUtil implements FileUpload {

	@Value("#{prop.basePath+prop.filePath}")
	private String filePath ;
	@Value("#{prop.basePath+prop.bankPath}")
	private String bankPath ;
//	
//	public void setFilePath(String filePath) {
//		System.out.println("filepath:"+filePath);
//		this.filePath = filePath;
//	}
//	
	public String[] getBankImage(){
		String[] images = new File(bankPath).list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".gif");
			}
		});
		return images;
	}
	//通过文件名获取扩展名
	private String getFileExt(String fileName){
		return FilenameUtils.getExtension(fileName);
	}
	
	//生成UUID随机数，作为新的文件名
	private String newFileName(String fileName){
		String ext = getFileExt(fileName);
		return UUID.randomUUID().toString()+"."+ext;
	}
	
	//实现文件上传的功能，返回上传后新的文件名
	/* (non-Javadoc)
	 * @see cn.it.shop.util.FileUpload#uploadFile(cn.it.shop.model.FileImage)
	 */
	@Override
	public String uploadFile(FileImage fileImage){
		//获取新唯一文件名
		String pic = newFileName(fileImage.getFilename());
		try {
			FileUtil.copyFile(fileImage.getFile(), new File(filePath,pic));
			return pic;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			fileImage.getFile().delete();
		}
		
	}
}
