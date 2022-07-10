import React from "react";
import {Link} from "react-router-dom";

export const Header=() => {

  return (
    <div>
      <h1>
              <Link to={''}>
                  <div className="header-section">
                      <h3 className="app-name">Learn IPL Dashboard</h3>
                      <br/>
                  </div>
              </Link>
        </h1>
    </div>
  );
}