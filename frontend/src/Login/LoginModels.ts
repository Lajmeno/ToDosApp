
export interface Token{
    token: string
}

export interface ContextInt {
    JWT: Token
    login: (email : LoginData, password:string) => void;

}

export interface LoginData{
    email: string,
    password: string;
}

export interface RegisterData{
    email: string,
    password: string;
    verifyPassword: string;
}