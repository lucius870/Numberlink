import {Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';
import {Link, NavLink} from "react-router-dom";
import React from "react";
import './Component.css'

const Menu = ({gameList}) => {
    return (
        <Navbar className="navbar" >
            <Container>
                <Link className="navbar-brand" to="/">GameStudio</Link>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Item>
                            <NavLink className="nav-link" to="/scores">Scores</NavLink>
                        </Nav.Item>
                        <Nav.Item>
                            <NavLink className="nav-link" to="/comments">Comments</NavLink>
                        </Nav.Item>
                        <Nav.Item>
                            <NavLink className="nav-link" to="/rating">Ratings</NavLink>
                        </Nav.Item>
                        <NavDropdown title="Games" id="nav-dropdown">
                            {gameList.map(game => (
                                <NavLink className="dropdown-item"
                                         key={game.path}
                                         to={`game/${game.path}`}>
                                    {game.title}
                                </NavLink>
                            ))}
                        </NavDropdown>
                    </Nav>
                    <Nav className="login">
                        <Nav.Item>
                            <NavLink className="nav-link" to="/login">Login</NavLink>
                        </Nav.Item>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default Menu;
