const apiPath = "/api/v1/messages";

export function getMessages() {
  return fetch(apiPath).then((res) => res.json());
}

export function saveMessages(message) {
  fetch("/api/v1/messages", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(message),
  });
}
