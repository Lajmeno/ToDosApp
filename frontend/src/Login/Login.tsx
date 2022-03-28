import { useState } from "react";
import { Button, Col, Container, FormControl, InputGroup, Row } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { LoginData } from "./LoginModels";


export default function Login(){

    const [loginData, setLoginData]= useState({} as LoginData)

    const navigate = useNavigate();


    const login = () => {
        const requestBody = {
            email : loginData.email,
            password : loginData.password
        }
        fetch(`${process.env.REACT_APP_BASE_URL}/auth/login`, {
            method: "POST", 
            body: JSON.stringify(requestBody),
            headers: {
                'Content-Type': 'application/json',
            }})
            .then(response => {return response.text()})
            .then(responseBody =>{localStorage.setItem("jwt", responseBody); navigate("/gallery"); })
    }
    const spotifyLogin = () => {
        const requestBody = {
            email : loginData.email,
            password : loginData.password
        }
        fetch(`${process.env.REACT_APP_BASE_URL}/auth/login`, {
            method: "POST", 
            body: JSON.stringify(requestBody),
            headers: {
                'Content-Type': 'application/json',
            }})
            .then(response => {return response.text()})
            .then(responseBody =>{localStorage.setItem("jwt", responseBody); navigate("/gallery"); })
    }

    return(
        <div>
            <Container fluid>
                <Row className="justify-content-md-center" >
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Email</InputGroup.Text>
                        <FormControl placeholder="" aria-label="Username" aria-describedby="basic-addon1" value={loginData.email} onChange= {v => {setLoginData((prevState) => ({password: prevState.password, email :v.target.value}))}}/>
                    </InputGroup>
                </Row>
                <Row className="justify-content-md-center" >
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                        <FormControl placeholder="" type="password" aria-label="Username" aria-describedby="basic-addon1" value={loginData.password} onChange= {v => {setLoginData((prevState) => ({email: prevState.email, password :v.target.value}))}}/>
                    </InputGroup>
                </Row>
                <Row className="justify-content-md-center">
                    <Col md="auto"><Button onClick={() => login()}>Login</Button></Col>
                    <Col><Link to="/register"><Button>Register New User</Button></Link></Col>
                </Row>
                <Row>
                     <Col md="auto"><Button onClick={() => spotifyLogin()}>Spotify-Login</Button></Col>
                    <a href="https://accounts.spotify.com/authorize?response_type=code&client_id=6a813b18cb8940bbbe1daab2ab175e60&scope=user-read-private&redirect_uri=http://localhost:5000/callback">Spotify</a>
                </Row>
                </Container>

        </div>
    )
}


