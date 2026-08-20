import { useState, useEffect } from "react";
import "./styles.css";
import { toast } from "react-toastify";

export default function ProductForm({
  addProduct,
  productToEdit,
  setProductToEdit,
  updateProduct,
}) {
  const [name, setName] = useState("");
  const [category, setCategory] = useState("");
  const [price, setPrice] = useState("");

  useEffect(() => {
    if (productToEdit) {
      setName(productToEdit.name);
      setCategory(productToEdit.category);
      setPrice(productToEdit.price);
    }
  }, [productToEdit]);

  const validate = () => {
    if (!name || !category || !price) {
      return false;
    }
    return true;
  };

  const handleSubmit = (ev) => {
    ev.preventDefault();
    if (!validate()) {
      toast.error("Preencha todos os campos do formulário!", {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
      return;
    }
    const product = {
      id: 0,
      name: name.trim(),
      category: category.trim(),
      price: Number(price),
    };
    if (productToEdit) {
      product.id = productToEdit.id;
      updateProduct(product);
    } else {
      addProduct(product);
    }

    setName("");
    setCategory("");
    setPrice("");
    setProductToEdit();
  };

  return (
    <form className="productForm" onSubmit={handleSubmit} noValidate>
      {productToEdit ? <h2>Editar Produto</h2> : <h2>Adicionar Produto</h2>}
      <label>
        <strong>Nome:</strong>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Nome do produto"
        />
      </label>

      <label>
        <strong>Categoria:</strong>
        <input
          type="text"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          placeholder="Ex: Eletrônicos, Roupas"
        />
      </label>

      <label>
        <strong>Preço</strong>
        <input
          type="number"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          placeholder="0.00"
          min="0"
        />
      </label>

      <button type="submit" className="pfButton">
        {productToEdit ? "Editar Produto" : "Adicionar Produto"}
      </button>
    </form>
  );
}
