import React, { Component } from 'react';
import withoutProduct from '../img/product-without-photo.svg';
import car from '../img/carr.svg';
import './General.css';
import './Products.css'

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            result: "",
            selectedProducts: 0
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
                console.log(data)
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


            //passar o id do cliente
            fetch("http://localhost:8080/v1/pedidos/cliente/1", {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: body,
            }).then(function (response) {
                if(response.ok){
                    //chamar outra tela
                } else {
                    result = "Falha ao consultar o carrinho!";
                    this.setState({ result })
                }
            }).catch(
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
        let { products, result, selectedProducts } = this.state
        return (
            <div className="App">
                <header className="App-header">
                    <div className="w90 flex-space-between mr5 ml5 mt3">
                        <h1 className="text-green">Produtos disponíveis</h1>
                        <div className="clickable" onClick={this.openCar}>
                            <img className="flex-end car mt1" src={car} alt="logo-product" height="40px" width="40px" />
                            <label className="text-green"><bold>{selectedProducts}</bold></label>
                        </div>
                    </div>

                    <label className="error"><b>{result}</b></label>
                    <div className="products flex-center">
                        {products.map((product, index) => {
                            return (
                                <div key={product.index} className="item">
                                    <div className="flex-center">
                                        <img src={withoutProduct} alt="logo-product" />
                                    </div>
                                    <div className={"mt5"}>
                                        <label><b>Nome:</b> {product.nome}</label>
                                        <label><b>Descrição:</b> {product.descricao}</label>
                                        <label><b>Quantidade:</b> {product.quantidade}</label>
                                        <label><b>R$:</b> {product.preco}</label>
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
        );
    }
}

export default App;
