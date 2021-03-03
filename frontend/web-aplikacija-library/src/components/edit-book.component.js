import React, { Component } from 'react';
import axios from 'axios';

export default class EditBook extends Component {
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

    componentDidMount() {
        axios.get('http://localhost:8082/api/books/' + this.props.match.params.id)
            .then(response => {
                this.setState({
                    title: response.data.title,
                    isbn: response.data.isbn,
                    category: response.data.category,
                    authors: response.data.authors[0],
                    publisher: response.data.publisher
                })
            })
            .catch(function (error) {
                console.log(error);
            })
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
        const dataObjectAuthor = [{ firstName: authorsObject.firstName, lastName: authorsObject.lastName }];
        const dataObjectPublisher = { name: this.state.publisher.name, country: this.state.publisher.country };


        const book = {
            title: this.state.title,
            isbn: this.state.isbn,
            category: this.state.category,
            authors: dataObjectAuthor,
            publisher: dataObjectPublisher
        }

        console.log(book);

        axios.put('http://localhost:8082/api/books/' + this.props.match.params.id, book)
            .then(res => console.log(res.data))
            .catch(e => console.log(e));



        window.location = '/';
    }

    render() {
        return (
            <div>
                <h3>Edit Book</h3>
                <form onSubmit={this.onSubmit}>
                    <div className="col-md-6">
                        <label>Title: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.title}
                            onChange={this.onChangeTitle}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Isbn: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.isbn}
                            onChange={this.onChangeIsbn}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Category: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.category}
                            onChange={this.onChangeCategory}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Publisher Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.publisher.name}
                            onChange={this.onChangePublisherName}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Publisher Country: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.publisher.country}
                            onChange={this.onChangePublisherCountry}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Author First Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.authors.firstName}
                            onChange={this.onChangeAuthorFirstName}
                        />
                    </div>
                    <div className="col-md-6">
                        <label>Author Last Name: </label>
                        <input type="text"
                            required
                            className="form-control"
                            value={this.state.authors.lastName}
                            onChange={this.onChangeAuthorLastName}
                        />
                    </div>

                    <div className="col-md-6" style={{ marginTop: '10px' }}>
                        <input type="submit" value="Edit Book" className="btn btn-primary" />
                    </div>
                </form>
            </div>
        )
    }
}