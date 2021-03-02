import React, { Component } from 'react';
import axios from 'axios';

export default class EditPublisher extends Component {
    constructor(props) {
        super(props);

        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeCountry = this.onChangeCountry.bind(this);
        this.onChangeBookTitle = this.onChangeBookTitle.bind(this);
        this.onChangeBookIsbn = this.onChangeBookIsbn.bind(this);
        this.onChangeBookCategory = this.onChangeBookCategory.bind(this);
        this.onChangeAuthorFirstName = this.onChangeAuthorFirstName.bind(this);
        this.onChangeAuthorLastName = this.onChangeAuthorLastName.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            name: '',
            country: '',
            books: [],
            authors: [],
        }
    }

    componentDidMount() {
        axios.get('http://localhost:8082/api/publishers/' + this.props.match.params.id)
            .then(response => {
                console.log(response.data)
                this.setState({
                    name: response.data.name,
                    country: response.data.country,
                    books: response.data.books[0],
                    authors: response.data.books[0].authors[0]
                })
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    onChangeName(e) {
        this.setState({
            name: e.target.value
        });
    }

    onChangeCountry(e) {
        this.setState({
            country: e.target.value
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

    onChangeAuthorFirstName(e) {
        this.setState(prevState => {
            let authors = Object.assign({}, prevState.authors);
            authors.firstName = e.target.value;
            return { authors };
        })
    }

    onChangeAuthorLastName(e) {
        this.setState(prevState => {
            let authors = Object.assign({}, prevState.authors);
            authors.lastName = e.target.value;
            return { authors };
        })
    }

    onSubmit(e) {
        e.preventDefault();

        const booksObject = Object.assign({}, this.state.books);
        const authorsObject = Object.assign({}, this.state.authors);
        const dataObjectBooks = [{
            title: booksObject.title, isbn: booksObject.isbn, category: booksObject.category, authors: [{ firstName: authorsObject.firstName, lastName: authorsObject.lastName }]
        }];


        const publisher = {
            name: this.state.name,
            country: this.state.country,
            books: dataObjectBooks
        }

        console.log(publisher);

        axios.put('http://localhost:8082/api/publishers/' + this.props.match.params.id, publisher)
            .then(res => console.log(res.data))
            .catch(e => console.log(e));



        window.location = '/';
    }

    render() {
        return (
            <div>
                <h3>Edit Publisher</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.name}
                            onChange={this.onChangeName}
                        />
                    </div>
                    <div className="form-group">
                        <label>Country: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.country}
                            onChange={this.onChangeCountry}
                        />
                    </div>
                    <div className="form-group">
                        <label>Book title: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.books.title}
                            onChange={this.onChangeBookTitle}
                        />
                    </div>
                    <div className="form-group">
                        <label>Book isbn: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.books.isbn}
                            onChange={this.onChangeBookIsbn}
                        />
                    </div>
                    <div className="form-group">
                        <label>Book category: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.books.category}
                            onChange={this.onChangeBookCategory}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author First Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.authors.firstName}
                            onChange={this.onChangeAuthorFirstName}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author Last Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.authors.lastName}
                            onChange={this.onChangeAuthorLastName}
                        />
                    </div>

                    <div className="form-group">
                        <input type="submit" value="Edit Publisher" className="btn btn-primary" />
                    </div>
                </form>
            </div>
        )
    }
}