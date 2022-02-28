import { ToDoModel } from "./TodoModel";

import './ToDo.css';
import { useState } from "react";

interface ToDoModelProps {
    item: ToDoModel;
    onItemChange: (todos : Array<ToDoModel>) => void;
}

export default function ToDo(props:ToDoModelProps){

    const [clicked, setClicked] = useState(false);

    const deleteToDo = (id:string) => {
        if(id.length > 0){
            fetch(`http://localhost:5000/todos/${id}`, {
            method: "DELETE"})
            .then(response => response.json())
            .then((todos : Array<ToDoModel>) => props.onItemChange(todos));
        }
    }

    const changeStatus = (id:string, changedStatus:string) => {
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
        <div onClick={() => clicked === false ? setClicked(true) : setClicked(false)} className="toDo">
            <div className="toDoTitle"> {props.item.title} </div>
            <div className="toDoDate"> {props.item.dateTime} </div>
            <div className={clicked === false ? "toDoContentHidden" : "toDoContent" }> {props.item.content} </div>
            <div className="toDoStatus"> {props.item.status} </div>
            <div className="toDoButtons">
            <button className="inprogressButton" onClick={() => changeStatus(props.item.id, "INPROGRESS")}>INPROGRESS</button>
            <button className="doneButton" onClick={() => changeStatus(props.item.id, "DONE")}>DONE</button>
                <button className="deleteButton" onClick={() => deleteToDo(props.item.id)}>delete</button>
            </div>
            
        </div>
    )
}