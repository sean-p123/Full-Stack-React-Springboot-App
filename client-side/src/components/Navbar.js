import {Link, useMatch, useResolvedPath} from "react-router-dom";

export default function NavBar(){
    
    return (<nav className="nav">
        <Link to="/" className="site-title">Student-Lecturer App</Link>
        <ul>
            <CustomLink to="/lecturer">Lecturers</CustomLink>
            <CustomLink to="/student">Students</CustomLink>
        </ul>
    </nav>)
}

function CustomLink({to, children, ...props}){
    
    const resolvedPath = useResolvedPath(to)
    const isActive = useMatch({path: resolvedPath.pathname, end:true});
    return (
        <li className={isActive ? "active": ""}>
            <Link to={to}>{children}</Link>
        </li>
    )
}