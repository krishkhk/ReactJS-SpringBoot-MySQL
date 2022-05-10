import React, { Component } from 'react'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import { faUser } from '@fortawesome/free-solid-svg-icons'

class HeaderComponent extends Component { 
 constructor(props) { 
 super(props) 
 this.state = { 
 
 } 
 } 
 render() { 
 return ( 
  <div>
            <header>
                <nav className="navbar navbar-light bg-light">
                <div className= "navbar-brand" ><FontAwesomeIcon icon={faUser} /> Employee Management App </div>
                </nav>
            </header>
        </div>
 ) 
 } 
} 
export default HeaderComponent