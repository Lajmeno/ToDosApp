import { ToDoModel } from "./TodoModel";

import './ToDo.css';
import { useState } from "react";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";

interface ToDoModelProps {
    item: ToDoModel;
    onItemChange: (todos : Array<ToDoModel>) => void;
}

export default function ToDo(props:ToDoModelProps){

    const [expand, setExpand] = useState(false);

    const [changedStatus, setChangedStatus] = useState(props.item.status);

    const{t} = useTranslation();

    const deleteToDo = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${props.item.id}`, {
        method: "DELETE"})
        .then(response => response.json())
        .then((todos : Array<ToDoModel>) => props.onItemChange(todos));
    }

    const switchStatus = () => {
        if(changedStatus === "WAITING"){
            return "INPROGRESS";
        }else if (changedStatus === "INPROGRESS"){
            return "DONE";
        }
        return 'WAITING';
    }

    const changeStatus = () => {
        const newStatus = switchStatus();
        setChangedStatus(newStatus);
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${props.item.id}`, {
        method: "PUT",
        body: JSON.stringify({status:newStatus}),
        headers: {
            'Content-Type': 'application/json',
        }})
        .then(response => response.json())
        .then((todos : Array<ToDoModel>) => props.onItemChange(todos));
    }


    return(
        <div>
        <div className="columns is-mobile">
            <Link to={`${props.item.id}`}><div /*onClick={() => expand === false ? setExpand(true) : setExpand(false)}*/ className="column is-one-quarter"> {props.item.title} </div></Link>
            <div className="column is-one-quarter"> {props.item.dateTime} </div>
            <div className={props.item.status === "WAITING" ? "toDoStatusWaiting" : (props.item.status === "DONE" ? "toDoStatusDone" : "toDoStatusInprogress")} onClick={() => changeStatus()}> {t(changedStatus)} </div>
            <div className="column is-one-quarter" >
                <button className="button" onClick={() => deleteToDo()}>delete</button>
             </div>
             </div>
             
            <div className={expand === false ? "toDoContentHidden" : "columns is mobile" }> {props.item.content} </div>
        </div>
    )
}