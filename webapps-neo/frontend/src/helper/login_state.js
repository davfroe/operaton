/**
 * The login states that put the login screen on screen.
 *
 * Every state a refused login can end in has to be listed here. A state that is missing renders
 * nothing at all, so the user gets a blank page instead of the reason the login was refused.
 */
export const shows_login_screen = (data) =>
  data === "unauthenticated" ||
  data === "wrong_login" ||
  data === "not_authorized_for_app";
