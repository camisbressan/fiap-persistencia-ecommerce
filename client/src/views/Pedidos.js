import React, { Component } from 'react';
import del from '../img/delete.svg';
import './General.css';
import './Pedidos.css'

class Pedidos extends Component {
    constructor(props) {
        super(props);
        this.state = {
            requests: [],
            showClient: false
        };
    };
    componentWillMount() {
        this.getRequests();
    }

    getRequests = () => {
        fetch("http://localhost:8080/v1/pedidos/cliente/1", {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
        }).then((response) => response.json()
            .then((data) => {
                this.setState({ requests: data })
            })
        ).catch(
            error => {
                console.log(error)
                this.setState({ result: "Erro ao consultar produtos!" })
            }
        );
    }

    deleteRequest = (idRequest) => {
        fetch("http://localhost:8080/v1/pedidos/" + idRequest, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }).then(function (response) {
            if (!response.ok) {
                console.log("Falha ao deletar pedido n°" + idRequest);
            }
        }).then(() => this.getRequests()
        ).catch(
            error => {
                console.log(error)
            }
        );
    }

    finishShopping = () => {
        this.setState({ showClient: true })
    }

    render() {
        let { requests, showClient } = this.state

        if (showClient) {
            let client = this.props.client;
            let endereco = this.props.client.enderecos[0];

            return (
            <div className="App">
                <header className="App-header">
                    <h1 className="text-green">Compra Finalizada</h1>
                    <div className="card row">
                        <h6 className="text-green">Dados da entrega:</h6>
                        <label className="mt1">Olá <b>{client.nome}</b>, realizaremos a entrega no endereço abaixo:</label>
                        <label className="mt1">{endereco.logradouro}, {endereco.numero} - {endereco.bairro} - {endereco.cidade}, {endereco.estado} - {endereco.cep}</label>
                        <label className="mt1">Enviaremos mais informações para o e-mail {client.email}</label>
                    </div>

                </header>
            </div> );
        }
        else if (this.props.logged) {
            return (
                <div className="App">
                    <header className="App-header">
                        <h1 className="text-green">Pedidos</h1>
                        <div className="pedidos-container">
                            {requests.map((request, index) => {
                                return (
                                    <div key={index} className="card">

                                        <div className="order-list">
                                            <h5 className="text-green"> {request.itens[0].produto.nome}</h5>
                                            <div className="card-body">
                                                <h6 className="card-subtitle mb-2 text-muted"> {request.itens[0].produto.descricao}</h6>
                                                <p><b>Quantidade:</b> {request.itens[0].produto.quantidade}</p>
                                                <p><b>Valor unitário:</b> R${request.itens[0].produto.preco}</p>
                                            </div>
                                        </div>
                                        <div className="details-order">
                                            <div className="clickable w100 flex-end" onClick={() => this.deleteRequest(request.id)}>
                                                <img className=" mt1" src={del} alt="logo-del" height="20px" width="20px" />
                                            </div>
                                            <h5 className="card-subtitle mb-2 text-muted">Resumo da compra</h5>
                                            <p><b>Pedido Nº:</b> {request.id}</p>
                                            <p><b>Data do pedido:</b> {request.dataPedido.substr(0, 10)}</p>
                                            <p><b>Valor total:</b> R${request.valorTotal}</p>
                                            <p><b>Entrega realizada em:</b> 30/06/2020</p>
                                        </div>
                                    </div>
                                );
                            })}
                            <button onClick={this.finishShopping}>FINALIZAR COMPRA</button>
                        </div>
                    </header>
                </div>
            );
        } else {
            return (
                <div className="App">
                    <header className="App-header">
                        <h1 className="error">Você precisa estar logado</h1>
                    </header>
                </div>
            );
        }
    }
}
export default Pedidos;