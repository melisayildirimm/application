package yte.intern.application.student.service;

import org.springframework.stereotype.Service;
import yte.intern.application.common.dto.MessageResponse;
import yte.intern.application.common.dto.MessageType;
import yte.intern.application.student.entity.Book;
import yte.intern.application.student.entity.Student;
import yte.intern.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public MessageResponse addStudent(Student student){
        studentRepository.save(student);
        return new MessageResponse("Student has been saved succesfully", MessageType.SUCCESS);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public MessageResponse updateStudent(Long id, Student newStudent) {
        Student student = studentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Student with id %s is not found".formatted(id)));
        student.updateStudent(newStudent);
        studentRepository.save(newStudent);
        return new MessageResponse("Student with id %s has been updated successfully".formatted(id), MessageType.SUCCESS);
    }


    public MessageResponse deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return new MessageResponse("Student with id %s has been deleted successfully".formatted(id),MessageType.SUCCESS);

    }

    @Transactional
    public MessageResponse addBooktoStudent(Long id, Book toBeAddedBook) {
        Student student = studentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Student with id %s is not found".formatted(id)));
        MessageResponse messageResponse=student.canAddBook(toBeAddedBook);
        if(messageResponse.hasError()){
            return messageResponse;
        }

        student.addBook(toBeAddedBook);
        return new MessageResponse("Book %s has been added successfully".formatted(toBeAddedBook.getName()),MessageType.SUCCESS);
    }
}
