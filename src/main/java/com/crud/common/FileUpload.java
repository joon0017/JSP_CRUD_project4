package com.crud.common;

import com.crud.bean.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class FileUpload {
    public BoardVO uploadPhoto(HttpServletRequest request) {
        String filename = "";
        int sizeLimit = 15 * 1024 * 1024; //15MB

        String realPath = request.getSession().getServletContext().getRealPath("/resources/upload");
        // System.out.println("realPath : " + realPath);
        
        File dir = new File(realPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        BoardVO one = null;
        MultipartRequest multipartRequest = null;
        try{
            multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
            filename = multipartRequest.getFilesystemName("photo");

            one = new BoardVO();
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    
    }
}
