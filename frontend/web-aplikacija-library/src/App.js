import React from 'react';
import "bootstrap/dist/css/bootstrap.min.css"
import { BrowserRouter as Router, Route } from "react-router-dom";

import Navbar from "./components/navbar.component";
import BooksList from "./components/books-list.component";
import EditBook from "./components/edit-book.component";
import CreateBook from "./components/create-book.component";
import CreatePublisher from "./components/create-publisher.component";
import EditPublisher from './components/edit-publisher.component';
import CreateAuthor from "./components/create-author.component";
import EditAuthor from "./components/edit-author.component";
import AddBook from "./components/add-book.component";

function App() {
  return (
    <Router>
      <Navbar />
      <br />
      <Route path="/" exact component={BooksList} />
      <Route path="/edit/:id" exact component={EditBook} />
      <Route path="/create" exact component={CreateBook} />
      <Route path="/publisher" exact component={CreatePublisher} />
      <Route path="/publisher/update/:id" exact component={EditPublisher} />
      <Route path="/author" exact component={CreateAuthor} />
      <Route path="/author/update/:id" exact component={EditAuthor} />
      <Route path="/addbook" exact component={AddBook} />
    </Router>
  );
}

export default App;
