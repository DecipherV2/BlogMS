package tn.esprit.msblogrecycle.Controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import io.micrometer.core.ipc.http.HttpSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.msblogrecycle.Entity.Blog;
import tn.esprit.msblogrecycle.Service.IBlogServic;

import java.util.List;

@RestController
public class BlogController {

    private String title="hello I'm the Article Microservice";


    @Autowired
    IBlogServic iBlogServic;



    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<HttpSender.Response> addArticle(@RequestPart("file") MultipartFile file, @RequestParam("Blog") String s)throws JsonParseException, JsonMappingException, Exception {
        return iBlogServic.add(file,s);
    }

    @PutMapping("/update-Blog/{id}")
    @ResponseBody
    Blog updateArticle(@RequestBody Blog s){
        return iBlogServic.updateArticle(s);
    }



    @RequestMapping("/hello")

    public String sayHello(){

        System.out.println(title);
        return title;
    }

    @DeleteMapping(value="/id", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteArticle(@PathVariable(value="id") int  id){
        return new ResponseEntity<>(iBlogServic.deleteArticle(id),HttpStatus.OK);

    }

    @GetMapping("/all")
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = iBlogServic.getAllBlogs();
        return blogs;
    }
}
