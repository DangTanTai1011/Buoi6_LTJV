package com.example.demo.services;

import com.example.demo.models.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseService {
    @PersistenceContext
    private EntityManager entityManager;

    public void add(Course newCourse){
        entityManager.persist(newCourse);
    }

    public List<Course> getAll(){
        String query = "SELECT c FROM Course c";
        return entityManager.createQuery(query, Course.class).getResultList();
    }

    public Course getById(int id){
        return entityManager.find(Course.class, id);
    }

    public void update(Course course){
        entityManager.merge(course);
    }

    public void delete(int id){
        Course course = entityManager.find(Course.class, id);
        if (course != null) {
            entityManager.remove(course);
        }
    }

    public List<Course> searchByPlaceAndLectureName(String place, String lectureName){
        String query = "SELECT c FROM Course c WHERE c.place = :place AND c.lectureName = :lectureName";
        return entityManager.createQuery(query, Course.class)
                .setParameter("place", place)
                .setParameter("lectureName", lectureName)
                .getResultList();
    }
    public List<Course> searchByPlace(String place){
        String query = "SELECT c FROM Course c WHERE c.place = :place";
        return entityManager.createQuery(query, Course.class)
                .setParameter("place", place)
                .getResultList();
    }

    public List<Course> searchByLectureName(String lectureName){
        String query = "SELECT c FROM Course c WHERE c.lectureName = :lectureName";
        return entityManager.createQuery(query, Course.class)
                .setParameter("lectureName", lectureName)
                .getResultList();
    }
}