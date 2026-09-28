export default function HistoryList({ history }) {
  return (
    <table>
      <thead>
        <tr>
          <th>ID do Produto (UUID)</th>
          <th>Tipo de Movimentação</th>
          <th>Quantidade</th>
          <th>Data/Hora</th>
        </tr>
      </thead>

      <tbody>
        {history.map((item) => (
          <tr key={item.id}>
            <td className="uuid-column">{item.idProduct}</td>
            <td>{item.movementType}</td>
            <td>{item.quantity}</td>
            <td>{new Date(item.timestamp).toLocaleString()}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
