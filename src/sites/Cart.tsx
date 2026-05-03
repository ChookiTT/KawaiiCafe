import React from 'react';
import "../css/Cart.css";
import type {OrderItem} from '../Interfaces/OrderingInterface'

interface CartProps {
    setPage: (page: 'menu' | 'login' | 'register' | 'cart' | 'order' | 'profile') => void;
    cartItems:OrderItem[];
    removeFromCart: (itemId: number) => void;
}
const Cart: React.FC<CartProps> = ({ setPage ,cartItems,removeFromCart}) => {

    const total = cartItems.reduce((sum, item) => sum + (item.item.itemPrice * item.qty), 0);

    return (
        <div className={'home-screen'}>
            <div className="cart-container">
                <h2 className="logo-text">
                    Košík
                </h2>

                <div className="cart-list">
                    {cartItems.map(item => (
                        <div key={item.item.itemId} className="cart-item">
                            <div className="item-info">
                                <span className="item-name">{item.item.itemName}</span>

                            </div>
                            <div className="item-controls">
                                <span className="item-price">
                                    {item.item.itemPrice}Kč
                                </span>
                                <span>
                                    {item.qty} ks
                                </span>
                                <button  className={'delete-btn'} onClick={() => removeFromCart(item.item.itemId)} title="Odstranit z košíku">
                                    <img  className={'delete-btn-img'} alt={'odstranit'} src='public/bin.png'></img>
                                </button>
                            </div>
                        </div>
                    ))}
                </div>

                <div className="cart-summary">
                    <h3>
                        Celkem: {total} Kč
                    </h3>
                    <div className="cart-buttons">
                        <button className="order-btn" onClick={() => setPage('order')}>
                            Pokračovat v objednávce
                        </button>
                        <button className="back-btn" onClick={() => setPage('menu')}>
                            Zpět do menu
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Cart;