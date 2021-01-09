import React, { Component } from "react";

export default class Message extends Component {
  render() {
    return (
      <div className="chat-message">
        <div className="sender"> {this.props.senderName} </div>
        <div className="message-text"> {this.props.text} </div>
        <div className="date"> {this.props.sendDate} </div>
      </div>
    );
  }
}
