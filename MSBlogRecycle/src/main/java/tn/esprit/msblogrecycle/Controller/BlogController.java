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
@CrossOrigin(origins = "*")
@RequestMapping("/blog")
public class BlogController {

    private String title="hello I'm the Article Microservice";


    @Autowired
    IBlogServic iBlogServic;


    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<HttpSender.Response> addArticle(@RequestPart("file") MultipartFile file, @RequestParam("Blog") String s)throws JsonParseException, JsonMappingException, Exception {
        return iBlogServic.add(file,s);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update-Blog")
    @ResponseBody
    Blog updateArticle(@RequestBody Blog s){
        return iBlogServic.updateArticle(s);
    }



    @RequestMapping("/hello")
    @CrossOrigin(origins = "*")
    public String sayHello(){

        System.out.println(title);
        return title;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteArticle(@PathVariable(value="id") int  id){
        return new ResponseEntity<>(iBlogServic.deleteArticle(id),HttpStatus.OK);

    }


    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = iBlogServic.getAllBlogs();
        return blogs;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id) {
        Blog blog = iBlogServic.getBlogById(id);
        if (blog != null) {
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
