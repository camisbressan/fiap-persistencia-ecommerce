import React from 'react';
import './Pedidos.css'

function Pedidos() {
    return (
        <div className="row">
            <div className="pedidos-container">
                <h1>Pedidos</h1>

                <div className="card">
                    <div className="order-list">
                        <h5 className="card-title">Tênis Nike</h5>
                        <div className="card-body">
                            <h6 className="card-subtitle mb-2 text-muted">Tênis Nike Wmns Sb Check Solar Cnvs Feminino</h6>
                            <p className="card-text">Cor: Cinza+Laranja</p>
                            <p className="card-text">Quantidade: 1</p>
                            <p className="card-text">Valor unitário: R$ 234,99</p>
                        </div>
                    </div>
                    <div className="details-order">
                        <h5>Resumo da compra</h5>
                        <p>Pedido Nº: 921382156</p>
                        <p>Data do pedido: 09/12/2019</p>
                        <p>Valor total: R$ 22,14</p>
                        <p>Entrega realizada em: 17/12/2019</p>
                    </div>
                </div>
                
            </div>

        </div>
        
    );
}

export default Pedidos;