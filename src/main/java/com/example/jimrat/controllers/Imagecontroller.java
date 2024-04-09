package com.example.jimrat.controllers;

import com.example.jimrat.models.Image;
import com.example.jimrat.models.User;
import com.example.jimrat.repositories.ImageRepository;
import com.example.jimrat.repositories.UserRepository;
import com.example.jimrat.services.LoggedUserManagmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/photo")
public class Imagecontroller {
    ImageRepository imageRepository;
    UserRepository userRepository;
    private final LoggedUserManagmentService loggedUserManagmentService;
    public Imagecontroller(UserRepository userRepository,ImageRepository imageRepository,LoggedUserManagmentService loggedUserManagmentService){
        this.imageRepository=imageRepository;
        this.userRepository=userRepository;
        this.loggedUserManagmentService=loggedUserManagmentService;
    }
    @PostMapping
    public void storePhoto(@RequestParam int id,@RequestParam String type,@RequestBody MultipartFile file) throws IOException {
        Image image=new Image();
        image.setId(0L);
        image.setName(file.getName());
        image.setFilePath(file.getOriginalFilename());
        image.setImageData(file.getBytes());
        image.setType(file.getContentType());
        imageRepository.storeImage(image,id,type);
    }
    @GetMapping
    public Image getPhoto(@RequestParam int image_id)
    {

        return imageRepository.getImage(image_id);
    }
    @GetMapping("/coaches")
    public List<User> getallCoach(){
        return userRepository.getAllCoaches();
    }
    @GetMapping("/trainee")
    public List<User> getallTrainees(){
        return userRepository.getAllTrainers();
    }

}
