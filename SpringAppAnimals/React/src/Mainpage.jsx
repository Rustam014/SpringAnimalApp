import React from 'react';
import {Link} from "react-router-dom";

const Mainpage = () => {
    return (
        <div>
            <Link to={"/animal/"}>
                Show animals
            </Link>
            <p>
            </p>
            <Link to={"/examination/"}>
                Show examinations
            </Link>
            <p>
            </p>
            <Link to={"/food/"}>
                Show food
            </Link>
            <p>
            </p>
            <Link to={"/business/"}>
                Business operation
            </Link>
        </div>
    );
};

export default Mainpage;