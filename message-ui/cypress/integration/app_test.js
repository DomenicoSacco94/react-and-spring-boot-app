describe("Login test", () => {
  it("Login is possible", () => {
    cy.visit("http://localhost:3000");
    cy.get('[data-cy="login-textbox"]').type("John Doe");
    cy.get('[data-cy="login-button"]').click();
    cy.get('[data-cy="send-button"]').should("exist");
    cy.get('[data-cy="message-textbox"]').should("exist");
    cy.get('[data-cy="chat-message-list"]').should("exist");
  });
});
