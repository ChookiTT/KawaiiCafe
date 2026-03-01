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
    useEffect(() => {
        fetch('http://localhost:8080/menu')
            .then(res => res.json())
            .then(data => setMenu(data));
    }, []);
  return(
      <html>
      <head>
          <link href="https://fonts.googleapis.com/css2?family=Cherry+Bomb+One&display=swap" rel="stylesheet"/>
          <title>KawaiiCafe</title>
      </head>
      <h1>Menu</h1>
      <body>

      <div className="menu">{/* menu */}
          {menu.map(item => (
              <div key={item.id}>{/*radky */}
              {item.nazev} - kategorie{item.category?.nazev}. . . . . . . . . . . {item.cena}kč
          </div>
      ))}
  </div>
  </body>
  </html>)
}

export default App
