import React from 'react';
import Numberlink from "./components/Numberlink";
import Header from "./components/Header";
import Footer from "./components/Footer";
import {Route, Routes} from "react-router-dom";
import Index from "./components/Index";


const App = () => (
    <span>
        <Header/>
        <main>
        <Routes>
            <Route path="/" element={<Index/>}/>
            <Route path="/numberlink/new" element={<Numberlink/>}/>
        </Routes>
        </main>
        <Footer/>
    </span>
)

export default App;
