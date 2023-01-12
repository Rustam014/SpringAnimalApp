import { Outlet } from 'react-router-dom';

import React from 'react';

const Layout = () => {
    return (
        <>
            <Outlet/>
            <footer></footer>
        </>
    );
};

export {Layout};