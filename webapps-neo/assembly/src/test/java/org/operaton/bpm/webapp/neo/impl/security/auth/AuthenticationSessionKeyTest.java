/*
 * Copyright 2026 the Operaton contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.operaton.bpm.webapp.neo.impl.security.auth;

import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthenticationSessionKeyTest {

  private static final String LEGACY_SESSION_KEY = "authenticatedUser";

  @Test
  void shouldNotUseTheLegacySessionAttributeName() {
    assertThat(AuthenticationUtil.CAM_AUTH_SESSION_KEY).isNotEqualTo(LEGACY_SESSION_KEY);
  }

  @Test
  void shouldNotTouchTheLegacySessionAttribute() {
    HttpSession session = mock(HttpSession.class);

    Authentications authentications = AuthenticationUtil.getAuthsFromSession(session);

    verify(session).setAttribute(AuthenticationUtil.CAM_AUTH_SESSION_KEY, authentications);
    verify(session, never()).setAttribute(eq(LEGACY_SESSION_KEY), any());
  }

  @Test
  void shouldIgnoreALegacyObjectInTheSameSession() {
    HttpSession session = mock(HttpSession.class);
    when(session.getAttribute(LEGACY_SESSION_KEY)).thenReturn(new Object());

    assertThatNoException().isThrownBy(() -> AuthenticationUtil.getAuthsFromSession(session));
  }
}
