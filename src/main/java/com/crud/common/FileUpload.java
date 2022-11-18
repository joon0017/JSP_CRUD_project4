package com.crud.common;

import com.crud.bean.BoardVO;
import com.crud.dao.BoardDAO;
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
            String sid = multipartRequest.getParameter("seq");
            if(sid!=null&&sid.equals("")) one.setSeq(Integer.parseInt(sid));
            one.setCategory(multipartRequest.getParameter("category"));
            one.setTitle(multipartRequest.getParameter("title"));
            one.setWriter(multipartRequest.getParameter("writer"));
            one.setContent(multipartRequest.getParameter("content"));

            if(sid!=null&&sid.equals("")){
                BoardDAO dao = new BoardDAO();
                String oldfilename = dao.getPhotoFilename(Integer.parseInt(sid));
                if (filename!=null && oldfilename!=null) FileUpload.deleteFile(request, oldfilename);
                else if(filename==null && oldfilename!=null) filename = oldfilename;
            }
            one.setFilename(filename);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return one;
    }

    public static void deleteFile(HttpServletRequest request, String filename){
        String filePath = request.getSession().getServletContext().getRealPath("upload");
        File file = new File(filePath+"/"+filename);
        if(file.exists()) file.delete();
    }
}
