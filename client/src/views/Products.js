import React, { Component } from 'react';
import Pedidos from './Pedidos';
import withoutProduct from '../img/product-without-photo.svg';
import car from '../img/carr.svg';
import './General.css';
import './Products.css';

class Products extends Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            result: "",
            selectedProducts: 0,
            showCarScreen: false
        };
        this.productsOnCar = [];
    };

    componentWillMount() {
        fetch("http://localhost:8080/v1/produtos", {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            },
        }).then((response) => response.json()
            .then((data) => {
                this.setState({ products: data })
            })
        ).catch(
            error => {
                console.log(error)
                this.setState({ result: "Erro ao consultar produtos!" })
            }
        );

    }

    putOnCar = (idx, product) => {
        let { products, selectedProducts } = this.state

        if (product.quantidade > 0) {
            products[idx].quantidade = product.quantidade - 1;
            selectedProducts = selectedProducts + 1;

            let idxExists = this.productsOnCar.findIndex((item) => { if (item.id === product.id) { return true } })

            if (idxExists >= 0) {
                this.productsOnCar[idxExists].quantidade = this.productsOnCar[idxExists].quantidade + 1;
            } else {
                this.productsOnCar[this.productsOnCar.length] = {
                    id: product.id,
                    nome: product.nome,
                    descricao: product.descricao,
                    quantidade: 1,
                    preco: product.preco
                }
            }

            this.setState({ products, selectedProducts, result: "" })
        }
    }

    openCar = (idx, product) => {
        let { result } = this.state

        if (this.productsOnCar.length > 0) {
            let vlTotal = this.productsOnCar.reduce(function (accumulator, currentValue) {
                return accumulator + (currentValue.preco * currentValue.quantidade);
            }, 0)

            let productsSelected = [];
            this.productsOnCar.forEach((prod, index) => {
                productsSelected[index] = {
                    produto: prod,
                    quantidade: prod.quantidade
                }
            })

            let body = JSON.stringify({
                valorTotal: vlTotal,
                itens: productsSelected
            });

            fetch("http://localhost:8080/v1/pedidos/cliente/"+ this.props.client.id, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: body,
            }).then(function (response) {
                if(!response.ok){
                    console.log("Falha ao consultar o carrinho!");
                }
            }).then(()=>this.setState({ showCarScreen: true })
            ).catch(
                error => {
                    console.log(error)
                }
            );
        } else {
            result = "O carrinho ainda não possui nenhum produto!";
            this.setState({ result })
        }

    }

    render() {
        let { products, result, selectedProducts, showCarScreen } = this.state

        if (this.props.logged) {
            return (
                <>
                    {showCarScreen ?
                        <Pedidos
                            logged={this.props.logged}
                            client={this.props.client}
                        /> : (
                            <div className="App">
                                <header className="App-header">
                                    <div className="w90 flex-space-between mr5 ml5 mt3">
                                        <h1 className="text-green">Produtos disponíveis</h1>
                                        <div className="clickable" onClick={this.openCar}>
                                            <img className="flex-end car mt1" src={car} alt="logo-product" height="40px" width="40px" />
                                            <label className="text-green"><b>{selectedProducts}</b></label>
                                        </div>
                                    </div>

                                    <label className="error"><b>{result}</b></label>
                                    <div className="products flex-center">
                                        {products.map((product, index) => {
                                            return (
                                                <div key={index} className="item">
                                                    <div className="flex-center">
                                                        <img src={withoutProduct} alt="logo-product" />
                                                    </div>
                                                    <div className={"mt5"}>
                                                        <label className="text-black"><b>Nome:</b> {product.nome}</label>
                                                        <label className="text-black"><b>Descrição:</b> {product.descricao}</label>
                                                        <label className="text-black"><b>Quantidade:</b> {product.quantidade}</label>
                                                        <label className="text-black"><b>R$:</b> {product.preco}</label>
                                                    </div>
                                                    {product.quantidade > 0 ?
                                                        <div className="mt5">
                                                            <button onClick={event => this.putOnCar(index, product)}>COMPRAR</button>
                                                        </div>
                                                        : null}
                                                </div>
                                            );
                                        })}
                                    </div>
                                </header>
                            </div>
                        )}
                </>
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

export default Products;
