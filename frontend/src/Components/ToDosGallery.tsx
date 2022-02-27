import { useEffect, useState } from "react";
import { isTemplateExpression } from "typescript";
import ToDo from "./ToDo";
import { ToDoModel } from "./TodoModel";

import './ToDosGallery.css';




export default function ToDosGallery() {

    const [toDos, setToDos] = useState([] as Array<ToDoModel>);
    const [newToDo, setNewTodo] = useState("");


    useEffect(() => {
        fetch("http://localhost:5000/todos")
        .then(response => response.json())
        .then(responseBody => setToDos(responseBody))
    },[]);

    const requestBody = {
        title : newToDo
    }

    const addToDo = (newToDo:string) => {
        fetch("http://localhost:5000/todos", {
        method: "post", 
        body: JSON.stringify(requestBody),
        headers: {
            'Content-Type': 'application/json',
        }})
        .then(response => response.json())
        .then(responseBody => setToDos(responseBody))
    };


    return( 
        <div className="main">
            <div className="addToDo">
                <input placeholder="Enter Title" value={newToDo} onChange= {v => setNewTodo(v.target.value)}></input>
                <button onClick={() => addToDo(newToDo)}>add</button>
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