import { useEffect, useState } from "react";
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
        .then(response => {
            if(response.ok){
                return response.json()
            }
            throw new Error("Could not GET Todos");
         })
        .then(responseBody => setToDo(responseBody))
        .catch((e:Error) => {setErrorMessage(e.message)})
    },[]);
    
    return (
        <div>
            <div>{errorMessage}</div>

            <div className="columns is-mobile">
                <div /*onClick={() => expand === false ? setExpand(true) : setExpand(false)}*/ className="column is-one-quarter"> {toDo.title} </div>
                <div className="column is-one-quarter"> {toDo.dateTime} </div>
                <div className={toDo.status === "WAITING" ? "toDoStatusWaiting" : (toDo.status === "DONE" ? "toDoStatusDone" : "toDoStatusInprogress")}>{t(toDo.status)} </div>
                <div className="column is-one-quarter" ></div>
            </div>
            <div className={"columns is mobile"}> {toDo.content} </div>
            <div>
                <Link to="/ToDosGallery"><button>Back</button></Link>
            </div>
        </div>
    )
}