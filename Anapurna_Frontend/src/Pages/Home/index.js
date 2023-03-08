import { Link } from "react-router-dom"

const Home = () => {
    return (
        <div>
            <h1 className="mt-3 mb-5">Select sign in / sign up page</h1>
            <table className="table">
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Sign In</th>
                        <th>Sign Up</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Customer</td>
                        <td>
                            <Link to={"/customer/signin"}>
                                <button type="button" className="btn btn-primary">Sign In</button>
                            </Link>
                        </td>
                        <td>
                            <Link to={"/customer/signup"}>
                                <button type="button" className="btn btn-primary">Sign Up</button>
                            </Link>
                        </td>
                    </tr>
                    <tr>
                        <td>Restaurant Manager</td>
                        <td>
                            <Link to={"/manager/signin"}>
                                <button type="button" className="btn btn-primary">Sign In</button>
                            </Link>
                        </td>
                        <td>
                            <Link to={"/manager/signup"}>
                                <button type="button" className="btn btn-primary">Sign Up</button>
                            </Link>
                        </td>
                    </tr>
                    <tr>
                        <td>Delivery Person</td>
                        <td>
                          <Link to={"/dp/signin"}>
                            <button type="button" className="btn btn-primary">Sign In</button>
                            </Link>
                        </td>
                        <td>
                          <Link to={"/deliveryperson/signup"}>
                            <button type="button" className="btn btn-primary">Sign Up</button>
                            </Link>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default Home