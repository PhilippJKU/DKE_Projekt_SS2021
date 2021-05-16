import React from 'react';
import {NavLink, Switch, BrowserRouter, Route, Link} from 'react-router-dom';
import LoginPage from "../pages/LoginPage";
import SignUpPage from "../pages/SignUpPage";


function Home (){
    return localStorage.getItem('user') ? (
            <div>
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <label> Your are logged in.</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <Link to="/profile">Profile</Link>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#" onClick={localStorage.clear()}>LOGOUT</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <Link to="/profile">Sign Up</Link>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        ) : (
            <div>
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <label> Your are not logged in. </label>
                        </td>
                    </tr>
                    <tr>
                        {}
                        <td>
                            <Link to="/login">Login</Link>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <Link to="/signup">Sign Up</Link>
                        </td>
                    </tr>
                    </tbody>
                    {/*<Switch>*/}
                    {/*    <Route exact path='/login' component={LoginPage}/>*/}
                    {/*    <Route exact path='/signup' component={SignUpPage}/>*/}
                    {/*</Switch>*/}
                </table>
            </div>
        );
}

export default Home;