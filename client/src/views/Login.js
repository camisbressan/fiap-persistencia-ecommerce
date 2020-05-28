import React, { Component } from 'react';
import senha from '../img/senha.svg';
import Products from './Products';
import './General.css';
import './Login.css'

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            logged: false,
            result: "", 
            client: []
        };
    };

    authentication = () => {
        let { email, password } = this.state;

        let body = JSON.stringify({
            email: email,
            senha: password
        });

        fetch("http://localhost:8080/v1/clientes/login", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: body,
        }).then((response) => response.json()
            .then((data) => {
                if (data.id > 0) {
                    this.setState({ logged: true, client: data })
                } else {
                    this.setState({ result: "Usuário não existe!" })
                }

            })
        ).catch(
            error => {
                console.log(error)
                this.setState({ result: "Usuário não existe!" })
            }
        );
    }

    handleEmailChange = (event) => {
        this.setState({ email: event.target.value });
    }

    handlePasswordChange = (event) => {
        this.setState({ password: event.target.value });
    }

    render() {
        let { logged, client } = this.state

        return (
            <>
                {logged ?
                    <Products
                        logged={this.state.logged}
                        client={client}
                    /> : (
                        <div className="App">
                            <header className="App-header">

                                <img src={senha} alt="logo-senha" />
                                <div className={"content"}>
                                    <div className={"mt10"}>
                                        <label>Usuário:</label>
                                        <input type="text" placeholder={"exemplo@gmail.com"} value={this.state.email} onChange={this.handleEmailChange} />
                                    </div>
                                    <div className={"mt5"}>
                                        <label>Senha:</label>
                                        <input type="password" placeholder={"********"} value={this.state.password} onChange={this.handlePasswordChange} />
                                    </div>

                                    <div className="error flex-end clickable mt3">{this.state.result}</div>

                                    <div className="mt20 flex-end">
                                        <button onClick={this.authentication}>ENTRAR</button>
                                    </div>
                                </div>
                            </header>
                        </div>
                    )}
            </>
        );
    }
}

export default Login;
