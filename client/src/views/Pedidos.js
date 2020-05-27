import React, { Component } from 'react';
import del from '../img/delete.svg';
import './General.css';
import './Pedidos.css'

class Pedidos extends Component {
    constructor(props) {
        super(props);
        this.state = {
            requests: []
        };
    };
    componentWillMount() {
        this.getRequests();
    }

    getRequests = () => {
        console.log("teste");
        fetch("http://localhost:8080/v1/pedidos/cliente/1", {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
        }).then((response) => response.json()
            .then((data) => {
                console.log(data)
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
            if(!response.ok){
                console.log("Falha ao consultar o carrinho!");
            }
        }).then(()=>this.getRequests()
        ).catch(
            error => {
                console.log(error)
            }
        );
    }

    render() {
        let { requests } = this.state
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
                        <button onClick={event => this.finishShopping()}>FINALIZAR COMPRA</button>
                    </div>
                </header>
            </div>

        );
    }
}
export default Pedidos;