package yte.intern.application.student.controller;

import org.springframework.web.bind.annotation.*;
import yte.intern.application.common.dto.MessageResponse;
import yte.intern.application.student.controller.request.AddBookStudentRequest;
import yte.intern.application.student.controller.request.AddStudentRequest;
import yte.intern.application.student.controller.request.UpdateStudentRequest;
import yte.intern.application.student.controller.response.StudentQueryResponse;
import yte.intern.application.student.entity.Student;
import yte.intern.application.student.service.StudentService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public MessageResponse addStudent(@Valid @RequestBody AddStudentRequest addStudentRequest){
        Student student = addStudentRequest.toEntity();
         return studentService.addStudent(student);

    }

    @GetMapping
    public List<StudentQueryResponse> getAllStudents(){
        return studentService.getAllStudents()
                .stream()
                .map(student->new StudentQueryResponse(student))
                .collect(Collectors.toList());
    }

    @PutMapping("{id}")
    public MessageResponse updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequest updateStudentRequest){
        return studentService.updateStudent(id,updateStudentRequest.toEntity());

    }

    @DeleteMapping("{id}")
    public MessageResponse deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @PostMapping("/{id}/books")
    public MessageResponse addBooktoStudent(@PathVariable Long id, @RequestBody AddBookStudentRequest addBookStudentRequest){
        return studentService.addBooktoStudent(id,addBookStudentRequest.toEntity());

    }


}
