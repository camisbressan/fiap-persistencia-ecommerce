import React, { Component } from 'react';
import withoutProduct from '../img/product-without-photo.svg';
import carr from '../img/carr.svg';
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
                this.setState({ products: data, result: "" })
            })
        ).catch(
            error => {
                console.log(error)
                this.setState({ result: "Erro ao consultar produtos!" })
            }
        );

    }

    putOnCar = (idx, e) => {
        let { products, selectedProducts } = this.state

        let result = ""
        //deu certo
        console.log(products[idx].quantidade)
        if (products[idx].quantidade > 0) {
            products[idx].quantidade = products[idx].quantidade - 1;
            selectedProducts = selectedProducts + 1;
        } else {
            result = "Produto em falta"
        }
        console.log(products[idx].quantidade)
        this.setState({ products, selectedProducts })

    }

    render() {
        let { products, selectedProducts } = this.state
        return (
            <div className="App">
                <header className="App-header">
                    <div className="w100 flex-space-between mr5 ml5 mt3">
                        <h1 className="text-green">Produtos disponíveis</h1>
                        <div>
                            <img className="flex-end car mt1" src={carr} alt="logo-product" height="40px" width="40px" />
                            <label className="text-green"><bold>{selectedProducts}</bold></label>
                        </div>
                    </div>
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

                                    <div className="mt5 flex-end">
                                        <button onClick={event => this.putOnCar(index, event)}>COMPRAR</button>
                                    </div>
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
