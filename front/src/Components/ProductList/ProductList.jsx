import "./styles.css";

export default function ProductList({ products }) {
  return (
    <table>
      <thead>
        <tr>
          <th>ID do Produto (UUID)</th>
          <th>Quantidade em Estoque</th>
        </tr>
      </thead>

      <tbody>
        {products.map((item) => (
          <tr key={item.idProduct}>
            <td className="uuid-column">{item.idProduct}</td>
            <td>{item.quantity}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
