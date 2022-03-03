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
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${id}`, {
        method: "DELETE"})
        .then(response => response.json())
        .then((todos : Array<ToDoModel>) => props.onItemChange(todos));
    }

    const changeStatus = (id:string, changedStatus?:string) => {

        if(changedStatus === undefined){
            props.item.status === "WAITING" ? changedStatus = "INPROGRESS" : changedStatus = "WAITING";
        }
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${id}`, {
        method: "PUT",
        body: JSON.stringify({status:changedStatus}),
        headers: {
            'Content-Type': 'application/json',
        }})
        .then(response => response.json())
        .then((todos : Array<ToDoModel>) => props.onItemChange(todos));
    }

    return(
        <div>
        <div className="columns is-mobile">
            <div onClick={() => expand === false ? setExpand(true) : setExpand(false)} className="column is-one-quarter"> {props.item.title} </div>
            <div className="column is-one-quarter"> {props.item.dateTime} </div>
            <div className={props.item.status === "WAITING" ? "toDoStatusWaiting" : (props.item.status === "DONE" ? "toDoStatusDone" : "toDoStatusInprogress")} onClick={() => changeStatus(props.item.id)}> {props.item.status} </div>
            <div className="column is-one-quarter" >
                <button className="button" onClick={() => changeStatus(props.item.id, "DONE")}>DONE</button>
                <button className="button" onClick={() => deleteToDo(props.item.id)}>delete</button>
             </div>
             </div>
             
            <div className={expand === false ? "toDoContentHidden" : "columns is mobile" }> {props.item.content} </div>
        </div>
    )
}