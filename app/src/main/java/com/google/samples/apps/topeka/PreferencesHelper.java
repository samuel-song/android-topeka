/*
 * Copyright 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.samples.apps.topeka;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.samples.apps.topeka.model.Avatar;
import com.google.samples.apps.topeka.model.Player;

public class PreferencesHelper {

    private static final String PLAYER_PREFERENCES = "playerPreferences";
    private static final String PREFERENCE_FIRST_NAME = PLAYER_PREFERENCES + ".firstName";
    private static final String PREFERENCE_LAST_INITIAL = PLAYER_PREFERENCES + ".lastInitial";
    private static final String PREFERENCE_AVATAR = PLAYER_PREFERENCES + ".avatar";


    public static void writeToPreferences(Context context, Player player) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCE_FIRST_NAME, player.getFirstName());
        editor.putString(PREFERENCE_LAST_INITIAL, player.getLastInitial());
        editor.putString(PREFERENCE_AVATAR, player.getAvatar().name());
        editor.apply();
    }

    public static Player getPlayer(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        final String firstName = preferences.getString(PREFERENCE_FIRST_NAME, "");
        final String lastInitial = preferences.getString(PREFERENCE_LAST_INITIAL, "");
        final Avatar avatar = Avatar.valueOf(
                preferences.getString(PREFERENCE_AVATAR, Avatar.ONE.name()));
        return new Player(firstName, lastInitial, avatar);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PLAYER_PREFERENCES, Context.MODE_PRIVATE);
    }
}