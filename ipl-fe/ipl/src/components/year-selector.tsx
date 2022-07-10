import React from "react";
import {Link} from "react-router-dom";

export const YearSelector=({teamName}) => {

    let years=[];
    const startYear=Number(process.env.REACT_APP_DATA_START_YEAR);
    const endYear=Number(process.env.REACT_APP_DATA_END_YEAR);

    let i: number;
    for(i=endYear;i>=startYear;i--) {
        years.push(i);
    }


    return (
        <ol className="YearSelector">
            {years.map(year => (
                <li key={year}>
                    <Link to={`/teams/${teamName}/matches/${year}`}>{year}</Link>
                </li>
            ))}
        </ol>
    )
}