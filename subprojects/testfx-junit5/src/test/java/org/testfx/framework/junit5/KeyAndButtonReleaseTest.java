/*
 * Copyright 2013-2014 SmartBear Software
 * Copyright 2014-2022 The TestFX Contributors
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
 * European Commission - subsequent versions of the EUPL (the "Licence"); You may
 * not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */
package org.testfx.framework.junit5;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests to see if pressed keys/buttons are released after a test is run.
 * <p>
 * For keys, this prevents hanging issues (if Shortcut key is pressed) or deletion issues (if backspace/delete key
 * is pressed) or insertion issues (if anything else is pressed). The same for mouse buttons
 */
@Disabled("junit5 doesn't support test ordering ATM, but this functionality can be added in future")
class KeyAndButtonReleaseTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @Test
    void b_When_a_test_forgets_to_release_keys() {
        press(KeyCode.CONTROL, KeyCode.SHIFT, KeyCode.ALT);
    }

    @Test
    void c_Then_keys_are_not_pressed() {
        assertThat(robotContext().getKeyboardRobot().getPressedKeys().isEmpty(), is(true));
    }

    @Test
    void e_When_a_test_forgets_to_release_buttons() {
        press(MouseButton.PRIMARY, MouseButton.SECONDARY, MouseButton.MIDDLE);
    }

    @Test
    void f_Then_buttons_are_not_pressed() {
        assertThat(robotContext().getMouseRobot().getPressedButtons().isEmpty(), is(true));
    }
}
