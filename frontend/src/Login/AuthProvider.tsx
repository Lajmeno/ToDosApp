import { ReactNode, useEffect, useState } from "react";
import AuthContext from "./AuthContext";
import { ContextInt, LoginData, Token } from "./LoginModels";



export default function AuthProvider({children}:{children :ReactNode}){


    const [authData, setAuthData] = useState({} as ContextInt)
    const [token, setToken] = useState({} as Token)

    const getToke = (email:string, password:string)  => {
        const requestBody = {
            email : email,
            password : password
        }
        fetch(`${process.env.REACT_APP_BASE_URL}/auth/login`, {
            method: "POST", 
            body: JSON.stringify(requestBody),
            headers: {
                'Content-Type': 'application/json',
            }})
            .then(response => {return response.text()})
            .then(responseBody =>setToken)

    }


    
    return <AuthContext.Provider value={authData }>{children}</AuthContext.Provider>;
}