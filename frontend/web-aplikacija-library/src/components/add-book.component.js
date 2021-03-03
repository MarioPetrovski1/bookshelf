import React, { Component } from 'react';
import axios from 'axios';


export default class CreateBook extends Component {
    constructor(props) {
        super(props);

        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeIsbn = this.onChangeIsbn.bind(this);
        this.onChangeCategory = this.onChangeCategory.bind(this);
        this.onChangePublisher = this.onChangePublisher.bind(this);
        this.onChangeAuthor = this.onChangeAuthor.bind(this);
        this.onFileChangeHandler = this.onFileChangeHandler.bind(this);
        this.onSubmit = this.onSubmit.bind(this);

        this.state = {
            title: '',
            isbn: '',
            category: '',
            authors: [],
            publishers: [],
            publisher: {},
            authorForPost: [],
            selectedFile: null,
            fileName: ''
        }
    }

    componentDidMount() {
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

    onChangeAuthor(e) {
        const authorObject = [{ id: parseInt(e.target.value) }];
        const message = [{ firstName: "Selected Author with id: " + e.target.value }];
        this.setState({
            authorForPost: authorObject,
            authors: message
        })

        document.getElementById("authorSelect").remove();
    }

    onChangePublisher(e) {
        const publisherObject = { id: parseInt(e.target.value) };
        const message = [{ name: "Selected Publisher with id: " + e.target.value }];
        this.setState({
            publisher: publisherObject,
            publishers: message
        })

        document.getElementById("publisherSelect").remove();
    }

    onFileChangeHandler(e) {
        e.preventDefault();
        this.setState({
            selectedFile: e.target.files[0],
            fileName: e.target.files[0].name
        });
        const formData = new FormData();
        formData.append('file', e.target.files[0]);
        axios.post('http://localhost:8082/api/upload', formData)
            .then(res => {
                console.log(res.data);
                alert("File uploaded successfully.")
            })
            .catch(e => console.log(e));

    };

    onSubmit(e) {
        e.preventDefault();

        const book = {
            title: this.state.title,
            isbn: this.state.isbn,
            category: this.state.category,
            authors: this.state.authorForPost,
            publisher: this.state.publisher,
            fileName: this.state.fileName
        }

        axios.post('http://localhost:8082/api/books/', book)
            .then(res => console.log(res.data))
            .catch(e => console.log(e));


        window.location = '/';
    }

    render() {
        return (
            <div>
                <h3>Add Book</h3>
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
                        <label>Choose Publisher </label>
                        <select
                            required
                            className="form-control"
                            value={this.state.publishers}
                            onChange={this.onChangePublisher}>
                            <option id="publisherSelect">Select publisher</option>
                            {
                                this.state.publishers.map(publisher => (
                                    <option value={publisher.id}>{publisher.name}</option>
                                ))
                            }
                        </select>
                    </div>
                    <div className="col-md-6">
                        <label>Choose Author </label>
                        <select
                            required
                            className="form-control"
                            value={this.state.authors}
                            onChange={this.onChangeAuthor}>
                            <option id="authorSelect">Select author</option>
                            {
                                this.state.authors.map(author => (
                                    <option value={author.id}>{author.firstName} {author.lastName}</option>
                                ))
                            }
                        </select>
                    </div>
                    <div className="col-md-6">
                        <div className="form-group files color">
                            <label>Upload Book </label>
                            <input type="file" className="form-control" name="file" onChange={this.onFileChangeHandler} />
                        </div>
                    </div>

                    <div className="col-md-6" style={{ marginTop: '10px' }}>
                        <input type="submit" value="Add Book" className="btn btn-primary" />
                    </div>
                </form>
            </div>
        )
    }
}