/*
 * Copyright (C) 2016 Peter Gregus for GravityBox Project (C3C076@xda)
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

package com.ceco.oreo.gravitybox.shortcuts;

import java.util.ArrayList;
import java.util.List;

import com.ceco.oreo.gravitybox.R;
import com.ceco.oreo.gravitybox.ModHwKeys;
import com.ceco.oreo.gravitybox.adapters.IIconListAdapterItem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

public class RotationLockShortcut extends AMultiShortcut {
    protected static final String ACTION =  ModHwKeys.ACTION_TOGGLE_ROTATION_LOCK;

    public RotationLockShortcut(Context context) {
        super(context);
    }

    @Override
    public String getText() {
        return mContext.getString(R.string.hwkey_action_auto_rotation);
    }

    @Override
    public Drawable getIconLeft() {
        return mContext.getDrawable(R.drawable.shortcut_rotation_lock);
    }

    @Override
    protected String getAction() {
        return ACTION;
    }

    @Override
    protected String getShortcutName() {
        return getText();
    }

    @Override
    protected boolean supportsToast() {
        return true;
    }

    @Override
    protected List<IIconListAdapterItem> getShortcutList() {
        final List<IIconListAdapterItem> list = new ArrayList<>();
        list.add(new ShortcutItem(mContext, R.string.hwkey_action_auto_rotation,
                R.drawable.shortcut_rotation_lock, null));
        list.add(new ShortcutItem(mContext, R.string.auto_rotation_on,
                R.drawable.shortcut_rotation_lock_enable, new ExtraDelegate() {
                @Override
                public void addExtraTo(Intent intent) {
                    intent.putExtra(EXTRA_ENABLE, true);
                }
        }));
        list.add(new ShortcutItem(mContext, R.string.auto_rotation_off,
                R.drawable.shortcut_rotation_lock_disable, new ExtraDelegate() {
                @Override
                public void addExtraTo(Intent intent) {
                    intent.putExtra(EXTRA_ENABLE, false);
                }
        }));
        return list;
    }

    public static void launchAction(final Context context, Intent intent) {
        Intent launchIntent = new Intent(ACTION);
        launchIntent.putExtras(intent.getExtras());
        context.sendBroadcast(launchIntent);
    }
}
