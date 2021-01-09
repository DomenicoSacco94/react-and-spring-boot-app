import React, { Component } from "react";

export default class MessageForm extends Component {
  render() {
    return (
      <div class="message-form-container">
        <form class="message-form">
          <input
            class="message-textbox"
            type="text"
            name="name"
            placeholder="Write a message..."
          />
          <input class="message-button" type="submit" value="Send" />
        </form>
      </div>
    );
  }
}
