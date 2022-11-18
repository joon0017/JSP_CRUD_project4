package com.crud.common;

import com.crud.bean.BoardVO;
import com.crud.dao.BoardDAO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class FileUpload {
    public BoardVO uploadPhoto(HttpServletRequest request) {
        String filename = "";
        int sizeLimit = 15 * 1024 * 1024; //15MB

//        String realPath = request.getServletContext().getRealPath("upload");
        String realPath = request.getSession().getServletContext().getRealPath("upload");
        System.out.println("realPath : " + realPath);

        // System.out.println("realPath : " + realPath);
        
        File dir = new File(realPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        BoardVO one = null;
        MultipartRequest multipartRequest = null;
        try {
            multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
            filename = multipartRequest.getFilesystemName("photo");
            one = new BoardVO();
            String seq = multipartRequest.getParameter("seq");
            if (seq != null && !seq.equals("")) {
                one.setSeq(Integer.parseInt(seq));
            }
            one.setCategory(multipartRequest.getParameter("category"));
            one.setTitle(multipartRequest.getParameter("title"));
            one.setWriter(multipartRequest.getParameter("writer"));
            one.setContent(multipartRequest.getParameter("content"));

            if (seq != null && !seq.equals("")) {
                BoardDAO dao = new BoardDAO();
                String oldfilename = dao.getPhotoFilename(Integer.parseInt(seq));
                if (filename != null && oldfilename != null) {
                    FileUpload.deleteFile(request, oldfilename);
                } else if (filename == null && oldfilename != null) {
                    filename = oldfilename;
                }
            }
//            System.out.println("one:" + one.getCategory());
//            System.out.println("filename: " + filename);
            one.setPhoto(filename);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return one;
    }

    public static void deleteFile(HttpServletRequest request, String filename){
        String filePath = request.getSession().getServletContext().getRealPath("upload");
//        String filePath = request.getServletPath();
        File file = new File(filePath+"/"+filename);
        if(file.exists()) file.delete();
    }
}
