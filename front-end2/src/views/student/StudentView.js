import React, {useEffect, useState} from "react";
import {StudentApi} from "./api/StudentApi";
import {StudentList} from "./StudentList/StudentList";
import {Button} from "@material-ui/core";
import {AddStudent} from "./AddStudent/AddStudent"
import {toast} from "react-toastify";

export function StudentView(){

    const studentApi = new StudentApi();
    const[students,setStudents]=useState([]);
    const[isAddStudentModelOpen,setStudentModelOpen]=useState(false);

    function fetchStudents() {
        studentApi.getStudent()
            .then(data => setStudents(data))
    }

    useEffect(()=>{
        fetchStudents();
    },[])

    const addStudent= async(formState)=>{
        const messageResponse = await studentApi.addStudent(formState);
        if(messageResponse.messageType === 'SUCCESS'){
            toast.success(messageResponse.message);
            setStudentModelOpen(false);
            fetchStudents();
        }

    }

    return(
        <div>
            <Button color="primary" onClick={()=>setStudentModelOpen(true)}>Add student</Button>
            <AddStudent isOpen={isAddStudentModelOpen} onClose={()=>setStudentModelOpen(false)} addStudent={addStudent}/>
            <StudentList student={students}/>
        </div>
    );

}