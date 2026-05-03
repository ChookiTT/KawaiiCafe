import {useEffect, useState} from "react";
import type {MenuItem} from "../Interfaces/MenuItem.tsx";

interface MenuProps {
    setPage: (page:  'menu' | 'login' | 'register' | 'cart' | 'order' | 'profile') => void;
    addToCart: (item: MenuItem) => void;
}

export default function Menu({ addToCart}: MenuProps) {
    const [menu, setMenu] = useState<MenuItem[]>([]);
    const [categories, setCategories] = useState<{categoryId: number, categoryName: string}[]>([]);
    const [activeCat, setActiveCat] = useState<number | null>(null);

    useEffect(() => {
        fetch('http://localhost:8080/api/menu')
            .then(res => res.json())
            .then(data => setMenu(data))
            .catch(err => console.error("Chyba menu:", err));

        fetch('http://localhost:8080/api/categories')
            .then(res => res.json())
            .then(data => setCategories(data))
            .catch(err => console.error("Chyba kategorií:", err));
    }, []);

    return (
        <div className="home-screen">
            <h1>
                Naše nabídka
            </h1>
                <div className="category-bar">
                    <button onClick={() => setActiveCat(null)}>
                        Celá nabídka
                    </button>

                    {categories.map(cat => (
                        <button key={cat.categoryId} onClick={() => setActiveCat(cat.categoryId)} className={activeCat === cat.categoryId ? 'active' : ''}>
                            {cat.categoryName}
                        </button>
                    ))}
                </div>

            <div className="menu">

                {menu
                    .filter(item => {

                        if (activeCat === null) return true;
                        if (!item.categories || !Array.isArray(item.categories)) return false;

                        return item.categories?.some(cat =>Number(cat.categoryId) === Number(activeCat));
                    })
                    .sort((a, b) => a.itemPrice - b.itemPrice)
                    .map(item => {
                        const allergenText = item.alergen?.length > 0
                            ? "Alergeny: " + item.alergen.map(a => a.alergenName).join(", ")
                            : "Bez alergenů";

                        return (
                              <div key={item.itemId} className="item-name-wrapper" title={allergenText}>
                                  <div className="item-image-container">
                                      <img src={item.imagePath || '/img/placeholder.jpg'} alt={item.itemName} className="menu-item-img"/>
                                   </div>
                                  <span className="item-info">
                                    {item.itemName} <strong>{item.itemPrice} Kč</strong>
                                  </span>

                                <button className="back-btn" onClick={() => addToCart(item)}>
                                    Objednat
                                </button>
                              </div>

                        );
                    })
                }

                {menu.length === 0 && <p>Momentálně připravujeme kávovar...</p>}
                <br />
            </div>
        </div>
    );
}