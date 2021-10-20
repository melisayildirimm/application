import axios from "axios";

export class StudentApi{

    async getStudent(){
        const response = await axios.get("/students")
        return response.data;
    }

    async addStudent(student){
        const response = await axios.post("/students", student);
        return response.data;
    }
}