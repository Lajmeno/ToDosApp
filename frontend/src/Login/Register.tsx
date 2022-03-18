import { useState } from "react";
import { Button, Col, Container, FormControl, InputGroup, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import { LoginData, RegisterData } from "./LoginModels";


export default function Register(){


    const [registerData, setRegisterData]= useState({} as RegisterData)

    const [errorMessage, setErrorMessage] = useState("");



    const register = () => {
        const requestBody = {
            email : registerData.email,
            password : registerData.password,
            verifyPassword : registerData.verifyPassword
        }
        if(registerData.password === registerData.verifyPassword){
            fetch(`${process.env.REACT_APP_BASE_URL}/users`, {
                method: "POST", 
                body: JSON.stringify(requestBody),
                headers: {
                    'Content-Type': 'application/json',
                }})
                .then(response => {
                    if(response.status === 404){
                        throw new Error("Email already used: Try another Email");
                    }else if (!response.ok) {
                        throw new Error("Passwords are not identical - try again.");
                    }
                 })
                 .catch((e:Error) => {setErrorMessage(e.message)});
                 setRegisterData({email:"", password:"", verifyPassword:""})
        }else{
            setErrorMessage("Passwords are not identical - try again.")
        }
        
    }

    return(
        <div>
            {errorMessage}
            <Container fluid>
                <Row className="justify-content-md-center" >
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Email</InputGroup.Text>
                        <FormControl placeholder="" aria-label="Username" aria-describedby="basic-addon1" value={registerData.email} onChange= {v => {setRegisterData((prevState) => ({password: prevState.password, verifyPassword:prevState.verifyPassword, email :v.target.value}))}}/>
                    </InputGroup>
                </Row>
                <Row className="justify-content-md-center" >
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                        <FormControl placeholder="" type="password" aria-label="Username" aria-describedby="basic-addon1" value={registerData.password} onChange= {v => {setRegisterData((prevState) => ({email: prevState.email, password :v.target.value, verifyPassword:prevState.verifyPassword,}))}}/>
                    </InputGroup>
                </Row>
                <Row className="justify-content-md-center" >
                    <InputGroup className="mb-3">
                        <InputGroup.Text id="basic-addon1">Verify Password</InputGroup.Text>
                        <FormControl placeholder="" type="password" aria-label="Username" aria-describedby="basic-addon1" value={registerData.verifyPassword} onChange= {v => {setRegisterData((prevState) => ({email: prevState.email, password: prevState.password, verifyPassword :v.target.value}))}}/>
                    </InputGroup>
                </Row>
                <Row className="justify-content-md-center">
                    <Col md="auto"><Button onClick={() => register()}>Register</Button></Col>
                    <Col><Link to="/login"><Button>Back to Login</Button></Link></Col>
                </Row>
                </Container>
        </div>
    )
}