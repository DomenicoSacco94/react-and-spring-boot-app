import React, { Component } from "react";

class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = { currentUser: "" };
  }

  submitHandler = (event) => {
    event.preventDefault();
    if (this.state.currentUser !== "") {
      this.props.setCurrentUser(this.state.currentUser);
      this.props.setIsAuthenticated(true);
    }
  };

  changeHandler = (event) => {
    this.setState({ currentUser: event.target.value });
  };

  render() {
    return (
      <div className="login-form-container">
        <div className="login-label">Doodle Chat App</div>
        <form className="login-form" onSubmit={this.submitHandler.bind(this)}>
          <input
            className="login-textbox"
            type="text"
            name="name"
            placeholder="Enter username..."
            onChange={this.changeHandler}
          />
          <input className="login-button" type="submit" value="Login" />
        </form>
      </div>
    );
  }
}

export default LoginForm;
