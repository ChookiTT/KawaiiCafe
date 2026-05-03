import React, { useState } from 'react';
import type { OrderingInterface, OrderItem } from "../Interfaces/OrderingInterface.tsx";
import type { User } from "../Interfaces/User.tsx";
import {EditableRow} from './Profile.tsx'

interface OrderingProps {
    setPage: (page: 'menu' | 'login' | 'register' | 'cart' | 'order' | 'profile') => void;
    cartItems: OrderItem[];
    currentUser: User | null;
    tempUser: User | null;
    handleSave: ()  => Promise<void>;
    setCartItems: (items: OrderItem[]) => void;
    handleChange: (section: 'contact' | 'address' | 'main', field: string, value: string)=> void;
    editingField: string | null;
    setEditingField: (field: string | null) => void;
}

const Ordering: React.FC<OrderingProps> = ({ setPage, handleSave,handleChange,setEditingField,editingField,cartItems, currentUser,tempUser, setCartItems }) => {
    const [note, setNote] = useState('');

    const sendOrder = async () => {
        if (!currentUser) {
            alert("Pro odeslání objednávky musíš být přihlášen!");
            return;
        }

        const finalOrder: OrderingInterface = {
            orderNote: note,
            items: cartItems.map(item => ({
                ...item,
                qty: item.qty
            })),
            orderPrice: cartItems.reduce((sum, item) => sum + (item.item.itemPrice * item.qty), 0),
            orderDate: new Date().toISOString(),
            user: currentUser,

        };

        try {
            const response = await fetch('http://localhost:8080/api/Orders/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(finalOrder),
            });

            if (response.ok) {
                setCartItems([]);
                localStorage.removeItem('cart');
                alert('Objednávka byla úspěšně odeslána');
                setPage('menu');
            }
            else {
                alert('Něco se nepovedlo na straně serveru.');
            }
        }
        catch (error) {
            console.error('Chyba při komunikaci:', error);
            alert('Nepodařilo se připojit k serveru.');
        }
    };

    return (
        <div className="home-screen">
            <div className="checkout-form">
                {currentUser && currentUser.address ? (
                    // --- LOGGED ---
                    <>
                        <h2 className="logo-text">
                            Dokončení objednávky
                        </h2>
                        <div className="profile-info">
                            <div className='individual'>
                                <EditableRow
                                    label={"Username:"}
                                    id="username"
                                    value={tempUser?.username|| ''}
                                    isEditing={editingField === 'username'}
                                    onEdit={() => setEditingField('username')}
                                    onSave={() => handleSave()}
                                    onChange={(val) => handleChange('main', 'username', val)}
                                />
                            </div>
                            <div className='individual'>
                                {editingField === 'name' ? (
                                    <div className="name-row-editing" style={{ display: 'flex', gap: '8px' }}>
                                        <input
                                            className="checkout-input-small firstName-input"
                                            placeholder="firstName"
                                            value={tempUser?.firstName || ''}
                                            onChange={(e) => handleChange('main', 'firstName', e.target.value)}
                                            autoFocus
                                        />
                                        <input
                                            className="checkout-input-small lastName-input"
                                            placeholder="lastName"
                                            style={{ width: '60px' }}
                                            value={tempUser?.lastName || ''}
                                            onChange={(e) => handleChange('main', 'lastName', e.target.value)}
                                        />
                                        <button className="save-btn"  onClick={async () => {await handleSave();setEditingField(null)}}>
                                            OK
                                        </button>
                                    </div>
                                ) : (
                                    <>
                                    <span className='profile-info'>
                                       <strong>
                                           Jméno:
                                       </strong>
                                        {tempUser?.firstName} {tempUser?.lastName}
                                    </span>
                                     <button className='edit-btn' onClick={() => setEditingField('name')}>
                                        <img src='/public/edit.png' alt='edit'></img>
                                     </button>
                                    </>
                                )}
                            </div>
                                <div className={'row-style'}></div>
                                    <h4>
                                        Kontakt
                                    </h4>
                                        <div className='individual'>
                                            <EditableRow
                                                id="email"
                                                value={tempUser?.contact.email|| ''}
                                                isEditing={editingField === 'email'}
                                                onEdit={() => setEditingField('email')}
                                                onSave={() => handleSave()}
                                                onChange={(val) => handleChange('contact', 'email', val)}
                                            />
                                        </div>

                                        <div className='individual'>
                                            <EditableRow
                                                id="phoneNumber"
                                                value={tempUser?.contact.phoneNumber|| ''}
                                                isEditing={editingField === 'phoneNumber'}
                                                onEdit={() => setEditingField('phoneNumber')}
                                                onSave={() =>handleSave()}
                                                onChange={(val) => handleChange('contact', 'phoneNumber', val)}
                                            />

                                        </div>
                                <div className=
                                         {'row-style'}>

                                </div>

                                    <h4>
                                        Adresa
                                    </h4>
                                        <div className='individual'>
                                            {editingField === 'fullAddress' ? (
                                                <div className="address-row-editing" style={{ display: 'flex', gap: '8px' }}>
                                                    <input
                                                        className="checkout-input-small street-input"
                                                        placeholder="Ulice"
                                                        value={tempUser?.address.street || ''}
                                                        onChange={(e) => handleChange('address', 'street', e.target.value)}
                                                        autoFocus
                                                    />
                                                    <input
                                                        className="checkout-input-small number-input"
                                                        placeholder="č.p."
                                                        style={{ width: '60px' }}
                                                        value={tempUser?.address.houseNumber || ''}
                                                        onChange={(e) => handleChange('address', 'houseNumber', e.target.value)}
                                                    />
                                                    <button className="save-btn" onClick={async () => {await handleSave();setEditingField(null)}}>
                                                        OK
                                                    </button>
                                                </div>
                                            ) : (
                                                <>
                                                    <span className='profile-info'>
                                                        {tempUser?.address.street} {tempUser?.address.houseNumber}
                                                    </span>
                                                    <button className='edit-btn' onClick={() => setEditingField('fullAddress')}>
                                                        <img src='/public/edit.png' alt='edit' ></img>
                                                    </button>
                                                </>
                                            )}
                                        </div>
                                        <div className='individual'>
                                            <EditableRow
                                                id="city"
                                                value={tempUser?.address.city || ''}
                                                isEditing={editingField === 'city'}
                                                onEdit={() => setEditingField('city')}
                                                onSave={() => handleSave()}
                                                onChange={(val) => handleChange('address', 'city', val)}
                                            />

                                        </div>

                                        <input
                                            className="checkout-input"
                                            type="text"
                                            placeholder="Poznámka k objednávce"
                                            value={note}
                                            onChange={(e) => setNote(e.target.value)}
                                        />

                                        <div className={'cart-buttons'}>
                                            <button className="order-btn" onClick={sendOrder} disabled={cartItems.length === 0}>
                                                Odeslat objednávku
                                            </button>
                                            <button className="back-btn" onClick={() => setPage('cart')}>
                                                Zpět do košíku
                                            </button>
                                        </div>
                         </div>
                    </>
                ) : (
                    //----NOT LOGGED---
                    <div className="order-screen">
                        <p>
                            Pro dokončení objednávky se prosím přihlaste.
                        </p>
                            <button className={'back-btn'} onClick={() => setPage('login')}>
                                Přihlásit
                            </button>
                            <button className={'back-btn'} onClick={() => setPage('register')}>
                                Zaregistrovat
                            </button>
                    </div>
                )}
            </div>
        </div>
    )

};

export default Ordering;