import { describe, it, expect } from "vitest";
import { shows_login_screen } from "./login_state.js";

describe("shows_login_screen", () => {
  it("covers every state a refused login can end in", () => {
    // A state missing here renders nothing at all: the user gets a blank page rather than the
    // reason the login was refused.
    expect(shows_login_screen("unauthenticated")).toBe(true);
    expect(shows_login_screen("wrong_login")).toBe(true);
    expect(shows_login_screen("not_authorized_for_app")).toBe(true);
  });

  it("leaves the other states to the rest of the app", () => {
    expect(shows_login_screen("authenticated")).toBe(false);
    expect(shows_login_screen("unknown")).toBe(false);
  });
});
