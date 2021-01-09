import React, { Component } from "react";

export default class Message extends Component {
  render() {
    return (
      <div class="chat-message">
        <div class="sender"> {this.props.senderName} </div>
        <div class="message-text"> {this.props.text} </div>
        <div class="date"> {this.props.sendDate} </div>
      </div>
    );
  }
}
