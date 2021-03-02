import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Book = props => (
    <tr>
        <td>{props.book.title}</td>
        <td>{props.book.isbn}</td>
        <td>{props.book.category}</td>
        <td>{props.book.publisher.name}</td>
        <td>{props.book.authors[0].firstName} {props.book.authors[0].lastName}</td>
        <td>
            <Link to={"/edit/" + props.book.id}>edit</Link> | <button onClick={() => { props.deleteBook(props.book.id) }} className="btn btn-danger">DELETE</button>
        </td>
    </tr>
)


const Author = props => (
    <tr>
        <td>{props.author.firstName}</td>
        <td>{props.author.lastName}</td>
        <td>
            <Link to={"/author/update/" + props.author.id}>edit</Link> | <button onClick={() => { props.deleteAuthor(props.author.id) }} className="btn btn-danger">DELETE</button>
        </td>

    </tr>
)

const Publisher = props => (
    <tr>
        <td>{props.publisher.name}</td>
        <td>{props.publisher.country}</td>
        <td>
            <Link to={"/publisher/update/" + props.publisher.id}>edit</Link> | <button onClick={() => { props.deletePublisher(props.publisher.id) }} className="btn btn-danger">DELETE</button>
        </td>

    </tr>
)


export default class BooksList extends Component {
    constructor(props) {
        super(props);

        this.deleteBook = this.deleteBook.bind(this);
        this.deleteAuthor = this.deleteAuthor.bind(this);
        this.deletePublisher = this.deletePublisher.bind(this);

        this.state = {
            books: [],
            publishers: [],
            authors: [],
        };
    }

    componentDidMount() {
        axios.get('http://localhost:8082/api/books/')
            .then(response => {
                this.setState({ books: response.data })
                console.log(response.data);
            })
            .catch((error) => {
                console.log(error);
            })
        axios.get('http://localhost:8082/api/publishers/')
            .then(response => {
                this.setState({ publishers: response.data })
            })
            .catch((error) => {
                console.log(error);
            })
        axios.get('http://localhost:8082/api/authors//')
            .then(response => {
                this.setState({ authors: response.data })
            })
            .catch((error) => {
                console.log(error);
            })

    }

    deleteBook(id) {
        axios.delete('http://localhost:8082/api/books/' + id)
            .then(res => console.log(res.data));
        this.setState({
            books: this.state.books.filter(el => el.id !== id)
        })
    }

    deleteAuthor(id) {
        axios.delete('http://localhost:8082/api/authors/' + id)
            .then(res => console.log(res.data));
        this.setState({
            authors: this.state.authors.filter(el => el.id !== id)
        })
    }

    deletePublisher(id) {
        axios.delete('http://localhost:8082/api/publishers/' + id)
            .then(res => console.log(res.data));
        this.setState({
            publishers: this.state.publishers.filter(el => el.id !== id)
        })
    }

    booksList() {
        return this.state.books.map(currentbook => {
            return <Book book={currentbook} deleteBook={this.deleteBook} key={currentbook.id} />;
        })
    }

    authorsList() {
        return this.state.authors.map(currentauthor => {
            return <Author author={currentauthor} deleteAuthor={this.deleteAuthor} key={currentauthor.id} />
        })
    }

    publishersList() {
        return this.state.publishers.map(currentPublisher => {
            return <Publisher publisher={currentPublisher} deletePublisher={this.deletePublisher} key={currentPublisher.id} />
        })
    }

    render() {
        return (
            <div>
                <div>
                    <h3>Books</h3>
                    <table className="table">
                        <thead className="thead-light">
                            <tr>
                                <th>Title</th>
                                <th>Isbn</th>
                                <th>Category</th>
                                <th>Publisher</th>
                                <th>Author</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.booksList()}
                        </tbody>
                    </table>
                </div>
                <div>
                    <h3>Authors</h3>
                    <table className="table">
                        <thead className="thead-light">
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.authorsList()}
                        </tbody>
                    </table>
                </div>
                <div>
                    <h3>Publishers</h3>
                    <table className="table">
                        <thead className="thead-light">
                            <tr>
                                <th>Name</th>
                                <th>Country</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.publishersList()}
                        </tbody>
                    </table>
                </div>
            </div>


        )
    }
}