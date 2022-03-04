import { t } from "i18next";
import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import ToDo from "./ToDo";
import { ToDoModel } from "./TodoModel";

import './ToDosGallery.css';


export default function ToDosGallery() {

    const [toDos, setToDos] = useState([] as Array<ToDoModel>);
    const [newToDo, setNewTodo] = useState({title: "", content: ""});

    const {t} = useTranslation();
    
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos`)
        .then(response => {
            if(response.ok){
                return response.json()
            }
            throw new Error("Could not GET Todos");
         })
        .then(responseBody => setToDos(responseBody))
        .catch((e:Error) => {setErrorMessage(e.message)})
    },[]);


    const addToDo = () => {

        const requestBody = {
            title : newToDo.title,
            content : newToDo.content
        }

        fetch(`${process.env.REACT_APP_BASE_URL}/todos`, {
        method: "POST", 
        body: JSON.stringify(requestBody),
        headers: {
            'Content-Type': 'application/json',
        }})
        .then(response => response.json())
        .then(responseBody => setToDos(responseBody))
    };

    const deleteDoneToDos = () => {
        fetch(`${process.env.REACT_APP_BASE_URL}/todos`, {
        method: "DELETE"})
        .then(response => response.json())
        .then(responseBody => setToDos(responseBody));
    }

    return( 
        <div className="main">
            <div className="addToDo">
                <input placeholder="Title" value={newToDo.title} onChange= {v => setNewTodo((prevState) => ({content: prevState.content, title :v.target.value}))}></input>
                <input placeholder="Description" value={newToDo.content} onChange= {v => setNewTodo((prevState) => ({title: prevState.title, content :v.target.value}))}></input>
                <button onClick={() => addToDo()}>{t('addButton')}</button>
            </div>
            <div className="deleteDoneButton">
                <button onClick={() => deleteDoneToDos()}>{t('deleteDoneTodosButton')}</button>
            </div>
            <div className="toDos" data-testid="todos">
                {
                toDos
                .map(item => <ToDo key={item.id} item={item} onItemChange = {setToDos}/>)
                }
                <div>{errorMessage}</div>
            </div>
        </div>
    );
}