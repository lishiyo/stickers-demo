package com.mirhoseini.marvel.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mirhoseini.marvel.ApplicationComponent;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.base.BaseActivity;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.databinding.ActivityCharacterBinding;
import timber.log.Timber;

public class CharacterActivity extends BaseActivity {

    private static final String ARG_CHARACTER = "characterModel";

    // injecting views via ButterKnife
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newIntent(Context context, CharacterModel character) {
        Intent intent = new Intent(context, CharacterActivity.class);
        intent.putExtra(ARG_CHARACTER, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding is named after layout
        ActivityCharacterBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_character);

        // inject views using ButterKnife
        ButterKnife.bind(this);

        if (null == getIntent() || null == getIntent().getExtras() || null == getIntent().getExtras().getSerializable(ARG_CHARACTER)) {
            finish();
            return;
        }

        // get args
        CharacterModel character = (CharacterModel) getIntent().getExtras().getSerializable(ARG_CHARACTER);

        // bind value using Android Binding
//        binding.setVariable(BR.character, character);
        binding.setCharacter(character);

        setupToolbar(character.getName());

        Timber.d("Character Activity Created");
    }

    private void setupToolbar(String characterName) {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logo);
        getSupportActionBar().setTitle(characterName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }

}
