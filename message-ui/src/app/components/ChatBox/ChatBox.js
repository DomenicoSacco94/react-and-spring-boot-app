import React, { useState } from "react";

import MessageDisplayer from "../MessageDisplayer/MessageDisplayer";
import MessageForm from "../MessageForm/MessageForm";

const ChatBox = (props) => {
  const [list, setList] = useState([]);

  return (
    <>
      <MessageDisplayer
        currentUser={props.currentUser}
        messageList={list}
        setMessageList={setList}
      />
      <MessageForm currentUser={props.currentUser} setMessageList={setList} />
    </>
  );
};

export default ChatBox;
