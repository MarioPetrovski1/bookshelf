import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class Navbar extends Component {

    render() {
        return (
            <nav className="navbar navbar-dark bg-dark navbar-expand-lg">
                <Link to="/" className="navbar-brand">Library</Link>
                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="navbar-item">
                            <Link to="/" className="nav-link">Home</Link>
                        </li>
                        <li className="navbar-item">
                            <Link to="/create" className="nav-link">Create Book</Link>
                        </li>
                        <li className="navbar-item">
                            <Link to="/publisher" className="nav-link">Create Publisher</Link>
                        </li>
                        <li className="navbar-item">
                            <Link to="/author" className="nav-link">Create Author</Link>
                        </li>
                        <li className="navbar-item">
                            <Link to="/addbook" className="nav-link">Add Book</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        )
    }
}