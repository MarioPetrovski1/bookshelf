import React, { Component } from 'react';
import axios from 'axios';

export default class CreateBook extends Component {
    constructor(props) {
        super(props);

        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeIsbn = this.onChangeIsbn.bind(this);
        this.onChangeCategory = this.onChangeCategory.bind(this);
        this.onChangeAuthorFirstName = this.onChangeAuthorFirstName.bind(this);
        this.onChangeAuthorLastName = this.onChangeAuthorLastName.bind(this);
        this.onChangePublisherName = this.onChangePublisherName.bind(this);
        this.onChangePublisherCountry = this.onChangePublisherCountry.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            title: '',
            isbn: '',
            category: '',
            authors: [],
            publisher: {
                name: '',
                country: ''
            }
        }
    }

    onChangeTitle(e) {
        this.setState({
            title: e.target.value
        });
    }

    onChangeIsbn(e) {
        this.setState({
            isbn: e.target.value
        });
    }

    onChangeCategory(e) {
        this.setState({
            category: e.target.value
        });
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

        const authorsObject = Object.assign({}, this.state.authors);
        const dataObject = [{ firstName: authorsObject.firstName, lastName: authorsObject.lastName }];


        const book = {
            title: this.state.title,
            isbn: this.state.isbn,
            category: this.state.category,
            authors: dataObject,
            publisher: this.state.publisher,
        }

        console.log(book);

        axios.post('http://localhost:8082/api/books/', book)
            .then(res => console.log(res.data))
            .catch(e => console.log(e));



        window.location = '/';
    }

    render() {
        return (
            <div>
                <h3>Create New Book</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                        <label>Title: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.title}
                            onChange={this.onChangeTitle}
                        />
                    </div>
                    <div className="form-group">
                        <label>Isbn: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.isbn}
                            onChange={this.onChangeIsbn}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.category}
                            onChange={this.onChangeCategory}
                        />
                    </div>
                    <div className="form-group">
                        <label>Publisher Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.publisher.name}
                            onChange={this.onChangePublisherName}
                        />
                    </div>
                    <div className="form-group">
                        <label>Publisher Country: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.publisher.country}
                            onChange={this.onChangePublisherCountry}
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
                        <input type="submit" value="Create Book" className="btn btn-primary" />
                    </div>
                </form>
            </div>
        )
    }
}