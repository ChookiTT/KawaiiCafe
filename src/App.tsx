import { useState ,useEffect} from 'react'
import './css/auth.css'
import './css/Menu.css'
import './css/Index.css'
import './css/Order.css'
import './css/header.css'
import './css/img.css'
import './css/Profile.css'
import './css/footer.css'
import Menu from "./sites/Menu.tsx";
import Auth from "./sites/Authorization.tsx";
import Ordering from "./sites/Ordering.tsx"
import Cart from "./sites/Cart.tsx"
import type {User} from "./Interfaces/User.tsx";
import type {OrderItem} from "./Interfaces/OrderingInterface.tsx";
import Profile from "./sites/Profile.tsx";
import type {MenuItem} from "./Interfaces/MenuItem.tsx";

function App() {
    const [page, setPage] = useState< 'menu' | 'login' | 'register' | 'cart' | 'order' | 'profile'>('menu');
    const [currentUser, setCurrentUser] = useState<User | null>(null);
    const [cartItems, setCartItems] = useState<OrderItem[]>([]);

    const addToCart = (clickedItem: MenuItem) => {
        setCartItems((prevItems: OrderItem[]) => {
            const existingItem = prevItems.find((i) => i.item.itemId === clickedItem.itemId);

            if (existingItem) {
                return prevItems.map((i) =>
                    i.item.itemId === clickedItem.itemId ? {...i, qty: i.qty + 1} : i
                );
            }
            return [...prevItems, {item: clickedItem, qty: 1}];
        });
        console.log("Přidáno do košíku:", clickedItem.itemName);
    };

    const cartCount = cartItems.reduce((total, item) => total + item.qty, 0);
    const removeFromCart = (idToDelete: number) => {
        setCartItems(prevItems =>{const existingItem = prevItems.find((i) => i.item.itemId === idToDelete);

        if (existingItem && existingItem.qty > 1) {
            return prevItems.map((i) =>
                i.item.itemId === idToDelete
                    ? {...i, qty: i.qty - 1}
                    : i
            );
        }
        return prevItems.filter((i) => i.item.itemId !== idToDelete);
        });
    }

    const [editingField, setEditingField] = useState<string | null>(null);
    const [tempUser, setTempUser] = useState(currentUser);
    useEffect(() => {
        setTempUser(currentUser);
    }, [currentUser]);

    const handleChange = (section: 'contact' | 'address' | 'main', field: string, value: string) => {
        if (!tempUser) return;
        if (section === 'main') {
            setTempUser({
                ...tempUser,
                [field]: value
            });
        }
        else {
            setTempUser({
                ...tempUser,
                [section]: {
                    ...tempUser[section],
                    [field]: value
                }
            });
        }
    };

    const handleSave = async () => {
        console.log("data",JSON.stringify(tempUser))

        if (!tempUser || !tempUser.userId) {
            console.error("Chybi ID uživatele nebo tempUser!");
            return;
        }

        try {
            console.log("Odesílám data do backendu...", tempUser);

            const response = await fetch(`http://localhost:8080/api/users/${tempUser.userId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(tempUser),
            });

            if (response.ok) {
                setCurrentUser(tempUser);
                setEditingField(null);
                console.log("Uloženo do backendu");
            } else {
                console.error("Backend vrátil chybu při ukládání:", response.status);
            }
        }
        catch (error) {
            console.error("Chyba spojení s backendem:", error);
        }
    };

    return (
        <div className="WholePage">
            <header className="topBox">
                <button className={'kawaiiCafe'} onClick={() => setPage('menu')}>
                    KawaiiCafé
                </button>

                <div className="topRight">
                    <button className="cartButton" onClick={() => setPage('cart')}>
                        <img alt={'košík'} src='/public/shopping-cart.png' className="cart-icon"></img>
                        {cartCount > 0 && <span className="cart-badge">{cartCount}</span>}
                    </button>
                    {currentUser ? (
                        // --- LOGGED---
                        <div className="userButtons">
                            <button  onClick={() => setPage('profile')}>
                                <img alt={'profil'} className={'user-icon'} src='/public/profile.png'></img> {currentUser.username}
                            </button>

                            <button onClick={() => {setCurrentUser(null);setPage('menu');}}>
                                Odhlásit
                            </button>

                        </div>
                    ) : (
                        // --- NOT LOGGED ---
                        <div className="userButtons">
                            <button onClick={() => setPage('login')}>Login</button>
                            <button onClick={() => setPage('register')}>Signin</button>
                        </div>
                    )}
                </div>
            </header>

            {page === 'menu' && (
                <div className="menu-container">
                     <Menu setPage={setPage} addToCart={addToCart}/>
                </div>
            )}
            {page === 'login' && <Auth mode="login"  setPage={setPage} setUser={setCurrentUser} />}
            {page === 'register' && <Auth mode="register" setPage={setPage} setUser={setCurrentUser} />}
            {page === 'cart' && <Cart setPage={setPage} cartItems={cartItems} removeFromCart={removeFromCart}/>}
            {page === 'order' &&
                <Ordering
                    setPage={setPage}
                    cartItems={cartItems}
                    currentUser={currentUser}
                    tempUser={tempUser}
                    setCartItems={setCartItems}
                    handleChange={handleChange}
                    handleSave={handleSave}
                    editingField={editingField}
                    setEditingField={setEditingField}
                />
            }
            {page === 'profile' &&
                <Profile currentUser={currentUser}
                         tempUser ={tempUser}
                         setPage={setPage}
                         handleChange={handleChange}
                         handleSave={handleSave}
                         editingField={editingField}
                         setEditingField={setEditingField}
                />
            }

            <footer>
                <div className={'foot-contact'}>
                    <div className={'email'}>
                        <img alt={'email'} src='/public/mail.png' className={'icons'}></img>
                        kontakt@cafe.cz
                    </div>
                    <div className={'phone'}>
                        <img alt={'telefon'} src='/public/telephone.png' className={'icons'}></img>
                        +420 732 746 938
                    </div>
                </div>
                <div className={'foot-message'}>
                    Baked with love
                </div>
            </footer>
        </div>
    );
}

export default App;