import { useEffect,useState } from 'react'

import './App.css'
interface MenuItem {
    id: number;
    nazev: string;
    cena: number;
    category?: {
        id: number;
        nazev: string;
    } | null;
}
function App() {
  const [menu, setMenu] = useState<MenuItem[]>([]);
  const [page, setPage] = useState<'home' | 'menu'>('home');
  const [categories, setCategories] = useState<{id: number, nazev: string}[]>([]);
  const [activeCat, setActiveCat] = useState<number | null>(null);
    useEffect(() => {
        fetch('http://localhost:8080/menu')
            .then(res => res.json())
            .then(data => setMenu(data));
        fetch('http://localhost:8080/menu/categories').then(res => res.json()).then(setCategories);
    }, []);

    if (page === 'home') {
        return (
            <div className="home-screen">

                <div className="welcome">
                    <h1>KawaiiCafé</h1>
                    <p></p>
                    <button className="enter-btn" onClick={() => setPage('menu')}>
                        Objednávka
                    </button>
                </div>
            </div>
        );
    }
  return(
      <div className="Menu-page">


          <div className="category-bar">{/* buttons top */}
              <button onClick={() => setActiveCat(null)}>Celá nabídka</button>
              {categories.map(cat => (
                  <button key={cat.id} onClick={() => setActiveCat(cat.id)}>
                      {cat.nazev}
                  </button>
              ))}

              <button onClick={() => setActiveCat(1)}>Kafe</button>
              {categories.map(cat => (
                  <button key={cat.id} onClick={() => setActiveCat(cat.id)}>
                      {cat.nazev}
                  </button>
              ))}
              <button onClick={() => setActiveCat(2)}>Dezerty</button>
              {categories.map(cat => (
                  <button key={cat.id} onClick={() => setActiveCat(cat.id)}>
                      {cat.nazev}
                  </button>
              ))}

          </div>


         <div className="menu">{/* menu */}
            <h1>Menu</h1>
            {menu.filter(item => activeCat === null || item.category?.id === activeCat)
                .sort((a, b) => a.cena - b.cena)
                .map(item => (
              <div key={item.id}>{/*radky */}
              {item.nazev} {item.category?.nazev}. . . . . . . . . . . {item.cena}kč
              </div>

            ))}
            <button onClick={() => setPage('home')}>Zpět</button>
         </div>
      </div>

  )
}


export default App
