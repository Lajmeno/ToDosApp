import { useEffect, useState } from "react";
import ToDo from "./ToDo";
import { ToDoModel } from "./TodoModel";

import './ToDosGallery.css';




export default function ToDosGallery() {

    const [toDos, setToDos] = useState([] as Array<ToDoModel>);
    const [newToDo, setNewTodo] = useState({title: "", content: ""});


    useEffect(() => {
        fetch("http://localhost:5000/todos")
        .then(response => response.json())
        .then(responseBody => setToDos(responseBody))
    },[]);


    const addToDo = () => {

        const requestBody = {
            title : newToDo.title,
            content : newToDo.content
        }

        fetch("http://localhost:5000/todos", {
        method: "POST", 
        body: JSON.stringify(requestBody),
        headers: {
            'Content-Type': 'application/json',
        }})
        .then(response => response.json())
        .then(responseBody => setToDos(responseBody))
    };

    const deleteDoneToDos = () => {
        fetch(`http://localhost:5000/todos`, {
        method: "DELETE"})
        .then(response => response.json())
        .then(responseBody => setToDos(responseBody));
    }

    return( 
        <div className="main">
            <div className="addToDo">
                <input placeholder="Title" value={newToDo.title} onChange= {v => setNewTodo((prevState) => ({content: prevState.content, title :v.target.value}))}></input>
                <input placeholder="Description" value={newToDo.content} onChange= {v => setNewTodo((prevState) => ({title: prevState.title, content :v.target.value}))}></input>
                <button onClick={() => addToDo()}>add</button>
            </div>
            <div className="deleteDoneButton">
                <button onClick={() => deleteDoneToDos()}>Delete Done ToDos</button>
            </div>
            <div className="toDos">
                {
                toDos
                .map(item => <ToDo key={item.id} item={item} onItemChange = {setToDos}/>)
                }
            </div>
            
        </div>
    );
}