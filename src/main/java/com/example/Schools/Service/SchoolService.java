package com.example.Schools.Service;

import com.example.Schools.Domain.School;
import com.example.Schools.Repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Library;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository repo;

    public List<School> listAll(){ return repo.findAll();}

    public School save(School sl) {
        School scheol =repo.save(sl);
        return  scheol;

    }

    public School get(long id)
    {

        return repo.findById(id).get();}

    public void delete (long id) {
        repo.deleteById(id);
    }
    }

