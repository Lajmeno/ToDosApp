import { Col, Container, Row } from "react-bootstrap";

export default function Header (){
    return(
        <Container>
            <Row>
                <Col className="justify-content-center">
                <h1 className="m-4">ToDos App</h1>
                </Col>
                <Col><h1 className="m-4"></h1></Col>
            </Row>
        </Container>
    )
}