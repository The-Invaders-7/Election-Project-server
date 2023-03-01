package com.example.ElectionProject.service;

import com.example.ElectionProject.models.ImageFile;
import com.example.ElectionProject.models.User;
import com.example.ElectionProject.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoterService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    public String addImage(MultipartFile image) throws IOException{
        DBObject metadata=new BasicDBObject();
        metadata.put("fileSize",image.getSize());
        Object imageID=template.store(image.getInputStream(),image.getOriginalFilename(),image.getContentType(),metadata);
        return imageID.toString();
    }

//    public ImageFile retrieveImage(String id) throws IOException{
//        GridFSFile gridFSFile=template.findOne(new Query(Criteria.where("_id").is(id)));
//        ImageFile imageFile=new ImageFile();
//        if(gridFSFile!=null && gridFSFile.getMetadata()!=null){
//            imageFile.setFileName(gridFSFile.getFilename());
//            imageFile.setFileType(gridFSFile.getMetadata().get("_contentType").toString());
//            imageFile.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());
//            imageFile.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
//        }
//        return imageFile;
//    }

    public List<User> findBy(String firstName, String middleName, String lastName, String city){
        try{
            firstName="^"+firstName;
            middleName="^"+middleName;
            lastName="^"+lastName;
            city="^"+city;
            return this.userRepository.findBy(firstName,middleName,lastName,city);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ArrayList<User>();
        }
    }

    
}
