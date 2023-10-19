package tn.esprit.msblogrecycle.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.msblogrecycle.Entity.Blog;

import java.util.List;

public interface IBlogServic {

    ResponseEntity<HttpSender.Response> add(MultipartFile file, String Article)
            throws JsonParseException, JsonMappingException, Exception;

    Blog updateArticle(Blog newArticle);
    // ResponseEntity<String> deleteHotel(int id);
    String deleteArticle(int id);

    public List<Blog> getAllBlogs();
}
