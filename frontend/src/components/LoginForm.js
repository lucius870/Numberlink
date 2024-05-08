import React from 'react';
import {Form, Button} from 'react-bootstrap';
import './Component.css'

const LoginForm = ({onLogin}) => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        onLogin(username, password);
    };

    return (
        <div className="login-form-container">
        <Form onSubmit={handleSubmit}>
            <Form.Group className="form">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" placeholder="Enter username" required value={username} onChange={(e) => setUsername(e.target.value)} />
            </Form.Group>

            <Form.Group className="form">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Enter password" required value={password} onChange={(e) => setPassword(e.target.value)} />
            </Form.Group>

            <Button  className="login-button" variant="primary" type="submit">
                Log in
            </Button>
        </Form>
        </div>
    );
};

export default LoginForm;