/*
 * Copyright 2018-2020 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kunminx.puremusic.ui.page;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.kunminx.architecture.ui.BaseActivity;
import com.kunminx.puremusic.R;
import com.kunminx.puremusic.databinding.ActivityMainBinding;
import com.kunminx.puremusic.ui.adapter.GridItemAdapter;
import com.kunminx.puremusic.ui.state.MainActivityViewModel;

/**
 * Create by KunMinX at 19/10/16
 */

public class MainActivity extends BaseActivity {

    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivityViewModel = getActivityViewModel(MainActivityViewModel.class);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVm(mMainActivityViewModel);
        binding.setClick(new ClickProxy());

        binding.setAdapterJava(new GridItemAdapter(getApplicationContext(), item -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(item.getPackageName(), item.getPackageName() + "." + item.getActivityName()));
            startActivity(intent);
        }));

        binding.setAdapterKotlin(new GridItemAdapter(getApplicationContext(), item -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(item.getPackageName(), item.getPackageName() + "." + item.getActivityName()));
            startActivity(intent);
        }));

        mMainActivityViewModel.getJavaItemsLiveData().observe(this, gridItems -> {
            mMainActivityViewModel.javaList.setValue(gridItems);
        });

        mMainActivityViewModel.getKotlinItemsLiveData().observe(this, gridItems -> {
            mMainActivityViewModel.kotlinList.setValue(gridItems);
        });

        mMainActivityViewModel.requestJavaItems();
        mMainActivityViewModel.requestKotlinItems();
    }

    public class ClickProxy {

        public void openMenu() {

        }

        public void login() {
//            nav().navigate(R.id.action_mainFragment_to_loginFragment);
        }

        public void search() {
//            nav().navigate(R.id.action_mainFragment_to_searchFragment);
        }

    }
}
