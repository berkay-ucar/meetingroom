import { useState } from "react";
import api from "../api/api";
import { useNavigate } from "react-router-dom";

function LoginPage() {
    const [email,setEmail] = useState("");
    const [password,setPassword] = useState("");
    const navigate = useNavigate();

    const login = async () => {
        const res = await api.post("/auth/login",{email,password});
        localStorage.setItem("token",res.data);
        navigate("/");
    }

    return (
        <div>
            <h2>Login</h2>
            <input placeholder="Email" onChange={e=>setEmail(e.target.value)} />
            <input placeholder="Password" type="password" onChange={e=>setPassword(e.target.value)} />
            <button onClick={login}>Login</button>
        </div>
    );
}

export default LoginPage;