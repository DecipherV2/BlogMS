package tn.esprit.msblogrecycle.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.msblogrecycle.Entity.Blog;
import tn.esprit.msblogrecycle.Repository.BlogRepsitory;

import javax.servlet.ServletContext;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import io.micrometer.core.ipc.http.HttpSender.Response;

@Service
public class BlogServiceImp implements IBlogServic{

    @Autowired
    private BlogRepsitory blogRepsitory;
    @Autowired
    ServletContext context;

    @Override
    public ResponseEntity<Response> add(MultipartFile file, String Blog) throws JsonParseException, JsonMappingException, Exception
    {

        System.out.println("Ok .............");
        Blog s = new ObjectMapper().readValue(Blog, Blog.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mkdir success.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }


        s.setImage(newFileName);

        Blog art = blogRepsitory.save(s);



        if (art != null)
        {
            return new ResponseEntity<Response>( HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
        }



    }

    public Blog updateArticle(Blog newArticle) {
        return (blogRepsitory.save(newArticle));



    }


    public String deleteArticle(int id) {
        if (blogRepsitory.findById(id).isPresent()) {
            blogRepsitory.deleteById(id);
            return "Article supprimé";
        } else
            return "Article non supprimé";
    }


    public List<Blog> getAllBlogs() {
        List<Blog> blogs = blogRepsitory.findAll();
        return blogs;
    }

}
