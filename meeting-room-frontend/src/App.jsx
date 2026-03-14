import { BrowserRouter, Routes, Route } from "react-router-dom"
import AdminPage from "./pages/AdminPage"
import LoginPage from "./pages/LoginPage.jsx";
import Reservations from "./pages/Reservations.jsx";

function App(){

    return(
        <BrowserRouter>

            <Routes>

                <Route path="/" element={<Reservations/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/admin" element={<AdminPage/>}/>

            </Routes>



        </BrowserRouter>
    )

}

export default App