import React from "react";
import {DataGrid} from "@mui/x-data-grid";

const tableColumns = [
    {field:"id",headerName:"ID",width:120},
    {field:"firstName",headerName:"First Name",width:200},
    {field:"lastName",headerName:"Last Name",width:200},
    {field:"email",headerName: "E-mail",width:200 },
    {field: "tcKimlikNumber",headerName:"TC Kimlik Number",width: 200},
    {field: "studentNumber",headerName: "Student Number",width: 200}

]


export function StudentList(props){

    console.log(props.student);

    return(
        
        <div style={{height:400,width:"100%"}}>
            <DataGrid columns={tableColumns} rows={props.student} pageSize={5}/>
        </div>
    );
}