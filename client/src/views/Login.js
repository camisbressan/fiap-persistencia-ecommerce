import React from 'react';
import senha from '../img/senha.svg';
import './General.css';
import './Login.css'

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <img src={senha} alt="logo-senha" />
                <div className={"content"}>
                    <div className={"mt10"}>
                        <label>Usuário:</label>
                        <input type="text" placeholder={"exemplo@gmail.com"} value={""} /*onChange={this.handleYearChange}*/ />
                    </div>
                    <div className={"mt5"}>
                        <label>Senha:</label>
                        <input type="text" placeholder={"********"} value={""} /*onChange={this.handleYearChange}*/ />
                    </div>

                    <div className="link flex-end clickable mt3">Você ainda não é nosso cliente?</div>

                    <div className="mt20 flex-end">
                        <button /*onClick={this.finishTrip}*/>ENTRAR</button>
                    </div>
                </div>
            </header>
        </div>
    );
}

export default App;
