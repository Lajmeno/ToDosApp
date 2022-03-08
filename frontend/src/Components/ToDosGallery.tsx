import { useEffect, useState } from "react";
import { Button, Col, Container, FormControl, InputGroup, Row, ThemeProvider } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import ToDo from "./ToDo";
import { ToDoModel } from "./TodoModel";

import './ToDosGallery.css';


export default function ToDosGallery() {

    const [toDos, setToDos] = useState([] as Array<ToDoModel>);
    const [newToDo, setNewTodo] = useState({title: localStorage.getItem("title")?? "", description: localStorage.getItem("description")?? ""});

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
            content : newToDo.description
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
        <ThemeProvider breakpoints={['xxxl', 'xxl', 'xl', 'lg', 'md', 'sm', 'xs', 'xxs']} >
            <Container fluid>
                <Row className="justify-content-md-center" >
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Title</InputGroup.Text>
                        <FormControl placeholder="Please write ToDo title here" aria-label="Username" aria-describedby="basic-addon1" value={newToDo.title} onChange= {v => {setNewTodo((prevState) => ({description: prevState.description, title :v.target.value})) ; localStorage.setItem("title",v.target.value)}}/>
                    </InputGroup>
                </Row>
                <Row className="justify-content-md-center" >
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Description</InputGroup.Text>
                        <FormControl placeholder="Please write Description here" aria-label="Username" aria-describedby="basic-addon1" value={newToDo.description} onChange= {v => {setNewTodo((prevState) => ({title: prevState.title, description :v.target.value})) ; localStorage.setItem("description",v.target.value)}}/>
                    </InputGroup>
                </Row>
                <Row className="justify-content-md-center">
                    <Col md="auto"><Button onClick={() => addToDo()}>{t('addButton')}</Button></Col>
                    <Col md="auto"> <Button onClick={() => deleteDoneToDos()}>{t('deleteDoneTodosButton')}</Button></Col>
                    <Row  >
                       
                        
                    </Row>
                </Row>
                <Row>
                    
                    <div className="toDos" data-testid="todos">
                        {
                        toDos
                        .map(item => <ToDo key={item.id} item={item} onItemChange = {setToDos}/>)
                        }
                        <div>{errorMessage}</div>
                    </div>
                </Row>
            </Container>
            </ThemeProvider>
    );
}