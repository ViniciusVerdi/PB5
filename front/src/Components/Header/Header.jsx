import "./styles.css";
export default function Header({ activePage, onNavigate }) {
  return (
    <header>
      <p className="titleHeader">Gestão de Produtos</p>
      <div className="nav">
        <p className="page" onClick={() => onNavigate("inventario")}>
          Inventário
        </p>
        <p className="page" onClick={() => onNavigate("historico")}>
          Histórico
        </p>
        <div className="leaveButton">Sair</div>
      </div>
    </header>
  );
}
