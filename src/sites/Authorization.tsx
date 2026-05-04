import { useState } from 'react';
import type { User } from "../Interfaces/User.tsx";

interface AuthProps {
    mode: 'login' | 'register';
    setPage: (page: 'menu' | 'login' | 'register' | 'cart' | 'order' | 'profile') => void;
    setUser: (user: User) => void;
}

export default function Auth({ mode, setPage,setUser }: AuthProps) {
    const [userData, setUserData] = useState<User>({

        firstName: '',
        lastName: '',
        username: '',
        password: '',
        address: { city: '', houseNumber: '', street: '' },
        contact: { phoneNumber: '', email: '' }
    });

    const handleSubmit = async () => {
        const path = mode === 'register' ? '/register' : '/login';
        const url = `http://localhost:8080/api/users${path}`;

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(userData),
            });

            if (response.ok) {
                const loggedUser = await response.json();
                if (mode === 'register') {
                    alert("Registrace byla úspěšná, můžete se přihlásit.");
                    setPage('login');
                }
                else {
                    setUser(loggedUser);
                    alert("Vítej, " + loggedUser.firstName + "!");
                    setPage('menu');
                }
            }
            else {
                alert(mode === 'register' ? "Registrace selhala" : "Zkontrolujte si údaje");
            }
        }
        catch (error) {
            console.error("Chyba:", error);
        }
    };

    return (
        <div className="home-screen">
            <div className="up-cont">
            <h3>
                {mode === 'login' ? 'Přihlášení' : 'Registrace'}
            </h3>
            </div>
            <div className="inputBox">
                <input
                    type="text"
                    placeholder="Uživatelské jméno"
                    value={userData.username}
                    onChange={(e) => setUserData({...userData, username: e.target.value})}
                />
                <input
                    type="password"
                    placeholder="Heslo"
                    value={userData.password}
                    onChange={(e) => setUserData({...userData, password: e.target.value})}
                />

                {mode === 'register' && (
                    <>
                        <input
                            type="text"
                            placeholder="Jméno"
                            value={userData.firstName}
                            onChange={(e) => setUserData({...userData, firstName: e.target.value})}
                        />
                        <input
                            type="text"
                            placeholder="Příjmení"
                            value={userData.lastName}
                            onChange={(e) => setUserData({...userData, lastName: e.target.value})}
                        />

                        <h4>
                            Adresa
                        </h4>
                        <input
                            type="text"
                            placeholder="Ulice"
                            value={userData.address.street}
                            onChange={(e) => setUserData({
                                ...userData,
                                address: {...userData.address, street: e.target.value}
                            })}
                        />
                        <input
                            type="text"
                            placeholder="Číslo domu"
                            value={userData.address.houseNumber}
                            onChange={(e) => setUserData({
                                ...userData,
                                address: {...userData.address, houseNumber: e.target.value}
                            })}
                        />

                        <input
                            type="text"
                            placeholder="Město"
                            value={userData.address.city}
                            onChange={(e) => setUserData({
                                ...userData,
                                address: {...userData.address, city: e.target.value}
                            })}
                        />

                        <h4>
                            Kontakt
                        </h4>
                        <input
                            type="text"
                            placeholder="Email"
                            value={userData.contact.email}
                            onChange={(e) => setUserData({
                                ...userData,
                                contact: {...userData.contact, email: e.target.value}
                            })}
                        />
                        <input
                            type="text"
                            placeholder="Telefon"
                            value={userData.contact.phoneNumber}
                            onChange={(e) => setUserData({
                                ...userData,
                                contact: {...userData.contact, phoneNumber: e.target.value}
                            })}
                        />
                    </>
                )}
            </div>

            <div className="actionButtons">
                <button className={'log-btn'} onClick={handleSubmit}>
                    {mode === 'login' ? 'Přihlásit' : 'Zaregistrovat'}
                </button>

            </div>
            {mode === 'login' ? (
                <p>Nemáte účet?
                    <br></br>
                    <button className={'back-btn'} onClick={() => setPage('register')}>
                        Zaregistrujte se
                    </button>
                </p>
            ) : (
                <p>Už máte účet?
                    <br></br>
                    <button className={'back-btn'} onClick={() => setPage('login')}>
                        Přihlásit se
                    </button></p>
            )}


            <button className={'back-btn'} onClick={() => setPage('menu')}>
                Zrušit
            </button>
        </div>
    );
}