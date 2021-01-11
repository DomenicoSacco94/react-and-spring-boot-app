import React from "react";

const Message = (props) => {
  return (
    <div className={props.className}>
      <div className="sender"> {props.senderName} </div>
      <div className="message-text"> {props.text} </div>
      <div className="date"> {props.sendDate} </div>
    </div>
  );
};

export default Message;
