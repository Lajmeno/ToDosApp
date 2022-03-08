import { useEffect, useState } from "react";
import { Button, Col, Row } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import { Link, useParams } from "react-router-dom";
import { ToDoModel } from "./TodoModel";

export default function ToDoDetails(){
    
    const param = useParams();

    const {t}= useTranslation();

    const [toDo, setToDo] = useState({} as ToDoModel);

    const[errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos/${param.id}`)
        .then(response => {return response.json()})
        .then(responseBody  => {
            if(responseBody){
                return responseBody;   
            }
            throw new Error("There is no ToDo with the requested id");
         })
        .then(responseBody => setToDo(responseBody))
        .catch((e:Error) => {setErrorMessage(e.message)})
    },[param.id]);
    
    return (
        <div>
            {errorMessage && <div>{errorMessage}</div>}

            <Row >
                <Col><div className="column is-one-quarter"> {toDo.title} </div></Col>
                <Col md="auto"><div className="column is-one-quarter"> {toDo.dateTime} </div></Col>
                <Col xs={5} lg="2"><div className={toDo.status === "WAITING" ? "toDoStatusWaiting" : (toDo.status === "DONE" ? "toDoStatusDone" : "toDoStatusInprogress")}>{t(toDo.status)} </div></Col>
            </Row>
            <Row>
                <Col> {toDo.content} </Col>
            </Row> 
            <Row>
                <Col><Link to="/ToDosGallery"><Button>Back</Button></Link></Col>
            </Row> 

        </div>
    )
}