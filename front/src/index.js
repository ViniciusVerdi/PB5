import { createRoot } from "react-dom/client";
import Routes from "./routes";
import { ToastContainer } from "react-toastify";

const rootElement = document.getElementById("root");
const root = createRoot(rootElement);

root.render(
  <>
    <Routes />
    <ToastContainer />
  </>
);
