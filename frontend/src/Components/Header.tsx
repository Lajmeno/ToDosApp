import { Col, Container, Row } from "react-bootstrap";

export default function Header (){
    return(
        <div>
            <Container>
                <Row>
                    <Col className="justify-content-center">
                    <h1 className="m-4">ToDos App</h1>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}