import { createBrowserRouter } from "react-router";
import { RouterProvider } from "react-router/dom";
import App from "./App.js";
import ProductForm from "./Components/ProductForm/ProductForm.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/adicionar",
    element: <ProductForm />,
  },
]);

export default function Routes() {
  return <RouterProvider router={router} />;
}
