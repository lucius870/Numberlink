import React from 'react';
import {Form, Button} from 'react-bootstrap';

const LoginForm = ({onLogin}) => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        onLogin(username, password);
    };

    return (
        <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
            </Form.Group>

            <Form.Group className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </Form.Group>

            <Button variant="primary" type="submit">
                Log in
            </Button>
        </Form>
    );
};

export default LoginForm;