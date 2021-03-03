import React, { Component } from 'react';
import axios from 'axios';

export default class EditAuthor extends Component {
    constructor(props) {
        super(props);

        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangeBookTitle = this.onChangeBookTitle.bind(this);
        this.onChangeBookIsbn = this.onChangeBookIsbn.bind(this);
        this.onChangeBookCategory = this.onChangeBookCategory.bind(this);
        this.onChangePublisherName = this.onChangePublisherName.bind(this);
        this.onChangePublisherCountry = this.onChangePublisherCountry.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            firstName: '',
            lastName: '',
            books: [],
            publisher: {
                name: '',
                country: ''
            }
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8082/api/authors/' + this.props.match.params.id)
            .then(response => {
                this.setState({
                    firstName: response.data.firstName,
                    lastName: response.data.lastName,
                    books: response.data.books[0],
                    publisher: response.data.books[0].publisher
                })
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    onChangeFirstName(e) {
        this.setState({
            firstName: e.target.value
        });
    }

    onChangeLastName(e) {
        this.setState({
            lastName: e.target.value
        });
    }

    onChangeBookTitle(e) {
        this.setState(prevState => {
            let books = Object.assign({}, prevState.books);
            books.title = e.target.value;
            return { books };
        })
    }

    onChangeBookIsbn(e) {
        this.setState(prevState => {
            let books = Object.assign({}, prevState.books);
            books.isbn = e.target.value;
            return { books };
        })
    }

    onChangeBookCategory(e) {
        this.setState(prevState => {
            let books = Object.assign({}, prevState.books);
            books.category = e.target.value;
            return { books };
        })
    }

    onChangePublisherName(e) {
        this.setState(prevState => ({
            publisher: {
                ...prevState.publisher,
                name: e.target.value
            }
        }));
    }

    onChangePublisherCountry(e) {
        this.setState(prevState => ({
            publisher: {
                ...prevState.publisher,
                country: e.target.value
            }
        }));
    }

    onSubmit(e) {
        e.preventDefault();

        const booksObject = Object.assign({}, this.state.books);
        const publisherObject = { name: this.state.publisher.name, country: this.state.publisher.country };
        const dataObject = [{ title: booksObject.title, isbn: booksObject.isbn, category: booksObject.category, publisher: publisherObject }];


        const author = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            books: dataObject
        }

        console.log(author);

        axios.put('http://localhost:8082/api/authors/' + this.props.match.params.id, author)
            .then(res => console.log(res.data))
            .catch(e => console.log(e));



        window.location = '/';
    }

    render() {
        return (
            <div>
                <h3>Edit Author</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="col-md-6">
                        <label>First Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.firstName}
                            onChange={this.onChangeFirstName}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Last Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.lastName}
                            onChange={this.onChangeLastName}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Book title: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.books.title}
                            onChange={this.onChangeBookTitle}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Book isbn: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.books.isbn}
                            onChange={this.onChangeBookIsbn}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Book category: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.books.category}
                            onChange={this.onChangeBookCategory}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Publisher name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.publisher.name}
                            onChange={this.onChangePublisherName}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Publisher country: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.publisher.country}
                            onChange={this.onChangePublisherCountry}
                        />
                    </div>

                    <div className="col-md-6" style={{ marginTop: '10px' }}>
                        <input type="submit" value="Edit Author" className="btn btn-primary" />
                    </div>
                </form>
            </div>
        )
    }
}