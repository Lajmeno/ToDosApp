import { ToDoModel } from "./TodoModel";

import './ToDo.css';
import { useState } from "react";

interface ToDoModelProps {
    item: ToDoModel;
    onItemChange: (todos : Array<ToDoModel>) => void;
}

export default function ToDo(props:ToDoModelProps){

    const [expand, setExpand] = useState(false);

    const deleteToDo = (id:string) => {
        if(id.length > 0){
            fetch(`http://localhost:5000/todos/${id}`, {
            method: "DELETE"})
            .then(response => response.json())
            .then((todos : Array<ToDoModel>) => props.onItemChange(todos));
        }
    }

    const changeStatus = (id:string, changedStatus?:string) => {

        if(changedStatus === undefined){
            props.item.status === "WAITING" ? changedStatus = "INPROGRESS" : changedStatus = "WAITING";
        }
        fetch(`http://localhost:5000/todos/${id}`, {
        method: "PUT",
        body: JSON.stringify({status:changedStatus}),
        headers: {
            'Content-Type': 'application/json',
        }})
        .then(response => response.json())
        .then((todos : Array<ToDoModel>) => props.onItemChange(todos));
    }

    return(
        <div className="toDo">
            <div onClick={() => expand === false ? setExpand(true) : setExpand(false)} className="toDoTitle"> {props.item.title} </div>
            <div className="toDoDate"> {props.item.dateTime} </div>
            <div className={expand === false ? "toDoContentHidden" : "toDoContent" }> {props.item.content} </div>
            <div className={props.item.status === "WAITING" ? "toDoStatusWaiting" : (props.item.status === "DONE" ? "toDoStatusDone" : "toDoStatusInprogress")} onClick={() => changeStatus(props.item.id)}> {props.item.status} </div>
            <div className="toDoButtons" >
                <button className="doneButton" onClick={() => changeStatus(props.item.id, "DONE")}>DONE</button>
                <button className="deleteButton" onClick={() => deleteToDo(props.item.id)}>delete</button>
            </div>
        </div>
    )
}