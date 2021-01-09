import React, { Component } from "react";
import moment from "moment";
import { saveMessages } from "../../services/MessageService";

export default class MessageForm extends Component {
  constructor(props) {
    super(props);
    this.state = { message: { senderName: "currentUser", text: "" } };
  }

  submitHandler = (event) => {
    event.preventDefault();
    if (this.state.message.text !== "") {
      const date = moment().format("DD-MM-YYYY HH:mm:ss.SSS").toString();

      const messageToSend = {
        senderName: this.state.message.senderName,
        text: this.state.message.text,
        sendDate: date,
      };

      console.log("props of message form");
      console.log(this.props);

      saveMessages(messageToSend);

      this.props.setMessageList((messageList) => [
        ...messageList,
        messageToSend,
      ]);

      this.setState({
        message: { ...this.state.message, text: "" },
      });
    }
  };

  changeHandler = (event) => {
    this.setState({
      message: { ...this.state.message, text: event.target.value },
    });
  };

  render() {
    return (
      <div className="message-form-container">
        <form className="message-form" onSubmit={this.submitHandler}>
          <input
            className="message-textbox"
            type="text"
            name="name"
            placeholder="Write a message..."
            onChange={this.changeHandler}
            value={this.state.message.text}
          />
          <input className="message-button" type="submit" value="Send" />
        </form>
      </div>
    );
  }
}
