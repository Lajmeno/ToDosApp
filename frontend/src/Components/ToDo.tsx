import { ToDoModel } from "./TodoModel";
import './ToDo.css';
import { useState } from "react";
import { useTranslation } from "react-i18next";
import { Link } from "react-router-dom";
import { Button, Col, Row } from "react-bootstrap";

interface ToDoModelProps {
    item: ToDoModel;
    onItemChange: (todos : Array<ToDoModel>) => void;
}

export default function ToDo(props:ToDoModelProps){


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
        method: "PATCH",
        body: JSON.stringify({status:newStatus}),
        headers: {
            'Content-Type': 'application/json',
        }})
        .then(response => response.json())
        .then((todos : Array<ToDoModel>) => props.onItemChange(todos));
    }


    return(
        <div className="toDoInList">
            <Row >
                <Col><Link to={`${props.item.id}`}><div className="column is-one-quarter"> {props.item.title} </div></Link></Col>
                <Col md="auto"><div className="column is-one-quarter"> {props.item.dateTime} </div></Col>
                <Col xs={5} lg="2"><div className={props.item.status === "WAITING" ? "toDoStatusWaiting" : (props.item.status === "DONE" ? "toDoStatusDone" : "toDoStatusInprogress")} onClick={() => changeStatus()}> {t(changedStatus)} </div></Col>
                <Col xs={5} lg="2" >
                    <Button  onClick={() => deleteToDo()}>delete</Button>
                </Col>
            </Row>
        </div>
    )
}