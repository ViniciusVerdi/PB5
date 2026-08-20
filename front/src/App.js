import Header from "./Components/Header/Header.jsx";
import ProductList from "./Components/ProductList/ProductList.jsx";
import "./styles.css";
import { useState, useEffect } from "react";

export default function App() {
  const [inventoryItems, setInventoryItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/inventory-ms/estoque")
      .then((response) => {
        if (!response.ok) throw new Error("Erro ao buscar dados do estoque");
        return response.json();
      })
      .then((data) => {
        setInventoryItems(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading)
    return (
      <div className="App">
        <Header />
        <h1>Carregando inventário...</h1>
      </div>
    );
  if (error)
    return (
      <div className="App">
        <Header />
        <h1>Erro: {error}</h1>
      </div>
    );

  return (
    <div className="App">
      <Header />
      <h1>Visualização de Inventário</h1>
      <ProductList products={inventoryItems} />
    </div>
  );
}
