import React, { Component } from 'react';
import senha from '../img/senha.svg';
import axios from 'axios';
import './General.css';
import './Login.css'

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            result: ""
        };
    };

    authentication = () => {
        console.log("auth");
        let { email, password } = this.state;

        const body = JSON.stringify({
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
            console.log(data)
            this.setState({ result: "" })
        })
    ).catch(
        error => {
            console.log(error)
            this.setState({ result: "Usuário não existe!" })
        }
    );/*.then((data) => {   
            if (data.status === 200)   
                this.setState({ result: "" })
            else
                this.setState({ result: "Usuário não existe!" })
        });*/
    }

    handleEmailChange = (event) => {
        console.log("email");
        console.log(event.target.value);
        this.setState({ email: event.target.value });
    }

    handlePasswordChange = (event) => {
        console.log("senha");
        console.log(event.target.value);
        this.setState({ password: event.target.value });
    }

    render() {
        return (
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
        );
    }
}

export default App;
